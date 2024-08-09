import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static GerenciamentoClientes gerenciamentoClientes = new GerenciamentoClientes();
    private static List<Produto> produtos = new ArrayList<>(); // Lista para armazenar produtos
    private static List<Pedido> pedidos = new ArrayList<>(); // Lista para armazenar pedidos

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        // Adiciona alguns produtos para testes
        produtos.add(new Produto(1, "Produto A", "Descrição do Produto A", 10.0, 100));
        produtos.add(new Produto(2, "Produto B", "Descrição do Produto B", 20.0, 50));

        while (continuar) {
            Util.clearScreen(); // Limpa a tela antes de exibir o menu principal
            exibirMenuPrincipal(); // Exibe o menu principal

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    buscarCliente(scanner);
                    break;
                case 3:
                    exibirTodosClientes();
                    break;
                case 4:
                    atualizarDadosCliente(scanner);
                    break;
                case 5:
                    if (loginCliente(scanner)) {
                        menuCliente(scanner);
                    }
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }

            if (opcao != 6) {
                System.out.print("\nPressione Enter para continuar...");
                scanner.nextLine(); // Espera o usuário pressionar Enter
            }
        }

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\nMenu Principal:");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Buscar Cliente por ID");
        System.out.println("3. Exibir Todos os Clientes");
        System.out.println("4. Atualizar Dados do Cliente");
        System.out.println("5. Entrar como Cliente");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void menuCliente(Scanner scanner) {
        boolean continuarCliente = true;

        while (continuarCliente) {
            Util.clearScreen(); // Limpa a tela antes de exibir o menu do cliente
            exibirMenuCliente(); // Exibe o menu do cliente

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    criarPedido(scanner);
                    break;
                case 3:
                    exibirPedidos();
                    break;
                case 4:
                    continuarCliente = false;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }

            if (opcao != 4) {
                System.out.print("\nPressione Enter para continuar...");
                scanner.nextLine(); // Espera o usuário pressionar Enter
            }
        }
    }

    private static void exibirMenuCliente() {
        System.out.println("\nMenu do Cliente:");
        System.out.println("1. Listar Produtos");
        System.out.println("2. Criar Pedido");
        System.out.println("3. Exibir Pedidos");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void listarProdutos() {
        Util.clearScreen(); // Limpa a tela antes de listar os produtos
        System.out.println("Lista de Produtos:");
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    private static void criarPedido(Scanner scanner) {
        Util.clearScreen(); // Limpa a tela antes de criar o pedido
        System.out.println("Criar Pedido:");
        
        Pedido pedido = new Pedido(pedidos.size() + 1, new Date(), "Pendente");
        boolean continuarAdicionandoItens = true;

        while (continuarAdicionandoItens) {
            System.out.print("Digite o ID do Produto: ");
            int idProduto = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            Produto produto = produtos.stream().filter(p -> p.getIdProduto() == idProduto).findFirst().orElse(null);
            if (produto == null) {
                System.out.println("Produto não encontrado.");
                continue;
            }

            System.out.print("Digite a quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            if (quantidade <= 0 || quantidade > produto.getEstoque()) {
                System.out.println("Quantidade inválida.");
                continue;
            }

            Item item = new Item(idProduto, quantidade, produto.getPreco());
            pedido.adicionarItem(item);
            produto.atualizaEstoque(produto.getEstoque() - quantidade);

            System.out.print("Deseja adicionar outro item? (s/n): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("n")) {
                continuarAdicionandoItens = false;
            }
        }

        pedidos.add(pedido);
        System.out.println("Pedido criado com sucesso!");
    }

    private static void exibirPedidos() {
        Util.clearScreen(); // Limpa a tela antes de exibir os pedidos
        System.out.println("Lista de Pedidos:");
        for (Pedido pedido : pedidos) {
            System.out.println("ID do Pedido: " + pedido.getIdPedido() +
                               ", Data: " + pedido.getDataPedido() +
                               ", Status: " + pedido.getStatus() +
                               ", Total: " + pedido.calcularTotal());
            System.out.println("Itens:");
            for (Item item : pedido.getItens()) {
                System.out.println(" - ID Item: " + item.getIdItem() +
                                   ", Quantidade: " + item.getQuantidade() +
                                   ", Preço Unitário: " + item.getPrecoUnitario() +
                                   ", Subtotal: " + item.calcularSubtotal());
            }
        }
    }

    private static boolean loginCliente(Scanner scanner) {
        Util.clearScreen(); // Limpa a tela antes do login
        System.out.print("Digite o username: ");
        String username = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        // Verifica se o cliente com username e senha fornecidos existe
        for (Cliente cliente : gerenciamentoClientes.listarTodosClientes()) {
            if (cliente.login(username, senha)) {
                System.out.println("Login realizado com sucesso!");
                return true;
            }
        }

        System.out.println("Username ou senha inválidos.");
        return false;
    }

    private static void cadastrarCliente(Scanner scanner) {
        Util.clearScreen(); // Limpa a tela antes de exibir a opção de cadastro
        System.out.println("Escolha o tipo de cliente:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Digite a opção: ");
        int tipoCliente = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
    
        System.out.print("Digite o ID do Cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
    
        System.out.print("Digite o nome do Cliente: ");
        String nome = scanner.nextLine();
    
        System.out.print("Digite o email do Cliente: ");
        String email = scanner.nextLine();
    
        System.out.print("Digite o endereço do Cliente: ");
        String endereco = scanner.nextLine();
    
        System.out.print("Digite o telefone do Cliente: ");
        String telefone = scanner.nextLine();
    
        System.out.print("Digite o username do Cliente: ");
        String userName = scanner.nextLine();
    
        System.out.print("Digite a senha do Cliente: ");
        String password = scanner.nextLine();
    
        Cliente cliente = null;
        try {
            if (tipoCliente == 1) {
                // Pessoa Física
                System.out.print("Digite o CPF: ");
                String cpf = scanner.nextLine();
    
                System.out.print("Digite o RG: ");
                String rg = scanner.nextLine();
    
                System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
                String dataNascimento = scanner.nextLine();
    
                cliente = new PessoaFisica(id, nome, email, endereco, telefone, userName, password, cpf, rg, dataNascimento);
            } else if (tipoCliente == 2) {
                // Pessoa Jurídica
                System.out.print("Digite o CNPJ: ");
                String cnpj = scanner.nextLine();
    
                System.out.print("Digite a razão social: ");
                String razaoSocial = scanner.nextLine();
    
                System.out.print("Digite a inscrição estadual: ");
                String inscricaoEstadual = scanner.nextLine();
    
                cliente = new PessoaJuridica(id, nome, email, endereco, telefone, userName, password, cnpj, razaoSocial, inscricaoEstadual);
            } else {
                System.out.println("Tipo de cliente inválido.");
                return;
            }
    
            if (!gerenciamentoClientes.adicionarCliente(cliente)) {
                System.out.println("Cliente não cadastrado. Voltando ao menu principal.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private static void buscarCliente(Scanner scanner) {
        Util.clearScreen(); // Limpa a tela antes de exibir a opção de busca
        System.out.print("Digite o ID do Cliente: ");
        int idBusca = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Cliente clienteEncontrado = gerenciamentoClientes.buscarClientePorId(idBusca);
        if (clienteEncontrado != null) {
            System.out.println("Cliente encontrado:\n" + clienteEncontrado);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void exibirTodosClientes() {
        Util.clearScreen(); // Limpa a tela antes de exibir todos os clientes
        gerenciamentoClientes.exibirTodosClientes();
    }

    private static void atualizarDadosCliente(Scanner scanner) {
        Util.clearScreen(); // Limpa a tela antes de exibir a opção de atualização
        System.out.print("Digite o ID do Cliente para atualizar: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Cliente cliente = gerenciamentoClientes.buscarClientePorId(idCliente);
        if (cliente != null) {
            System.out.print("Digite o novo nome do Cliente (deixe em branco para não alterar): ");
            String novoNome = scanner.nextLine();
            System.out.print("Digite o novo email do Cliente (deixe em branco para não alterar): ");
            String novoEmail = scanner.nextLine();
            System.out.print("Digite o novo endereço do Cliente (deixe em branco para não alterar): ");
            String novoEndereco = scanner.nextLine();
            System.out.print("Digite o novo telefone do Cliente (deixe em branco para não alterar): ");
            String novoTelefone = scanner.nextLine();

            if (!novoNome.isBlank()) cliente.setNome(novoNome);
            if (!novoEmail.isBlank()) cliente.setEmail(novoEmail);
            if (!novoEndereco.isBlank()) cliente.setEndereco(novoEndereco);
            if (!novoTelefone.isBlank()) cliente.setTelefone(novoTelefone);

            System.out.println("Dados atualizados com sucesso!");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}

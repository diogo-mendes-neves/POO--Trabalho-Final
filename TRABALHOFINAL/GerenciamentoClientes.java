import java.util.ArrayList;
import java.util.List;

public class GerenciamentoClientes {
    private List<Cliente> clientes;
    private Cliente clienteAtual; // Cliente atualmente logado

    public GerenciamentoClientes() {
        this.clientes = new ArrayList<>();
        this.clienteAtual = null; // Nenhum cliente logado inicialmente
    }

    public boolean adicionarCliente(Cliente cliente) {
        if (cliente == null) {
            System.out.println("Erro ao cadastrar cliente: Cliente é nulo.");
            return false;
        }

        if (isIdDuplicado(cliente.getIdCliente())) {
            System.out.println("Erro: Cliente com ID " + cliente.getIdCliente() + " já cadastrado.");
            return false;
        }

        if (cliente instanceof PessoaFisica) {
            PessoaFisica pf = (PessoaFisica) cliente;
            if (isCpfDuplicado(pf.getCpf())) {
                System.out.println("Erro: Cliente com CPF " + pf.getCpf() + " já cadastrado.");
                return false;
            }
            if (isRgDuplicado(pf.getRg())) {
                System.out.println("Erro: Cliente com RG " + pf.getRg() + " já cadastrado.");
                return false;
            }
        } else if (cliente instanceof PessoaJuridica) {
            PessoaJuridica pj = (PessoaJuridica) cliente;
            if (isCnpjDuplicado(pj.getCnpj())) {
                System.out.println("Erro: Cliente com CNPJ " + pj.getCnpj() + " já cadastrado.");
                return false;
            }
        }

        if (isUserNameDuplicado(cliente.getUserName())) {
            System.out.println("Erro: Cliente com username " + cliente.getUserName() + " já cadastrado.");
            return false;
        }

        this.clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso: " + cliente.getNome());
        return true;
    }

    public Cliente buscarClientePorId(int idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        System.out.println("Cliente com ID " + idCliente + " não encontrado.");
        return null;
    }

    public List<Cliente> listarTodosClientes() {
        return new ArrayList<>(clientes); // Retorna uma cópia da lista de clientes
    }

    public void exibirTodosClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getIdCliente() + " | Nome: " + cliente.getNome());
            }
        }
    }

    public boolean atualizarCliente(int idCliente, String nome, String email, String endereco, String telefone) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente == null) {
            return false; // Cliente não encontrado
        }
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefone);
        System.out.println("Dados do cliente com ID " + idCliente + " atualizados com sucesso.");
        return true;
    }

    private boolean isIdDuplicado(int idCliente) {
        for (Cliente c : clientes) {
            if (c.getIdCliente() == idCliente) {
                return true;
            }
        }
        return false;
    }

    private boolean isCpfDuplicado(String cpf) {
        for (Cliente c : clientes) {
            if (c instanceof PessoaFisica && ((PessoaFisica) c).getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRgDuplicado(String rg) {
        for (Cliente c : clientes) {
            if (c instanceof PessoaFisica && ((PessoaFisica) c).getRg().equals(rg)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCnpjDuplicado(String cnpj) {
        for (Cliente c : clientes) {
            if (c instanceof PessoaJuridica && ((PessoaJuridica) c).getCnpj().equals(cnpj)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUserNameDuplicado(String userName) {
        for (Cliente c : clientes) {
            if (c.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

    public Cliente login(String userName, String password) {
        for (Cliente cliente : clientes) {
            if (cliente.login(userName, password)) {
                clienteAtual = cliente;
                return cliente;
            }
        }
        System.out.println("Credenciais inválidas.");
        return null;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void logout() {
        clienteAtual = null;
    }

}

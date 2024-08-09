public class Produto {
    private int idProduto;
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;

    public Produto(int idProduto, String nome, String descricao, double preco, int estoque) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    // Atualiza o estoque
    public void atualizaEstoque(int quantidade) {
        this.estoque += quantidade;
    }

    // Define um novo estoque
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    // Atualiza o preço
    public void atualizaPreco(double preco) {
        this.preco = preco;
    }

    // Adiciona uma quantidade ao estoque
    public void adicionarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.estoque += quantidade;
        } else {
            System.out.println("Quantidade inválida para adicionar ao estoque.");
        }
    }

    // Remove uma quantidade do estoque
    public void removerEstoque(int quantidade) {
        if (quantidade > 0 && quantidade <= this.estoque) {
            this.estoque -= quantidade;
        } else {
            System.out.println("Quantidade inválida ou estoque insuficiente.");
        }
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + idProduto +
                ", nome='" + nome + '\'' +
                ", descrição='" + descricao + '\'' +
                ", preço=" + preco +
                ", estoque=" + estoque +
                '}';
    }
}

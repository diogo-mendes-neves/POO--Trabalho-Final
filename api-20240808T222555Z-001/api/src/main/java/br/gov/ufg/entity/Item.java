package br.gov.ufg.entity;

public class Item {
    private int idItem;
    private int quantidade;
    private double precoUnitario;

    public Item(int idItem, int quantidade, double precoUnitario) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (precoUnitario < 0) {
            throw new IllegalArgumentException("Preço unitário não pode ser negativo.");
        }
        this.idItem = idItem;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public int getIdItem() {
        return idItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade > 0) {
            this.quantidade = quantidade;
        } else {
            System.out.println("Quantidade deve ser maior que zero.");
        }
    }

    public void setPrecoUnitario(double precoUnitario) {
        if (precoUnitario >= 0) {
            this.precoUnitario = precoUnitario;
        } else {
            System.out.println("Preço unitário não pode ser negativo.");
        }
    }

    public double calcularSubtotal() {
        return quantidade * precoUnitario;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + idItem +
                ", quantidade=" + quantidade +
                ", preço unitário=" + precoUnitario +
                ", subtotal=" + calcularSubtotal() +
                '}';
    }
}

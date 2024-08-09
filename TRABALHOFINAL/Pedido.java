import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int idPedido;
    private Date dataPedido;
    private String status;
    private List<Item> itens;

    public Pedido(int idPedido, Date dataPedido, String status) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.status = status;
        this.itens = new ArrayList<>();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
    }

    public void adicionarItem(Item item) {
        if (item != null) {
            itens.add(item);
        } else {
            System.out.println("Item não pode ser nulo.");
        }
    }

    public void removerItem(Item item) {
        if (item != null) {
            itens.remove(item);
        } else {
            System.out.println("Item não pode ser nulo.");
        }
    }

    public List<Item> getItens() {
        return new ArrayList<>(itens); // Retorna uma cópia da lista de itens
    }

    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido{")
          .append("id=").append(idPedido)
          .append(", data=").append(dataPedido)
          .append(", status='").append(status).append('\'')
          .append(", itens=[");
        
        for (Item item : itens) {
            sb.append("\n  ").append(item);
        }

        sb.append("\n], total=").append(calcularTotal())
          .append('}');
        
        return sb.toString();
    }
}

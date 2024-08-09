package br.gov.ufg.entity;

public class Cliente {
    private int idCliente;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private String userName;
    private String password;

    public Cliente(int idCliente, String nome, String email, String endereco, String telefone, String userName, String password) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.userName = userName;
        this.password = password;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ID: " + idCliente + "\n" +
               "Nome: " + nome + "\n" +
               "Email: " + email + "\n" +
               "Endereço: " + endereco + "\n" +
               "Telefone: " + telefone + "\n" +
               "Username: " + userName;
    }

    public boolean login(String userName, String password) {
        if (userName == null || password == null) {
            return false;
        }
        return this.userName.equals(userName) && this.password.equals(password);
    }
}

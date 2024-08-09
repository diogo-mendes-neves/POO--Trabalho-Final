package br.gov.ufg.entity;

public class PessoaJuridica extends Cliente {
    private String cnpj;
    private String razaoSocial;
    private String inscricaoEstadual;

    public PessoaJuridica(int idCliente, String nome, String email, String endereco, String telefone, String userName, String password, String cnpj, String razaoSocial, String inscricaoEstadual) {
        super(idCliente, nome, email, endereco, telefone, userName, password);
        if (validarCNPJ(cnpj)) {
            this.cnpj = cnpj;
        } else {
            throw new IllegalArgumentException("CNPJ inválido!");
        }
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "CNPJ: " + cnpj + "\n" +
               "Razão Social: " + razaoSocial + "\n" +
               "Inscrição Estadual: " + inscricaoEstadual;
    }

    private boolean validarCNPJ(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || cnpj.matches(cnpj.charAt(0) + "{14}")) {
            return false;
        }

        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int digito1 = calcularDigito(cnpj.substring(0, 12), pesos1);
        int digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesos2);

        return cnpj.equals(cnpj.substring(0, 12) + digito1 + digito2);
    }

    private int calcularDigito(String str, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += Character.getNumericValue(str.charAt(i)) * pesos[i];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}

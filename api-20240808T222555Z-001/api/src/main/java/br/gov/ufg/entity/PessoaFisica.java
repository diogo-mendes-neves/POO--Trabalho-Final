package br.gov.ufg.entity;

public class PessoaFisica extends Cliente {
    private String cpf;
    private String rg;
    private String dataNascimento;

    public PessoaFisica(int idCliente, String nome, String email, String endereco, String telefone, String userName, String password, String cpf, String rg, String dataNascimento) {
        super(idCliente, nome, email, endereco, telefone, userName, password);
        if (validarCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido!");
        }
        this.rg = rg;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "CPF: " + cpf + "\n" +
               "RG: " + rg + "\n" +
               "Data de Nascimento: " + dataNascimento;
    }

    private boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
            return false;
        }

        int[] pesos = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int digito1 = calcularDigito(cpf.substring(0, 9), pesos);
        int digito2 = calcularDigito(cpf.substring(0, 9) + digito1, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2});

        return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
    }

    private int calcularDigito(String str, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += Character.getNumericValue(str.charAt(i)) * pesos[i];
        }
        int resto = 11 - (soma % 11);
        return (resto > 9) ? 0 : resto;
    }
}

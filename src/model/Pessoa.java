package model;

import java.time.LocalDate;
import java.time.Period;

public class Pessoa {
    private String nome;
    private String sobrenome;
    private LocalDate dataNasc;
    private String cpf;

    public Pessoa(String nome, String sobrenome, LocalDate dataNasc, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
    }

    public static Pessoa criarPessoa(String nome, String sobrenome, LocalDate dataNasc, String cpf) {
        if (!validaCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        return new Pessoa(nome, sobrenome, dataNasc, cpf);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public int getIdade() {
        return Period.between(dataNasc, LocalDate.now()).getYears();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!validaCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = cpf;
    }

    private static boolean validaCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        try {
            int[] digitos = new int[11];
            for (int i = 0; i < 11; i++) {
                digitos[i] = Integer.parseInt(cpf.substring(i, i + 1));
            }

            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += digitos[i] * (10 - i);
            }
            int digitoVerificador1 = 11 - (soma % 11);
            if (digitoVerificador1 == 10 || digitoVerificador1 == 11) {
                digitoVerificador1 = 0;
            }
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += digitos[i] * (11 - i);
            }
            int digitoVerificador2 = 11 - (soma % 11);
            if (digitoVerificador2 == 10 || digitoVerificador2 == 11) {
                digitoVerificador2 = 0;
            }

            return digitoVerificador1 == digitos[9] && digitoVerificador2 == digitos[10];
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String exibiPessoa() {
        return "Nome: " + nome + ", Sobrenome: " + sobrenome + ", Idade: " + getIdade() + 
        ", CPF: " + cpf + ".";
    }
}
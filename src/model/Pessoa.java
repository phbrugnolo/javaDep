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

    public Pessoa() {}

    public static Pessoa criarPessoa(String nome, String sobrenome, LocalDate dataNasc, String cpf) {
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
        this.cpf = cpf;
    }

    public String exibiPessoa() {
        return "Nome: " + nome + ", Sobrenome: " + sobrenome + ", Idade: " + getIdade() + 
        ", CPF: " + cpf + ".";
    }
}
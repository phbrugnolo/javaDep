package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Pessoa implements Serializable{
    private String nome;
    private String sobrenome;
    private LocalDate dataNasc;
    private String endereco;
    private String cpf;
    private boolean trabalhando;

    public Pessoa(String nome, String sobrenome, LocalDate dataNasc, String endereco, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.cpf = cpf;
        this.trabalhando = false;
    }

    public static Pessoa criarPessoa(String nome, String sobrenome, LocalDate dataNasc, String endereco, String cpf) {
        return new Pessoa(nome, sobrenome, dataNasc, endereco, cpf);
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isTrabalhando() {
        return trabalhando;
    }

    public void setTrabalhando(boolean trabalhando) {
        this.trabalhando = trabalhando;
    }

    public String exibiPessoa() {
        return "Nome: " + nome + ", Sobrenome: " + sobrenome + ", Idade: " + getIdade() + ", Endereço: " + endereco
                + ", CPF: " + cpf + ", Status de Trabalho: " + (trabalhando? "Ativo" : "Inativo") + ".";
    }
}
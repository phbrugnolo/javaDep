package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import util.FormataData;

public class Pessoa implements Serializable{
    private static final long serialVersionUID = 1L;

    private String nome;
    private String sobrenome;
    private String dataNascimentoStr;
    private LocalDate dataNascimento;
    private String cpf;
    private String cnpj;
    private boolean isCnpj;

    public Pessoa(String nome, String sobrenome, String dataNascimentoStr, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimentoStr = dataNascimentoStr;
        this.dataNascimento = FormataData.parseData(dataNascimentoStr);
        this.cpf = cpf;
        this.cnpj = null;
        this.isCnpj = false;
    }

    public Pessoa(String nome, String sobrenome, String dataNascimentoStr, String cnpj, boolean isCnpj) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimentoStr = dataNascimentoStr;
        this.dataNascimento = FormataData.parseData(dataNascimentoStr);
        this.cpf = null;
        this.cnpj = cnpj;
        this.isCnpj = isCnpj;
    }
    
    public Pessoa() {}

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento() {
        this.dataNascimento = FormataData.parseData(getDataNascimentoStr());
    }
    
    public String getDataNascimentoStr() {
        return dataNascimentoStr;
    }

    public void setDataNascimentoStr(String dataNascimentoStr) {
        this.dataNascimentoStr = dataNascimentoStr;
    }

    public int getIdade() {
        if(dataNascimento == null) return 0;
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String exibiPessoa() {
        return "Nome: " + nome + ", Sobrenome: " + sobrenome + ", Idade: " + getIdade() + (isCnpj ? ", CNPJ: " + cnpj : ", CPF: " + cpf) + ".";
    }
}
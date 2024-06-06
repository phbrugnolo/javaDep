package model;

import java.io.Serializable;
import java.util.List;

public class Departamento implements Serializable{
    
    private String nome;
    private List<Funcionario> funcionarios;

    public Departamento(String nome, List<Funcionario> funcionarios){
        this.nome = nome;
        this.funcionarios = funcionarios;
    }

    public static Departamento criarDepartamento(String nome, List<Funcionario> funcionarios){
        return new Departamento(nome, funcionarios);
    }

    
    public Departamento(String nome){
        this.nome = nome;

    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public List<Funcionario> getFuncionarios(){
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios){
        this.funcionarios = funcionarios;
    }

    @Override
    public String toString(){
        return "Departamento: " + nome + "\nFuncionarios: " + funcionarios;
    }
    
}

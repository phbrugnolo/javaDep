package model;

import java.io.Serializable;
import java.util.List;
import controller.FuncionarioController;

public class Departamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private FuncionarioController funcionarioController;

    public Departamento(String nome, FuncionarioController funcionarioController) {
        this.nome = nome;
        this.funcionarioController = funcionarioController;
    }

    public static Departamento criarDepartamento(String nome, FuncionarioController funcionarioController) {
        return new Departamento(nome, funcionarioController);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarioController.getFuncionarios();
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        funcionarioController.setFuncionarios(funcionarios);
    }

    public void adicionarFuncionario(Funcionario funcionario) throws Exception {
        funcionarioController.adicionaFuncionario(funcionario);
    }

    public String nomeDoDepartamento() {
        return nome;
    }
}

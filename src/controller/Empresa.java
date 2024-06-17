package controller;

import model.*;

public class Empresa {
    private String nome;
    private DepartamentoController departamentoController;
    private FuncionarioController funcionarioController;
    private FornecedorController fornecedorController;

    public Empresa(String nome, DepartamentoController departamentoController,
            FuncionarioController funcionarioController, FornecedorController fornecedorController) {
        this.nome = nome;
        this.departamentoController = departamentoController;
        this.funcionarioController = funcionarioController;
        this.fornecedorController = fornecedorController;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarFuncionario(Funcionario funcionario, Departamento departamento) throws Exception{
       Departamento d =  departamentoController.buscaDepartamento(nome).orElseThrow(() -> new Exception("Departamento não encontrado"));
        d.adicionarFuncionario(funcionario);
    }

    public String listarDepartamentos() {
        StringBuilder sb = new StringBuilder("Departamentos:\n");
        for (Departamento d : departamentoController.getDepartamentos()) {
            sb.append(d).append("\n");
        }
        return sb.toString();
    }

    public String listarFuncionarios() {
        StringBuilder sb = new StringBuilder("Funcionários:\n");
        for (Funcionario f : funcionarioController.getFuncionarios()) {
            sb.append(f.exibiPessoa()).append("\n");
        }
        return sb.toString();
    }

    public String listarFornecedores() {
        StringBuilder sb = new StringBuilder("Fornecedores:\n");
        for (Fornecedor f : fornecedorController.getFornecedores()) {
            sb.append(f.exibiPessoa()).append("\n");
        }
        return sb.toString();
    }

    public String listaGeral() {
        return "Empresa: " + nome + "\n" +
               listarDepartamentos() + "\n" +
               listarFuncionarios() + "\n" +
               listarFornecedores() + "\n";
    }
}

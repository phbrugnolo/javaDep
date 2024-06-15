package controller;

// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;

import model.*;


public class Empresa {
    private String nome;
    private DepartamentoController departamentoController;
    private FuncionarioController funcionarioController;
    private PessoaController pessoaController;

    public Empresa(String nome, DepartamentoController departamentoController,
            FuncionarioController funcionarioController, PessoaController pessoaController) {
        this.nome = nome;
        this.departamentoController = departamentoController;
        this.funcionarioController = funcionarioController;
        this.pessoaController = pessoaController;
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


    // public void relatorioFolhaSalarial() {
    //     List<Funcionario> sortedFuncionarios = new ArrayList<>();
    //     sortedFuncionarios.addAll(funcionarios);
    //     Collections.sort(sortedFuncionarios);

    //     System.out.println("Relatório de Folha Salarial:");
    //     for (Funcionario f : sortedFuncionarios) {
    //         System.out.println(f.exibiPessoa());
    //     }
    // }

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

    public String listarPessoas() {
        StringBuilder sb = new StringBuilder("Pessoas:\n");
        for (Pessoa p : pessoaController.getPessoas()) {
            sb.append(p.exibiPessoa()).append("\n");
        }
        return sb.toString();
    }

    
    public String listaGeral() {
        return "Empresa: " + nome + "\n" +
               listarDepartamentos() + "\n" +
               listarFuncionarios() + "\n" +
               listarPessoas();
    }

    
}

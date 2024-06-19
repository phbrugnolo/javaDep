package controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.*;

public class Empresa implements IEmpresa {
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

    @Override
    public String listarDepartamentos() {
        StringBuilder sb = new StringBuilder("Departamentos:\n");
        for (Departamento d : departamentoController.getDepartamentos()) {
            sb.append(d).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String listarFuncionarios() {
        StringBuilder sb = new StringBuilder("Funcionários:\n");
        for (Funcionario f : funcionarioController.getFuncionarios()) {
            sb.append(f.exibiPessoa()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String listarFornecedores() {
        StringBuilder sb = new StringBuilder("Fornecedores:\n");
        for (Fornecedor f : fornecedorController.getFornecedores()) {
            sb.append(f.exibiPessoa()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String listaGeral() {
        return "Empresa: " + nome + "\n" +
                listarDepartamentos() + "\n" +
                listarFuncionarios() + "\n" +
                listarFornecedores() + "\n";
    }

    @Override
    public String calcularFolhaSalarial() {
        StringBuilder sb = new StringBuilder("Relatório de Folha Salarial:\n");

        List<Funcionario> funcionarios = funcionarioController.getFuncionarios();
        if (funcionarios.isEmpty()) {
            sb.append("Não há funcionários.\n");
        } else {
            double folhaSalarialTotal = funcionarios.stream()
                    .mapToDouble(Funcionario::getSalario)
                    .sum();
            sb.append("Folha Salarial Total: ").append(folhaSalarialTotal).append("\n");

            List<Funcionario> topSalarios = funcionarios.stream()
                    .sorted()
                    .limit(5)
                    .collect(Collectors.toList());

            sb.append("Top 5 Salários:\n");
            for (Funcionario f : topSalarios) {
                sb.append(f.exibiPessoa()).append("\n");
            }
        }
        return sb.toString();
    }
    
    @Override
    public String listarFuncionariosEmDepartamento(String departamentoNome) {
        Optional<Departamento> departamentoBuscado = departamentoController.buscaDepartamento(departamentoNome);
        
        if (departamentoBuscado.isPresent()) {
            Departamento departamento = departamentoBuscado.get();
            StringBuilder sb = new StringBuilder("Funcionários do Departamento " + departamentoNome + ":\n");
            
            for (Funcionario f : departamento.getFuncionarios()) {
                sb.append(f.exibiPessoa()).append("\n");
            }
            
            return sb.toString();
        } else {
            return "Departamento " + departamentoNome + " não encontrado.\n";
        }
    }
    
}

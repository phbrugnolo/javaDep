package controller;

import java.util.ArrayList;
import java.util.List;
import models.*;

public class Empresa {
    private List<Pessoa> pessoas = new ArrayList<>();
    private List<Funcionario> funcionarios = new ArrayList<>();
    private List<Departamento> departamentos = new ArrayList<>();

    public Pessoa buscaPessoa(String nome) throws Exception {
        return pessoas.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() -> new Exception("Pessoa não encontrada"));
    }

    public void adicionaPessoa(Pessoa pessoa) throws Exception {
        try {
            Pessoa existente = buscaPessoa(pessoa.getNome());
            throw new Exception("Pessoa já cadastrada no sistema");
        } catch (Exception e) {
            if (e.getMessage().equals("Pessoa não encontrada")) {
                pessoas.add(pessoa);
            } else {
                throw e;
            }
        }
    }

    public void removePessoa(String nome) throws Exception {
        Pessoa pessoa = buscaPessoa(nome);
        pessoas.remove(pessoa);
    }

    public List<Pessoa> listaPessoas() {
        return pessoas;
    }

    public void adicionaFuncionario(Funcionario funcionario) throws Exception {
        try {
            Funcionario existente = buscaFuncionario(funcionario.getId());
            throw new Exception("Funcionario já cadastrado no sistema");
        } catch (Exception e) {
            if (e.getMessage().equals("Nao existe um funcionario com este ID.")) {
                funcionarios.add(funcionario);
            } else {
                throw e;
            }
        }
    }

    public void removeFuncionario(Funcionario funcionario) throws Exception {
        if (funcionario != null) {
            funcionarios.remove(funcionario);
        } else {
            throw new Exception("Funcionário não encontrado");
        }
    }

    public List<Funcionario> listaFuncionarios() {
        return funcionarios;
    }

    public Funcionario buscaFuncionario(String nome) throws Exception {
        return funcionarios.stream().filter(f -> f.getNome().equals(nome)).findFirst().orElseThrow(() -> new Exception("Nao existe um funcionario com este nome."));
    }

    public Funcionario buscaFuncionario(int id) throws Exception {
        return funcionarios.stream().filter(f -> f.getId() == id).findFirst().orElseThrow(() -> new Exception("Nao existe um funcionario com este ID."));
    }

    public Departamento buscaDepartamento(String nome) throws Exception {
        return departamentos.stream()
                .filter(d -> d.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() -> new Exception("Departamento não encontrado"));
    }

    public void adicionaDepartamento(Departamento departamento) throws Exception {
        try {
            Departamento existente = buscaDepartamento(departamento.getNome());
            throw new Exception("Departamento já cadastrado no sistema");
        } catch (Exception e) {
            if (e.getMessage().equals("Departamento não encontrado")) {
                departamentos.add(departamento);
            } else {
                throw e;
            }
        }
    }

    public void removeDepartamento(String nome) throws Exception {
        Departamento departamento = buscaDepartamento(nome);
        departamentos.remove(departamento);
    }

    public List<Departamento> listaDepartamentos() {
        return departamentos;
    }

    public Funcionario criaFuncionarioSePessoaExistir(String nomePessoa, String cargo, double salario, String email) throws Exception {
        Pessoa pessoa = buscaPessoa(nomePessoa);
        Funcionario funcionario = new Funcionario(pessoa.getNome(), pessoa.getSobrenome(), pessoa.getIdade(), pessoa.getEndereco(), pessoa.getCpf(), cargo, salario, email);
        adicionaFuncionario(funcionario);
        return funcionario;
    }
}

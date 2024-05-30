package controller;

import java.util.List;
import models.*;

public class Empresa {
    List<Departamento> departamentos;
    List<Funcionario> funcionarios;
    List<Pessoa> pessoas;

    public Pessoa buscaPessoa(String nome) throws Exception {
        return pessoas.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() -> new Exception("Pessoa não encontrada"));
    }

    // No bloco do Try ele verifica se a pessoa já existe, se sim, ele lança uma exceção, no catch ele verifica se a exceção é de pessoa não encontrada, se sim, ele adiciona a pessoa, se não, ele lança outra exceção.
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
        }else {
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
}

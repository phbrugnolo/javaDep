package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import model.Pessoa;
import util.*;

public class PessoaController {

    private List<Pessoa> pessoas;

    public PessoaController(List<Pessoa> pessoas) {
        this.pessoas = pessoas;

        try {
            carregarDados();
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados");
        }
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Optional<Pessoa> buscaPessoa(String nome){
        return pessoas.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public void editaPessoa(String nome, String novoNome, String novoSobrenome, LocalDate novaIdade, String novoEndereco, String novoCpf)
            throws Exception {
        Pessoa p = buscaPessoa(nome).orElse(null);
        if (p != null) {
            p.setNome(novoNome);
            p.setSobrenome(novoSobrenome);
            p.setDataNasc(novaIdade);
            p.setEndereco(novoEndereco);
            p.setCpf(novoCpf);
            Log.logAction("Pessoa " + p.getNome() + " editada com sucesso");
        }
        throw new Exception("Pessoa não encontrada");
    }

    public void adicionaPessoa(Pessoa pessoa) throws Exception {
        Pessoa p = buscaPessoa(pessoa.getNome()).orElse(null);
        if (p == null) {
            pessoas.add(pessoa);
            Log.logAction("Pessoa " + pessoa.getNome() + " cadastrada com sucesso");
        }
        throw new Exception("Pessoa já cadastrada no sistema");
    }

    public void removePessoa(String nome) throws Exception {
        Pessoa p = buscaPessoa(nome).orElse(null);
        if (p == null) {
            throw new Exception("Pessoa não encontrada");
        }
        Log.logAction("Pessoa " + p.getNome() + " removida com sucesso");
        pessoas.remove(p);
    }

    public void salvarDados() throws Exception {
        Ser.salvarPessoa(pessoas);
    }

    private void carregarDados() throws Exception {
        pessoas = Ser.lerPessoas();
    }

}

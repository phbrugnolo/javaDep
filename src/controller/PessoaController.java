package controller;

import java.util.List;
import java.util.Optional;

import model.Pessoa;
import util.Ser;

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

    public Optional<Pessoa> buscaPessoa(String nome) throws Exception {
        return pessoas.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public void editaPessoa(String nome, String novoNome, String novoSobrenome, int novaIdade, String novoEndereco)
            throws Exception {
        Pessoa p = buscaPessoa(nome).orElse(null);
        if (p != null) {
            p.setNome(novoNome);
            p.setSobrenome(novoSobrenome);
            p.setIdade(novaIdade);
            p.setEndereco(novoEndereco);
        } else {
            throw new Exception("Pessoa não encontrada");
        }
    }

    public void adicionaPessoa(Pessoa pessoa) throws Exception {
        Pessoa p = buscaPessoa(pessoa.getNome()).orElse(null);
        if (p == null) {
            pessoas.add(pessoa);
        } else {
            throw new Exception("Pessoa já cadastrada no sistema");
        }
    }

    public void removePessoa(String nome) throws Exception {
        Pessoa p = buscaPessoa(nome).orElse(null);
        if (p == null) {
            throw new Exception("Pessoa não encontrada");
        } else {
            pessoas.remove(p);
        }
    }

    public void salvarDados() throws Exception {
        Ser.salvarPessoa(pessoas);
    }

    private void carregarDados() throws Exception {
        pessoas = Ser.lerPessoas();
    }

}

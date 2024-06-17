package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import model.Fornecedor;
import util.*;

public class FornecedorController {

    private List<Fornecedor> fornecedores;

    public FornecedorController() {
        this.fornecedores = CriarLista.criarListaFornecedor();

        try {
            carregarDados();
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados");
        }
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public Optional<Fornecedor> buscaFornecedor(String nome){
        return fornecedores.stream()
                .filter(f -> f.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public void editaFornecedor(String nome, String novoNome, String novoSobrenome, LocalDate novaDataNasc, String novoEndereco, String novoCpf, String novaEmpresa)
            throws Exception {
        Fornecedor f = buscaFornecedor(nome).orElseThrow(() -> new Exception("Fornecedor não encontrado"));
        f.setNome(novoNome);
        f.setSobrenome(novoSobrenome);
        f.setDataNasc(novaDataNasc);
        f.setEndereco(novoEndereco);
        f.setCpf(novoCpf);
        f.setEmpresa(novaEmpresa);
        Log.logAction("Fornecedor " + f.getNome() + " editado com sucesso");
        salvarDados();
    }

    public void adicionaFornecedor(Fornecedor fornecedor) throws Exception {
        if (buscaFornecedor(fornecedor.getNome()).isPresent()) {
            throw new Exception("Fornecedor já cadastrado no sistema");
        }
        fornecedores.add(fornecedor);
        Log.logAction("Fornecedor " + fornecedor.getNome() + " cadastrado com sucesso");
        salvarDados();
    }

    public void removeFornecedor(String nome) throws Exception {
        Fornecedor f = buscaFornecedor(nome).orElseThrow(() -> new Exception("Fornecedor não encontrado"));
        fornecedores.remove(f);
        Log.logAction("Fornecedor " + f.getNome() + " removido com sucesso");
        salvarDados();
    }

    public void salvarDados() throws Exception {
        Ser.salvarFornecedor(fornecedores);
    }

    private void carregarDados() throws Exception {
        fornecedores = Ser.lerFornecedores();
    }
}

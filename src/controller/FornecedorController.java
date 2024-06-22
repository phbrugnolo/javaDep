package controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import model.Fornecedor;
import util.*;

public class FornecedorController implements Serializable{
    private static final long serialVersionUID = 1L;

    private List<Fornecedor> fornecedores;

    public FornecedorController(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
        try {
            carregarDados();
        } catch (Exception e) {
            System.err.println("ERRO AO CARREGAR DADOS");
        }
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    private int criarId() {
        return fornecedores.stream().mapToInt(Fornecedor::getId).max().orElse(0) + 1;
    }

    public Optional<Fornecedor> buscaFornecedor(String nome){
        return fornecedores.stream()
                .filter(fornecedor -> fornecedor.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public void editaFornecedor(String nome, String novoNome, String novoSobrenome, LocalDate novaDataNasc, String novoCpf, String novaEmpresa) throws Exception {
        Fornecedor fornecedor = buscaFornecedor(nome).orElseThrow(() -> new Exception("Fornecedor não encontrado"));
        fornecedor.setNome(novoNome);
        fornecedor.setSobrenome(novoSobrenome);
        fornecedor.setDataNasc(novaDataNasc);
        fornecedor.setCpf(novoCpf);
        fornecedor.setNomeEmpresa(novaEmpresa);
        Log.escreverNoLog("Fornecedor " + fornecedor.getNome() + " editado com sucesso");
        salvarDados();
    }

    public void adicionaFornecedor(Fornecedor fornecedor) throws Exception {
        if (buscaFornecedor(fornecedor.getNome()).isPresent()) throw new Exception("Fornecedor já cadastrado no sistema");

        fornecedores.add(fornecedor);
        fornecedor.setId(criarId());
        Log.escreverNoLog("Fornecedor " + fornecedor.getNome() + " cadastrado com sucesso");
        salvarDados();
    }

    public void removeFornecedor(String nome) throws Exception {
        Fornecedor fornecedor = buscaFornecedor(nome).orElseThrow(() -> new Exception("Fornecedor não encontrado"));
        fornecedores.remove(fornecedor);
        Log.escreverNoLog("Fornecedor " + fornecedor.getNome() + " removido com sucesso");
        salvarDados();
    }

    public void registrarFornecimento(String nomeFornecedor, List<String> produtos) throws Exception {
        Fornecedor fornecedor = buscaFornecedor(nomeFornecedor).orElseThrow(() -> new Exception("Fornecedor não encontrado"));
        fornecedor.registrarFornecimento(produtos);
        Log.escreverNoLog("Fornecimento registrado para o fornecedor " + fornecedor.getNome() + " com produtos: " + produtos);
        salvarDados();
    }

    public void salvarDados() throws Exception {
        Ser.salvarFornecedor(fornecedores);
    }

    private void carregarDados() {
        try {
            fornecedores = Ser.lerFornecedores();
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}

package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
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
            System.err.println("Erro ao carregar os dados: " + e.getMessage());
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

    public void editaFornecedor(String nome, String novoNome, String novoSobrenome, String novaDataNascimentoStr, String novoCnpj, String novaEmpresa) throws Exception {
        Fornecedor fornecedor = buscaFornecedor(nome).orElseThrow(() -> new NoSuchElementException("Fornecedor não encontrado"));

        if(novoNome == null || novoNome.trim().isEmpty()) throw new IllegalArgumentException("Nome do fornecedor não pode ser vazio.");
        if(novoSobrenome == null || novoSobrenome.trim().isEmpty()) throw new IllegalArgumentException("Sobrenome do fornecedor não pode ser vazio.");
        if(novaDataNascimentoStr == null || novaDataNascimentoStr.trim().isEmpty()) throw new IllegalArgumentException("Data de nascimento do fornecedor não pode ser vazia.");
        if(novoCnpj == null || novoCnpj.trim().isEmpty()) throw new IllegalArgumentException("CNPJ do fornecedor não pode ser vazio.");
        if(novaEmpresa == null || novaEmpresa.trim().isEmpty()) throw new IllegalArgumentException("Nome da empresa do fornecedor não pode ser vazio.");
        if(!ValidarCpfCnpj.validarCNPJ(novoCnpj.trim())) throw new IllegalArgumentException("CNPJ inválido.");
        if(!FormataData.validarFormato(novaDataNascimentoStr)) 

        fornecedor.setNome(novoNome);
        fornecedor.setSobrenome(novoSobrenome);
        fornecedor.setDataNascimentoStr(novaDataNascimentoStr);
        fornecedor.setCnpj(novoCnpj);
        fornecedor.setDataNascimento();
        fornecedor.setNomeEmpresa(novaEmpresa);

        Log.escreverNoLog("Fornecedor " + fornecedor.getNome() + " editado com sucesso");
        salvarDados();
    }

    public void adicionaFornecedor(Fornecedor fornecedor) throws Exception {
        if(fornecedor.getNome() == null || fornecedor.getNome().trim().isEmpty()) throw new IllegalArgumentException("Nome do fornecedor não pode ser vazio.");
        if(fornecedor.getSobrenome() == null || fornecedor.getSobrenome().trim().isEmpty()) throw new IllegalArgumentException("Sobrenome do fornecedor não pode ser vazio.");
        if(fornecedor.getDataNascimentoStr() == null || fornecedor.getDataNascimentoStr().trim().isEmpty()) throw new IllegalArgumentException("Data de nascimento do fornecedor não pode ser vazia.");
        if(fornecedor.getCnpj() == null || fornecedor.getCnpj().trim().isEmpty()) throw new IllegalArgumentException("CNPJ do fornecedor não pode ser vazio.");
        if(fornecedor.getNomeEmpresa() == null || fornecedor.getNomeEmpresa().trim().isEmpty()) throw new IllegalArgumentException("Nome da empresa do fornecedor não pode ser vazio.");
        if(!ValidarCpfCnpj.validarCNPJ(fornecedor.getCnpj().trim()))
        if(!FormataData.validarFormato(fornecedor.getDataNascimentoStr()))
        if(buscaFornecedor(fornecedor.getNome()).isPresent()) throw new IllegalArgumentException("Fornecedor já cadastrado no sistema");

        fornecedores.add(fornecedor);
        fornecedor.setId(criarId());
        Log.escreverNoLog("Fornecedor " + fornecedor.getNome() + " cadastrado com sucesso");
        salvarDados();
    }

    public void removeFornecedor(String nome) throws Exception {
        Fornecedor fornecedor = buscaFornecedor(nome).orElseThrow(() -> new NoSuchElementException("Fornecedor não encontrado"));
        fornecedores.remove(fornecedor);
        Log.escreverNoLog("Fornecedor " + fornecedor.getNome() + " removido com sucesso");
        salvarDados();
    }

    public void registrarFornecimento(String nomeFornecedor, List<String> produtos) throws Exception {
        if (nomeFornecedor == null || nomeFornecedor.isEmpty()) {
            throw new IllegalArgumentException("Nome do fornecedor não pode ser vazio.");
        }

        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("Lista de produtos não pode ser vazia.");
        }

        produtos.stream()
            .filter(produto -> produto.trim().isEmpty())
            .findAny()
            .ifPresent(produto -> {
                throw new IllegalArgumentException("Produto inválido na lista.");
            });

        Fornecedor fornecedor = buscaFornecedor(nomeFornecedor)
            .orElseThrow(() -> new NoSuchElementException("Fornecedor não encontrado"));
        
        fornecedor.registrarFornecimento(produtos);
        Log.escreverNoLog(String.format("Fornecedor %s forneceu os seguintes produtos: %s", fornecedor.getNome(), String.join(", ", produtos)));
        salvarDados();
    }
    

    public void salvarDados() throws Exception {
        Ser.salvarFornecedor(fornecedores);
    }

    private void carregarDados() {
        try {
            fornecedores = Ser.lerFornecedores();
        } catch (InvalidFileException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}

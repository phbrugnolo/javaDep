package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.FormataData;

public class Fornecedor extends Pessoa {
    private static final long serialVersionUID = 1L;

    private String nomeEmpresa;	
    private int id;
    private List<String> produtosFornecidos;
    private LocalDate dataUltimoFornecimento;

    public Fornecedor(String nome, String sobrenome, String dataNascimentoStr, String cnpj, boolean isCnpj, String nomeEmpresa) {
        super(nome, sobrenome, dataNascimentoStr, cnpj, isCnpj);
        this.nomeEmpresa = nomeEmpresa;
        this.produtosFornecidos = new ArrayList<>();
    }

    public Fornecedor() {
        super();
    }

    public static Fornecedor criarFornecedor(String nome, String sobrenome, String dataNascimentoStr, String cnpj, String nomeEmpresa) {
        return new Fornecedor(nome, sobrenome, dataNascimentoStr, cnpj, true, nomeEmpresa);
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public List<String> getProdutosFornecidos() {
        return produtosFornecidos;
    }

    public void adicionarProdutoFornecido(String produto) {
        this.produtosFornecidos.add(produto);
    }

    public LocalDate getDataUltimoFornecimento() {
        return dataUltimoFornecimento;
    }

    public void setDataUltimoFornecimento(LocalDate dataUltimoFornecimento) {
        this.dataUltimoFornecimento = dataUltimoFornecimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void registrarFornecimento(List<String> novosProdutos) {
        this.produtosFornecidos.addAll(novosProdutos);
        this.dataUltimoFornecimento = LocalDate.now();
    }

    @Override
    public String exibiPessoa() {
        return "ID: " + id + " " + super.exibiPessoa() + " Empresa: " + nomeEmpresa + ", Produtos Fornecidos: " + (produtosFornecidos == null || produtosFornecidos.isEmpty() ? "Nenhum produto Fornecido" : produtosFornecidos)  + ", Data do Último Fornecimento: " + (dataUltimoFornecimento == null ? "Nenhuma data Constada" : FormataData.formatData(dataUltimoFornecimento)) + ".";
    }
}

package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fornecedor extends Pessoa {
    private String empresa;
    private List<String> produtosFornecidos;
    private LocalDate dataUltimoFornecimento;

    public Fornecedor(String nome, String sobrenome, LocalDate dataNasc, String endereco, String cpf, String empresa) {
        super(nome, sobrenome, dataNasc, endereco, cpf);
        this.empresa = empresa;
        this.produtosFornecidos = new ArrayList<>();
        this.dataUltimoFornecimento = LocalDate.now();
    }

    public static Fornecedor criarFornecedor(String nome, String sobrenome, LocalDate dataNasc, String endereco, String cpf, String empresa) {
        return new Fornecedor(nome, sobrenome, dataNasc, endereco, cpf, empresa);
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    @Override
    public String exibiPessoa() {
        return super.exibiPessoa() + " Empresa: " + empresa + ", Produtos Fornecidos: " + produtosFornecidos + ", Data do Último Fornecimento: " + dataUltimoFornecimento + ".";
    }
}

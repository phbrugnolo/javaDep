package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import model.Funcionario;
import util.*;

public class FuncionarioController {

    private List<Funcionario> funcionarios;

    public FuncionarioController() {
        this.funcionarios = CriarLista.criarListaFuncionario();
        carregarDados();
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    private int criarId() {
        return funcionarios.stream().mapToInt(Funcionario::getId).max().orElse(0) + 1;
    }

    public void adicionaFuncionario(Funcionario funcionario) throws Exception {
        if (buscaFuncionario(funcionario.getId()).isPresent()) throw new Exception("Funcionário já cadastrado no sistema");
        funcionario.setId(criarId());
        funcionarios.add(funcionario);
        Log.logAction("Funcionário cadastrado " + funcionario.getNome() + " com sucesso");
        salvarDados();
    }

    public void removeFuncionario(int id) throws Exception {
        Funcionario funcionario = buscaFuncionario(id).orElseThrow(() -> new Exception("Funcionário não encontrado"));
        funcionarios.remove(funcionario);      
        Log.logAction("Funcionário removido " + funcionario.getNome() + " com sucesso");
        salvarDados();
    }

    public void editaFuncionario(String nome, String novoNome, String novoSobrenome, LocalDate novaDataNasc, String novoCpf, String novoCargo, double novoSalario)
            throws Exception {
        Funcionario funcionario = buscaFuncionario(nome).orElseThrow(() -> new Exception("Funcionário não encontrado"));
        funcionario.setNome(novoNome);
        funcionario.setSobrenome(novoSobrenome);
        funcionario.setDataNasc(novaDataNasc);
        funcionario.setCpf(novoCpf);
        funcionario.setCargo(novoCargo);
        funcionario.setSalario(novoSalario);
        Log.logAction("Funcionário editado " + funcionario.getNome() + " com sucesso");
        salvarDados();
    }

    public Optional<Funcionario> buscaFuncionario(String nome) {
        return funcionarios.stream().filter(funcionario -> funcionario.getNome().equalsIgnoreCase(nome)).findFirst();
    }

    public Optional<Funcionario> buscaFuncionario(int id) {
        return funcionarios.stream().filter(funcionario -> funcionario.getId() == id).findFirst();
    }

    public void salvarDados() throws Exception {
        Ser.salvarFuncionario(funcionarios);
    }

    private void carregarDados() {
        try {
            funcionarios = Ser.lerFuncionarios();
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados");
        }
    }
}

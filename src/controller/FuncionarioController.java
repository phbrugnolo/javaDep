package controller;

import java.util.List;
import java.util.Optional;

import model.Funcionario;
import util.*;

public class FuncionarioController {

    private List<Funcionario> funcionarios;

    public FuncionarioController() {
        this.funcionarios = CriarLista.criarListaFuncionario();

        try {
            carregarDados();
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados");
        }
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
        buscaFuncionario(funcionario.getId()).orElseThrow(() -> new Exception("Funcionário já cadastrado no sistema"));
        funcionario.setId(criarId());
        funcionarios.add(funcionario);
        Log.logAction("Funcionário cadastrado " + funcionario.getNome() + " com sucesso");
        salvarDados();

    }

    public void removeFuncionario(int id) throws Exception {
        Funcionario f = buscaFuncionario(id).orElseThrow(() -> new Exception("Funcionário não encontrado"));
        funcionarios.remove(f);      
        Log.logAction("Funcionário removido " + f.getNome() + " com sucesso");
        salvarDados();
    }

    public void editaFuncionario(int id, double novoSalario, String novoCargo) throws Exception {
        Funcionario f = buscaFuncionario(id).orElseThrow(() -> new Exception("Funcionário não encontrado"));
        f.setSalario(novoSalario);
        f.setCargo(novoCargo);
        Log.logAction("Funcionário editado " + f.getNome() + " com sucesso");
        salvarDados();

    }

    public Optional<Funcionario> buscaFuncionario(String nome) throws Exception {
        return funcionarios.stream().filter(f -> f.getNome().equalsIgnoreCase(nome)).findFirst();
    }

    public Optional<Funcionario> buscaFuncionario(int id) throws Exception {
        return funcionarios.stream().filter(f -> f.getId() == id).findFirst();
    }
    

    public void salvarDados() throws Exception {
        Ser.salvarFuncionario(funcionarios);
    }

    private void carregarDados() throws Exception {
        funcionarios = Ser.lerFuncionarios();
    }

}

package controller;

import java.util.List;
import java.util.Optional;

import model.Funcionario;
import util.Ser;

public class FuncionarioController {

    private List<Funcionario> funcionarios;

    public FuncionarioController(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;

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
        Funcionario f = buscaFuncionario(funcionario.getId()).orElse(null);
        if(f == null){
            funcionario.setId(criarId());
            funcionarios.add(funcionario);
        }else {
            throw new Exception("Funcionário já cadastrado no sistema");
        }
    }

    public void removeFuncionario(int id) throws Exception {
        Funcionario f = buscaFuncionario(id).orElse(null);
        if(f == null){
            throw new Exception("Funcionário não encontrado");
        }else {
            funcionarios.remove(f);      
        }
  
    }

    public void editaFuncionario(int id, double novoSalario, String novoCargo) throws Exception {
        Funcionario f = buscaFuncionario(id).orElse(null);
        if(f != null){
            f.setSalario(novoSalario);
            f.setCargo(novoCargo);
        }else {
            throw new Exception("Funcionário não encontrado");
        }
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

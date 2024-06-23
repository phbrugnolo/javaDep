package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import model.Departamento;
import model.Funcionario;
import util.*;

public class FuncionarioController implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Funcionario> funcionarios;
    private DepartamentoController departamentoController;

    public FuncionarioController(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
        try {
            carregarDados();
        } catch (Exception e) {
            System.err.println("ERRO AO CARREGAR DADOS");
        }
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void setDepartamentoController(DepartamentoController departamentoController) {
        this.departamentoController = departamentoController;
    }

    private int criarId() {
        return funcionarios.stream().mapToInt(Funcionario::getId).max().orElse(0) + 1;
    }

    public Optional<Funcionario> buscaFuncionario(String nome) {
        return funcionarios.stream()
                .filter(funcionario -> funcionario.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public Optional<Funcionario> buscaFuncionario(int id) {
        return funcionarios.stream()
                .filter(funcionario -> funcionario.getId() == id)
                .findFirst();
    }

    public void adicionaFuncionario(Funcionario funcionario) throws Exception {
        if(funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) throw new IllegalArgumentException("Nome do funcionário não pode ser vazio.");
        if(funcionario.getSobrenome() == null || funcionario.getSobrenome().trim().isEmpty()) throw new IllegalArgumentException("Sobrenome do funcionário não pode ser vazio.");
        if(funcionario.getDataNascimentoStr() == null || funcionario.getDataNascimentoStr().trim().isEmpty()) throw new IllegalArgumentException("Data de nascimento do funcionário não pode ser vazia.");
        if(funcionario.getCpf() == null || funcionario.getCpf().trim().isEmpty()) throw new IllegalArgumentException("CPF do funcionário não pode ser vazio.");
        if(funcionario.getCargo() == null || funcionario.getCargo().trim().isEmpty()) throw new IllegalArgumentException("Cargo do funcionário não pode ser vazio.");
        if(funcionario.getSalario() <= 0) throw new IllegalArgumentException("Salário do funcionário não pode ser menor ou igual a zero.");
        if(!FormataData.validarFormato(funcionario.getDataNascimentoStr()))
        if(!ValidarCpfCnpj.validarCPF(funcionario.getCpf().trim())) throw new IllegalArgumentException("CPF inválido.");
        if(buscaFuncionario(funcionario.getId()).isPresent()) throw new IllegalArgumentException("Funcionário já cadastrado no sistema");

        funcionario.setId(criarId());
        funcionarios.add(funcionario);
        Log.escreverNoLog("Funcionário cadastrado " + funcionario.getNome() + " com sucesso");
        salvarDados();
    }

    public void removeFuncionario(int id) throws Exception {
        Funcionario funcionario = buscaFuncionario(id).orElseThrow(() -> new NoSuchElementException("Funcionário não encontrado"));
        removerFuncionarioDoDepartamento(funcionario);
        funcionarios.remove(funcionario);
        Log.escreverNoLog("Funcionário removido " + funcionario.getNome() + " com sucesso");
        salvarDados();
    }

    private void removerFuncionarioDoDepartamento(Funcionario funcionario) throws Exception {
        for (Departamento departamento : departamentoController.getDepartamentos()) {
            if (departamento.getFuncionarios().contains(funcionario)) {
                departamento.getFuncionarios().remove(funcionario);
                break;
            }
        }
    }

    public void editaFuncionario(int id, String novoNome, String novoSobrenome, String novaDataNascimentoStr, String novoCpf, String novoCargo, double novoSalario, String novoEmail) throws Exception {
        
        Funcionario funcionario = buscaFuncionario(id).orElseThrow(() -> new NoSuchElementException("Funcionário não encontrado"));

        if(novoNome == null || novoNome.trim().isEmpty()) throw new IllegalArgumentException("Nome do funcionário não pode ser vazio.");
        if(novoSobrenome == null || novoSobrenome.trim().isEmpty()) throw new IllegalArgumentException("Sobrenome do funcionário não pode ser vazio.");
        if(novaDataNascimentoStr == null || novaDataNascimentoStr.trim().isEmpty()) throw new IllegalArgumentException("Data de nascimento do funcionário não pode ser vazia.");
        if(novoCpf == null || novoCpf.trim().isEmpty()) throw new IllegalArgumentException("CNPJ do funcionário não pode ser vazio.");
        if(novoCargo == null || novoCargo.trim().isEmpty()) throw new IllegalArgumentException("Nome da empresa do funcionário não pode ser vazio.");
        if(novoSalario <= 0) throw new IllegalArgumentException("Salário do funcionário não pode ser menor ou igual a zero.");
        if(!FormataData.validarFormato(novaDataNascimentoStr))
        if(!ValidarCpfCnpj.validarCPF(novoCpf.trim())) throw new IllegalArgumentException("CPF inválido.");
     
        funcionario.setNome(novoNome);
        funcionario.setSobrenome(novoSobrenome);
        funcionario.setDataNascimentoStr(novaDataNascimentoStr);
        funcionario.setDataNascimento();
        funcionario.setCpf(novoCpf);
        funcionario.setEmail(novoEmail);
        funcionario.setCargo(novoCargo);
        funcionario.setSalario(novoSalario);

        Log.escreverNoLog("Funcionário editado " + funcionario.getNome() + " com sucesso");
        salvarDados();
    }

    public void salvarDados() throws Exception {
        Ser.salvarFuncionario(funcionarios);
    }

    private void carregarDados() {
        try {
            funcionarios = Ser.lerFuncionarios();
        } catch (InvalidFileException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }    
}

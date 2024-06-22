package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import model.Departamento;
import model.Funcionario;
import util.*;

public class DepartamentoController implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Departamento> departamentos;

    public DepartamentoController(List<Departamento> departamentos) {
        this.departamentos = departamentos;
        try {
            carregarDados();
        } catch (Exception e) {
            System.err.println("ERRO AO CARREGAR DADOS");
        }
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public Optional<Departamento> buscaDepartamento(String nome) {
        return departamentos.stream()
                .filter(departamento -> departamento.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public void adicionaDepartamento(Departamento departamento) throws Exception {
        if (departamento.getNome() == null || departamento.getNome().trim().isEmpty()) throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
        if (buscaDepartamento(departamento.getNome()).isPresent()) throw new IllegalArgumentException("Departamento já cadastrado");
        departamentos.add(departamento);
        Log.escreverNoLog("Departamento cadastrado " + departamento.getNome() + " com sucesso");
        salvarDados();
    }

    public void editaDepartamento(String nome, String novoNome) throws Exception {
        if (novoNome == null || novoNome.trim().isEmpty()) throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
        Departamento departamento = buscaDepartamento(nome).orElseThrow(() -> new NoSuchElementException("Departamento não encontrado"));
        departamento.setNome(novoNome);
        Log.escreverNoLog("Departamento editado " + departamento.getNome() + " com sucesso");
        salvarDados();
    }

    public void removeDepartamento(String nome) throws Exception {
        Departamento departamento = buscaDepartamento(nome).orElseThrow(() -> new NoSuchElementException("Departamento não encontrado"));
        departamentos.remove(departamento);
        Log.escreverNoLog("Departamento removido " + departamento.getNome() + " com sucesso");
        salvarDados();
    }

    public void adicionarFuncionario(Funcionario funcionario, String nomeDepartamento) throws Exception {
        Departamento departamento = buscaDepartamento(nomeDepartamento).orElseThrow(() -> new NoSuchElementException("Departamento não encontrado"));
        departamento.adicionarFuncionario(funcionario);
        Log.escreverNoLog(funcionario.getNome() + " adicionado no " + nomeDepartamento + " com sucesso");
        salvarDados();
    }

    public void salvarDados() throws Exception {
        Ser.salvarDepartamento(departamentos);
    }

    private void carregarDados() {
        try {
            departamentos = Ser.lerDepartamentos();
        }  catch (InvalidFileException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}

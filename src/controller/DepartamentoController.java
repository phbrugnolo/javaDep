package controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import model.Departamento;
import model.Funcionario;
import util.*;

public class DepartamentoController implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Departamento> departamentos;

    public DepartamentoController() {
        this.departamentos = CriarLista.criarListaDepartamento();
        carregarDados();
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
        if (buscaDepartamento(departamento.getNome()).isPresent()) throw new Exception("Departamento já cadastrado");

        departamentos.add(departamento);
        Log.escreverNoLog("Departamento cadastrado " + departamento.getNome() + " com sucesso");
        salvarDados();
    }

    public void editaDepartamento(String nome, String novoNome) throws Exception {
        Departamento departamento = buscaDepartamento(nome).orElseThrow(() -> new Exception("Departamento não encontrado"));
        departamento.setNome(novoNome);
        Log.escreverNoLog("Departamento editado " + departamento.getNome() + " com sucesso");
        salvarDados();
    }

    public void removeDepartamento(String nome) throws Exception {
        Departamento departamento = buscaDepartamento(nome).orElseThrow(() -> new Exception("Departamento não encontrado"));
        departamentos.remove(departamento);
        Log.escreverNoLog("Departamento removido " + departamento.getNome() + " com sucesso");
        salvarDados();
    }

    public void adicionarFuncionario(Funcionario funcionario, String nomeDepartamento) throws Exception {
        Departamento departamento = buscaDepartamento(nomeDepartamento).orElseThrow(() -> new Exception("Departamento não encontrado"));
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
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados");
        }
    }
}

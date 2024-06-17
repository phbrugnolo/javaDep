package controller;

import java.util.List;
import java.util.Optional;

import model.Departamento;
import model.Funcionario;
import util.*;

public class DepartamentoController {

    private List<Departamento> departamentos;

    public DepartamentoController() {
        this.departamentos = CriarLista.criarListaDepartamento();

        try {
            carregarDados();
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados");
        }
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public Optional<Departamento> buscaDepartamento(String nome) throws Exception {
        return departamentos.stream()
                .filter(departamento -> departamento.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public void adicionaDepartamento(Departamento departamento) throws Exception {
        Optional<Departamento> departamentoBuscado = buscaDepartamento(departamento.getNome());
        if(departamentoBuscado.isPresent()) throw new Exception("Departamento já cadastrado");
        departamentos.add(departamento);
        Log.logAction("Departamento cadastrado " + departamento.getNome() + " com sucesso");
        salvarDados();

    }

    public void editaDepartamento(String nome, String novoNome) throws Exception {
        Departamento departamento = buscaDepartamento(nome).orElseThrow(() -> new Exception("Departamento não encontrado"));
        departamento.setNome(novoNome);
        Log.logAction("Departamento editado " + departamento.getNome() + " com sucesso");
        salvarDados();
    }

    public void removeDepartamento(String nome) throws Exception {
        Departamento departamento = buscaDepartamento(nome).orElseThrow(() -> new Exception("Departamento não encontrado"));
        departamentos.remove(departamento);
        Log.logAction("Departamento removido " + departamento.getNome() + " com sucesso");
        salvarDados();
    }

    public void adicionarFuncionario(Funcionario funcionario, String nomeDepartamento) {
        try {
            Departamento departamento = buscaDepartamento(nomeDepartamento).orElseThrow(() -> new Exception("Departamento não encontrado"));
            departamento.adicionarFuncionario(funcionario);
            Log.logAction(funcionario.getNome() + " adicionado no " + nomeDepartamento +   " com sucesso");
            salvarDados();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void salvarDados() throws Exception {
        Ser.salvarDepartamento(departamentos);
    }

    private void carregarDados() throws Exception {
        departamentos = Ser.lerDepartamentos();
    }
}

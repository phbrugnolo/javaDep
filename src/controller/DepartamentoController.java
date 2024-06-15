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
                .filter(d -> d.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public void adicionaDepartamento(Departamento departamento) throws Exception {
        Optional<Departamento> departamentoBuscado = buscaDepartamento(departamento.getNome());
        if(departamentoBuscado.isPresent()) {
            throw new Exception("Departamento já cadastrado");
        }
        departamentos.add(departamento);
        Log.logAction("Departamento cadastrado " + departamento.getNome() + " com sucesso");
        salvarDados();

    }

    public void editaDepartamento(String nome, String novoNome) throws Exception {
        Departamento d = buscaDepartamento(nome).orElseThrow(() -> new Exception("Departamento não encontrado"));
        d.setNome(novoNome);
        Log.logAction("Departamento editado " + d.getNome() + " com sucesso");
        salvarDados();
    }

    public void removeDepartamento(String nome) throws Exception {
        Departamento d = buscaDepartamento(nome).orElseThrow(() -> new Exception("Departamento não encontrado"));
        departamentos.remove(d);
        Log.logAction("Departamento removido " + d.getNome() + " com sucesso");]
        salvarDados();
    }

    public void adicionarFuncionario(Funcionario funcionario, String nomeDepartamento) {
        try {
            Departamento departamento = buscaDepartamento(nomeDepartamento).orElseThrow(() -> new Exception("Departamento não encontrado"));
            departamento.adicionarFuncionario(funcionario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // public String listarFuncionarios() {
    //     StringBuilder sb = new StringBuilder("Funcionários do Departamento: " + nome + "\n");
    //     for (Funcionario f : funcionarioController.getFuncionarios()) {
    //         sb.append(f.exibiPessoa()).append("\n");
    //     }
    //     return sb.toString();
    // }

    public void salvarDados() throws Exception {
        Ser.salvarDepartamento(departamentos);
    }

    private void carregarDados() throws Exception {
        departamentos = Ser.lerDepartamentos();
    }
}

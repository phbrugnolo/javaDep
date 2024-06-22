package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Departamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private List<Funcionario> funcionarios;

    public Departamento(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public Departamento() {}

    public static Departamento criarDepartamento(String nome) {
        return new Departamento(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void adicionarFuncionario(Funcionario funcionario) throws Exception {

        funcionarios.stream()
            .filter(f -> f.getCpf().equals(funcionario.getCpf()) && f.getId() == funcionario.getId())
            .findFirst()
            .ifPresentOrElse(
                f -> { throw new RuntimeException("Funcionario ja cadastrado neste Departamento"); },
                () -> funcionarios.add(funcionario)
            );     
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "nome='" + nome + '\'' +
                ", funcionarios=" + funcionarios.size() +
                '}';
    }
}

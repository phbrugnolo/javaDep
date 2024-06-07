package model;

import java.time.LocalDate;

public class Funcionario extends Pessoa implements Comparable<Funcionario> {
    private String cargo;
    private double salario;
    private String email;
    private Departamento departamento;
    private int id;

    public Funcionario(String nome, String sobrenome, LocalDate dataNasc, String endereco, String cpf, String cargo,
            double salario, String email, Departamento departamento) {
        super(nome, sobrenome, dataNasc, endereco, cpf);
        this.cargo = cargo;
        this.salario = salario;
        this.email = email;
        this.departamento = departamento;
    }

    public static Funcionario criarFuncionario(String nome, String sobrenome, LocalDate dataNasc, String endereco, String cpf,
            String cargo, double salario, String email, Departamento departamento) {
        return new Funcionario(nome, sobrenome, dataNasc, endereco, cpf, cargo, salario, email, departamento);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String exibiPessoa() {
        return super.exibiPessoa() + " Email: " + email + ", Cargo: " + cargo + ", Salário: " + salario + ", ID: " + id
                + ".";
    }

    @Override
    public int compareTo(Funcionario o) {
        return Double.compare(o.salario, this.salario);
    }
}

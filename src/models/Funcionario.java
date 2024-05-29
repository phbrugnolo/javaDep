package models;

public class Funcionario extends Pessoa{

    private String cargo;
    private double salario;
    private int id;
    private static int nextId = 1;

    public Funcionario(String nome, String sobrenome, int idade, String endereco, String cpf, String cargo, double salario) {
        super(nome, sobrenome, idade, endereco, cpf);
        this.cargo = cargo;
        this.salario = salario;
        this.id = nextId++;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
}

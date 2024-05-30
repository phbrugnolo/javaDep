package models;

public class Funcionario extends Pessoa {

    private String cargo;
    private double salario;
    // private String email;
    private int id;
    private static int nextId = 1;

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

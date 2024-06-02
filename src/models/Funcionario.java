package models;

public class Funcionario extends Pessoa {
    private String cargo;
    private double salario;
    private String email;
    private int id;
    private static int nextId = 1;

    public Funcionario(String nome, String sobrenome, int idade, String endereco, String cpf, String cargo, double salario, String email) {
        super(nome, sobrenome, idade, endereco, cpf);
        this.cargo = cargo;
        this.salario = salario;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public static void setNextId(int nextId) {
        Funcionario.nextId = nextId;
    }

    @Override
    public String exibiPessoa() {
        return "Nome: " + getNome() + ", Sobrenome: " + getSobrenome() + ", Idade: " + getIdade() + ", Endereço: " + getEndereco()
                + ", CPF: " + getCpf() + ", Status de Trabalho: " + isTrabalhando() + ". Email: " + email + ", Cargo: " + cargo + ", Salário: " + salario + ", ID: " + id + ".";
    }
}

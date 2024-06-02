package views;

import java.util.Scanner;
import models.Funcionario;
import models.Pessoa;
import controller.Empresa;
import util.Log;

public class _Funcionario {

    public static void cadastroFuncionario(Empresa listaFuncionario, Scanner scanner){
        System.out.print("Digite o nome da Pessoa: ");
        String nome = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Salário: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();

        try {
            Pessoa p = listaFuncionario.buscaPessoa(nome);
            String email = p.getNome().toLowerCase() + "." + p.getSobrenome().toLowerCase() + "@" + listaFuncionario.getNome() + ".com";
            Funcionario newFuncionario = new Funcionario(p.getNome(), p.getSobrenome(), p.getIdade(), p.getEndereco(), p.getCpf(), cargo, salario, email);
            listaFuncionario.adicionaFuncionario(newFuncionario);
            System.out.println("Funcionário cadastrado com sucesso!");
            p.setTrabalhando(true);
            Log.logAction("Funcionário " + newFuncionario.getNome() + " cadastrado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buscaFuncionario(Empresa listaFuncionario, Scanner scanner) {
        System.out.println("Buscar Funcionário:");
        System.out.println("[1] Buscar por Nome");
        System.out.println("[2] Buscar por ID");
        System.out.print("Escolha uma opção: ");
        int buscaOption = scanner.nextInt();
        scanner.nextLine();
        Funcionario f = null;
        switch (buscaOption) {
            case 1:
                try {
                    System.out.print("Nome do funcionário: ");
                    String nome = scanner.nextLine();
                    f = listaFuncionario.buscaFuncionario(nome);
                    System.out.println("Funcionário encontrado: " + f.getNome() + "\n" + f);
                    Log.logAction("Funcionário " + f.getNome() + " buscado por nome com sucesso.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    System.out.print("ID do funcionário: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    f = listaFuncionario.buscaFuncionario(id);
                    System.out.println("Funcionário encontrado: " + f.getNome() + "\n" + f);
                    Log.logAction("Funcionário " + f.getNome() + " buscado por ID com sucesso.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public static void removeFuncionario(Empresa listaFuncionario, Scanner scanner) {
        System.out.print("Id do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            listaFuncionario.removeFuncionario(id);
            System.out.println("Funcionário removido com sucesso!");
            Funcionario f = listaFuncionario.buscaFuncionario(id);
            Log.logAction("Funcionário " + f.getNome() + " removido");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

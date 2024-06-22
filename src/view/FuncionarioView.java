package view;

import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import controller.*;
import model.*;

public class FuncionarioView {

    public static void cadastroFuncionario(FuncionarioController fController, DepartamentoController dController, Empresa empresa, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine().trim();
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        String dataNascimentoStr = scanner.nextLine().trim();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine().trim();
        System.out.print("Salário: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nome do Departamento: ");
        String nomeDepartamento = scanner.nextLine().trim();

        String email = nome.toLowerCase() + "." + sobrenome.toLowerCase() + "@" + empresa.getNome().toLowerCase() + ".com";
        
        try {
            Funcionario funcionario = Funcionario.criarFuncionario(nome, sobrenome, dataNascimentoStr, cpf, cargo, salario, email);
            fController.adicionaFuncionario(funcionario);
            dController.adicionarFuncionario(funcionario, nomeDepartamento);
            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ocorreu um erro ao cadastrar o funcionário: " + e.getMessage());
            return;
        } catch (DateTimeParseException e) {
            System.out.println("Ocorreu um erro ao cadastrar o funcionário: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao cadastrar o funcionário: " + e.getMessage());
        }
    }

    public static void buscaFuncionario(FuncionarioController fController, Scanner scanner) {
        System.out.println("Buscar Funcionário:");
        System.out.println("[1] Buscar por Nome");
        System.out.println("[2] Buscar por ID");
        System.out.print("Escolha uma opção: ");
        int buscaOption = scanner.nextInt();
        scanner.nextLine();
        switch (buscaOption) {
            case 1:
                System.out.print("Nome do funcionário: ");
                String nome = scanner.nextLine();
                try {
                    fController.buscaFuncionario(nome).ifPresentOrElse(
                        funcionario -> System.out.println("Funcionário encontrado: " + funcionario.exibiPessoa()),
                        () -> System.out.println("Funcionário não encontrado."));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                System.out.print("ID do funcionário: ");
                int id = scanner.nextInt();
                try {
                    scanner.nextLine();
                    fController.buscaFuncionario(id).ifPresentOrElse(
                        funcionario -> System.out.println("Funcionário encontrado: " + funcionario.exibiPessoa()),
                        () -> System.out.println("Funcionário não encontrado."));
                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public static void removeFuncionario(FuncionarioController fController, Scanner scanner) {
        System.out.print("Id do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            fController.removeFuncionario(id);
            System.out.println("Funcionário removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editaFuncionario(FuncionarioController fController,Empresa empresa, Scanner scanner) {
        System.out.print("Id do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine().trim();
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        String dataNascimentoStr = scanner.nextLine().trim();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine().trim();
        System.out.print("Salário: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();

        String email = nome.toLowerCase() + "." + sobrenome.toLowerCase() + "@" + empresa.getNome().toLowerCase() + ".com";

        try {
            fController.editaFuncionario(id, nome, sobrenome, dataNascimentoStr, cpf, cargo, salario, email);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

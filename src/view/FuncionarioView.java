package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

import controller.*;
import model.*;

public class FuncionarioView {

    public static void cadastroFuncionario(FuncionarioController fController, DepartamentoController dController,
            Empresa empresa, Scanner scanner) {
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
        System.out.println("Nome do Departamento: ");
        String nomeDepartamento = scanner.nextLine().trim();

        if (nome.isEmpty() || sobrenome.isEmpty() || cpf.isEmpty() || cargo.isEmpty() || salario == 0
                || nomeDepartamento.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        LocalDate dataNascimento;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data de nascimento no formato inválido.");
            return;
        }

        String email = nome.toLowerCase() + "." + sobrenome.toLowerCase() + "@" + empresa.getNome().toLowerCase()
                + ".com";

        try {
            Funcionario funcionario = Funcionario.criarFuncionario(nome, sobrenome, dataNascimento, cpf, cargo, salario,
                    email);
            dController.adicionarFuncionario(funcionario, nomeDepartamento);
            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

                } catch (Exception e) {
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

    public static void editaFuncionario(FuncionarioController fController, DepartamentoController dController,
            Scanner scanner) {
        System.out.println("Digite o ID do funcionário que deseja editar: ");
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
        System.out.println("Nome do Departamento: ");
        String nomeDepartamento = scanner.nextLine().trim();

        if (nome.isEmpty() || sobrenome.isEmpty() || cpf.isEmpty() || cargo.isEmpty() || salario == 0
                || nomeDepartamento.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        LocalDate dataNascimento;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data de nascimento no formato inválido.");
            return;
        }

        try {
            // Busca o funcionário pelo ID
            Optional<Funcionario> optionalFuncionario = fController.buscaFuncionario(id);
            if (!optionalFuncionario.isPresent()) {
                System.out.println("Funcionário não encontrado.");
                return;
            }

            Funcionario funcionario = optionalFuncionario.get();

            // Atualiza os dados do funcionário
            funcionario.setNome(nome);
            funcionario.setSobrenome(sobrenome);
            funcionario.setDataNasc(dataNascimento);
            funcionario.setCpf(cpf);
            funcionario.setCargo(cargo);
            funcionario.setSalario(salario);

            // Verifica se o departamento mudou
            Optional<Departamento> departamentoAtual = dController.getDepartamentos().stream()
                    .filter(d -> d.getFuncionarios().contains(funcionario))
                    .findFirst();

            if (!departamentoAtual.isPresent()) {
                System.out.println("Departamento atual do funcionário não encontrado.");
                return;
            }

            if (!departamentoAtual.get().getNome().equalsIgnoreCase(nomeDepartamento)) {
                // Remove o funcionário do departamento atual
                departamentoAtual.get().getFuncionarios().remove(funcionario);

                // Adiciona o funcionário ao novo departamento
                Optional<Departamento> novoDepartamento = dController.buscaDepartamento(nomeDepartamento);
                if (!novoDepartamento.isPresent()) {
                    System.out.println("Novo departamento não encontrado.");
                    return;
                }

                novoDepartamento.get().adicionarFuncionario(funcionario);
            }

            System.out.println("Funcionário editado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

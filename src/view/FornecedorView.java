package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import controller.*;
import model.*;

public class FornecedorView {

    public static void cadastroFornecedor(FornecedorController fController, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine().trim();
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        String dataNascimentoStr = scanner.nextLine().trim();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();
        System.out.print("Empresa: ");
        String empresa = scanner.nextLine().trim();

        if (nome.isEmpty() || sobrenome.isEmpty() || cpf.isEmpty() || empresa.isEmpty()) {
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

        Fornecedor fornecedor = new Fornecedor(nome, sobrenome, dataNascimento, cpf, empresa);

        try {
            fController.adicionaFornecedor(fornecedor);
            System.out.println("Fornecedor cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buscaFornecedor(FornecedorController fController, Scanner scanner) {
        System.out.println("Buscar Fornecedor:");
        System.out.print("Nome do fornecedor: ");
        String nome = scanner.nextLine();
        try {
            fController.buscaFornecedor(nome).ifPresentOrElse(
                fornecedor -> System.out.println("Fornecedor encontrado: " + fornecedor.exibiPessoa()),
                () -> System.out.println("Fornecedor não encontrado."));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeFornecedor(FornecedorController fController, Scanner scanner) {
        System.out.print("Nome do fornecedor: ");
        String nome = scanner.nextLine();
        try {
            fController.removeFornecedor(nome);
            System.out.println("Fornecedor removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editaFornecedor(FornecedorController fController, Scanner scanner) {
        System.out.print("Nome do fornecedor que deseja editar: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Novo Nome: ");
        String novoNome = scanner.nextLine().trim();
        System.out.print("Novo Sobrenome: ");
        String novoSobrenome = scanner.nextLine().trim();
        System.out.print("Nova Data de Nascimento (dd/MM/yyyy): ");
        String novaDataNascimentoStr = scanner.nextLine().trim();
        System.out.print("Novo CPF: ");
        String novoCpf = scanner.nextLine().trim();;
        System.out.print("Nova Empresa: ");
        String novaEmpresa = scanner.nextLine().trim();

        if (novoNome.isEmpty() || novoSobrenome.isEmpty() || novoCpf.isEmpty() || novaEmpresa.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        LocalDate novaDataNascimento;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            novaDataNascimento = LocalDate.parse(novaDataNascimentoStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data de nascimento no formato inválido.");
            return;
        }

        try {
            fController.editaFornecedor(nome, novoNome, novoSobrenome, novaDataNascimento, novoCpf, novaEmpresa);
            System.out.println("Fornecedor editado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package view;

import controller.PessoaController;
import model.Pessoa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PessoaView {

    public static void cadastroPessoa(PessoaController pController, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine().trim();
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        String dataNascimentoStr = scanner.nextLine().trim();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine().trim();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();

        if (nome.isEmpty() || sobrenome.isEmpty() || endereco.isEmpty() || cpf.isEmpty()) {
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

        Pessoa p = Pessoa.criarPessoa(nome, sobrenome, dataNascimento, endereco, cpf);

        try {
            pController.adicionaPessoa(p);
            System.out.println("Pessoa cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buscaPessoa(PessoaController pController, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        try {
            pController.buscaPessoa(nome).ifPresentOrElse(
                pessoa -> System.out.println("Pessoa encontrada: " + pessoa.exibiPessoa()),
                () -> System.out.println("Pessoa não encontrada."));
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao buscar a pessoa: " + e.getMessage());
        }
    }

    public static void removePessoa(PessoaController pController ,Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        try {
            pController.removePessoa(nome);
            System.out.println("Pessoa removida com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editaPessoa(PessoaController pController,Scanner scanner) {
        System.out.println("Digite o nome da pessoa que deseja editar: ");
        String oldName = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine();
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        String dataNascimentoStr = scanner.nextLine().trim();
        scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();


        if (nome.isEmpty() || sobrenome.isEmpty() || endereco.isEmpty() || cpf.isEmpty()) {
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
            pController.editaPessoa(oldName, nome, sobrenome, dataNascimento, endereco, cpf);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

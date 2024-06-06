package view;

import controller.PessoaController;
import model.Pessoa;

// import java.util.Optional;
import java.util.Scanner;

public class PessoaView {

    public static void cadastroPessoa(PessoaController pController, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine().trim();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine().trim();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();

        if (nome.isEmpty() || sobrenome.isEmpty() || endereco.isEmpty() || cpf.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        Pessoa p = Pessoa.criarPessoa(nome, sobrenome, idade, endereco, cpf);

        try {
            System.out.println("Pessoa cadastrada com sucesso!");
            pController.adicionaPessoa(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buscaPessoa(PessoaController pController, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        try {
            // Optional<Pessoa> pessoaOptional = pController.buscaPessoa(nome);
            // if (pessoaOptional.isPresent()) {
            //     Pessoa pessoa = pessoaOptional.get();
            //     System.out.println("Pessoa encontrada: " + pessoa);
            // } else {
            //     System.out.println("Pessoa não encontrada.");
            // }
            pController.buscaPessoa(nome).ifPresentOrElse(
                pessoa -> System.out.println("Pessoa encontrada: " + pessoa),
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
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        try {
            pController.editaPessoa(oldName, nome, sobrenome, idade, endereco);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

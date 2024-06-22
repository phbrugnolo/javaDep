package view;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
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
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine().trim();
        System.out.print("Empresa: ");
        String empresa = scanner.nextLine().trim();

        Fornecedor fornecedor = Fornecedor.criarFornecedor(nome, sobrenome, dataNascimentoStr, cnpj, empresa);

        try {
            fController.adicionaFornecedor(fornecedor);
            System.out.println("Fornecedor cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ocorreu um erro ao cadastrar o fornecedor: " + e.getMessage());
            return;
        } catch (DateTimeParseException e) {
            System.out.println("Ocorreu um erro ao cadastrar o fornecedor: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao cadastrar o fornecedor: " + e.getMessage());
        }
    }

    public static void buscaFornecedor(FornecedorController fController, Scanner scanner) {
        System.out.print("Nome do fornecedor: ");
        String nome = scanner.nextLine();
        try {
            fController.buscaFornecedor(nome).ifPresentOrElse(
                fornecedor -> System.out.println("Fornecedor encontrado: " + fornecedor.exibiPessoa()),
                () -> System.out.println("Fornecedor não encontrado."));
        } catch (NoSuchElementException e) {
            System.out.println("Ocorreu um erro ao buscar o fornecedor: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao buscar o fornecedor: " + e.getMessage());
        }
    }

    public static void removeFornecedor(FornecedorController fController, Scanner scanner) {
        System.out.print("Nome do fornecedor: ");
        String nome = scanner.nextLine();
        try {
            fController.removeFornecedor(nome);
            System.out.println("Fornecedor removido com sucesso!");
        } catch (NoSuchElementException e) {
            System.out.println("Ocorreu um erro ao remover o fornecedor: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao remover o fornecedor: " + e.getMessage());
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
        System.out.print("Novo CNPJ: ");
        String novoCpf = scanner.nextLine().trim();
        System.out.print("Nova Empresa: ");
        String novaEmpresa = scanner.nextLine().trim();


        try {
            fController.editaFornecedor(nome, novoNome, novoSobrenome, novaDataNascimentoStr, novoCpf, novaEmpresa);
            System.out.println("Fornecedor editado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ocorreu um erro ao editar o fornecedor: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Ocorreu um erro ao editar o fornecedor: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao editar o fornecedor: " + e.getMessage());
        }
    }

    public static void registrarFornecimento(FornecedorController fController, Scanner scanner) {
        System.out.print("Nome do fornecedor: ");
        String nomeFornecedor = scanner.nextLine().trim();
    
        System.out.println("Digite os produtos fornecidos separados por vírgula:");
        String produtosStr = scanner.nextLine().trim();
        List<String> produtos = List.of(produtosStr.split("\\s*,\\s*"));
    
        try {
            fController.registrarFornecimento(nomeFornecedor, produtos);
            System.out.println("Fornecimento registrado com sucesso!");
        } catch (NoSuchElementException e) {
            System.out.println("Ocorreu um erro ao registrar o fornecimento: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao registrar o fornecimento: " + e.getMessage());
        }
    }
}

package view;

import controller.DepartamentoController;
import model.Departamento;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DepartamentoView {

    public static void cadastroDepartamento(DepartamentoController depController, Scanner scanner) {
        System.out.print("Nome do Departamento: ");
        String nome = scanner.nextLine().trim();
        
        Departamento departamento = Departamento.criarDepartamento(nome);

        try {
            depController.adicionaDepartamento(departamento);
            System.out.println("Departamento cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ocorreu um erro ao cadastrar o departamento: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao cadastrar o departamento: " + e.getMessage());
        }
    }
    

    public static void removeDepartamento(DepartamentoController depController, Scanner scanner) {
        System.out.print("Nome do Departamento: ");
        String nome = scanner.nextLine();

        try {
            depController.removeDepartamento(nome);
            System.out.println("Departamento removido com sucesso.");
        } catch (NoSuchElementException e) {
            System.out.println("Ocorreu um erro ao remover o departamento: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao remover o departamento: " + e.getMessage());
        }
    }

    public static void buscaDepartamento(DepartamentoController depController, Scanner scanner) {
        System.out.print("Nome do Departamento: ");
        String nome = scanner.nextLine();

        try {
            depController.buscaDepartamento(nome).ifPresentOrElse(
                departamento -> System.out.println("Departamento encontrado: " + departamento),
                () -> System.out.println("Departamento não encontrado."));
        } catch (NoSuchElementException e) {
            System.out.println("Ocorreu um erro ao buscar o departamento: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao buscar o departamento: " + e.getMessage());
        }
    }

    public static void editaDepartamento(DepartamentoController depController, Scanner scanner) {
        System.out.print("Nome do Departamento: ");
        String nome = scanner.nextLine();

        System.out.print("Novo nome do Departamento: ");
        String novoNome = scanner.nextLine();

        try {
            depController.editaDepartamento(nome, novoNome);
            System.out.println("Departamento editado com sucesso.");
        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.out.println("Ocorreu um erro ao editar o departamento: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao editar o departamento: " + e.getMessage());
        }
    }
}
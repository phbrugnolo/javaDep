package views;

import java.util.Scanner;
import controller.CriarLista;
import java.util.List;
import models.*;

public class App {
    List<Pessoa> pessoas = CriarLista.criarLista();
    List<Funcionario> funcionarios = CriarLista.criarLista();
    List<Departamento> departamentos = CriarLista.criarLista();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menu:");
            System.out.println("[1] Cadastrar uma pessoa");
            System.out.println("[2] Cadastrar um funcionário");
            System.out.println("[3] Buscar um funcionário");
            System.out.println("[4] Listar funcionários de um departamento");
            System.out.println("[5] Cadastrar um departamento");
            System.out.println("[6] Buscar departamento");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    
                    break;
                case 2:
                    break;
                case 3:
                    // Listar funcionários de um departamento
                    break;
                case 4:
                    // Cadastrar um departamento
                    break;
                case 5:
                    break;
                case 6:
                    // Buscar departamento
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (option != 0);

        scanner.close();
    }
}

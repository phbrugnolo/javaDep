package views;

import java.util.Scanner;
import controller.CriarLista;
import java.util.List;
import models.*;
import util.Log;

public class App {
    List<Pessoa> pessoas = CriarLista.criarLista();
    List<Funcionario> funcionarios = CriarLista.criarLista();
    List<Departamento> departamentos = CriarLista.criarLista();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int option;
        int subOption;

        do {
            System.out.println("Menu Principal:");
            System.out.println("[1] Pessoa");
            System.out.println("[2] Funcionário");
            System.out.println("[3] Departamento");
            System.out.println("[4] Empresa");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    do {
                        System.out.println("Menu Pessoa:");
                        System.out.println("[1] Cadastrar Pessoa");
                        System.out.println("[2] Buscar Pessoa");
                        System.out.println("[3] Atualizar Pessoa");
                        System.out.println("[4] Deletar Pessoa");
                        System.out.println("[0] Voltar");
                        System.out.print("Escolha uma opção: ");
                        subOption = scanner.nextInt();

                        switch (subOption) {
                            case 1:
                                // Código para cadastrar uma pessoa
                                Log.logAction("Pessoa cadastrada");
                                break;
                            case 2:
                                // Código para buscar uma pessoa
                                break;
                            case 3:
                                // Código para atualizar uma pessoa
                                break;
                            case 4:
                                // Código para deletar uma pessoa
                                break;
                            case 0:
                                System.out.println("Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } while (subOption != 0);
                    break;

                case 2:
                    do {
                        System.out.println("Menu Funcionário:");
                        System.out.println("[1] Cadastrar Funcionário");
                        System.out.println("[2] Buscar Funcionário");
                        System.out.println("[3] Atualizar Funcionário");
                        System.out.println("[4] Deletar Funcionário");
                        System.out.println("[0] Voltar");
                        System.out.print("Escolha uma opção: ");
                        subOption = scanner.nextInt();

                        switch (subOption) {
                            case 1:
                                // Código para cadastrar um funcionário
                                Log.logAction("Funcionário cadastrado");
                                break;
                            case 2:
                                // Código para buscar um funcionário
                                break;
                            case 3:
                                // Código para atualizar um funcionário
                                break;
                            case 4:
                                // Código para deletar um funcionário
                                break;
                            case 0:
                                System.out.println("Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } while (subOption != 0);
                    break;

                case 3:
                    do {
                        System.out.println("Menu Departamento:");
                        System.out.println("[1] Cadastrar Departamento");
                        System.out.println("[2] Buscar Departamento");
                        System.out.println("[3] Atualizar Departamento");
                        System.out.println("[4] Deletar Departamento");
                        System.out.println("[0] Voltar");
                        System.out.print("Escolha uma opção: ");
                        subOption = scanner.nextInt();

                        switch (subOption) {
                            case 1:
                                // Código para cadastrar um departamento
                                Log.logAction("Departamento cadastrado");
                                break;
                            case 2:
                                // Código para buscar um departamento
                                break;
                            case 3:
                                // Código para atualizar um departamento
                                break;
                            case 4:
                                // Código para deletar um departamento
                                break;
                            case 0:
                                System.out.println("Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } while (subOption != 0);
                    break;

                case 4:
                    do {
                        System.out.println("Menu Empresa:");
                        System.out.println("[1] Cadastrar Empresa");
                        System.out.println("[2] Buscar Empresa");
                        System.out.println("[3] Atualizar Empresa");
                        System.out.println("[4] Deletar Empresa");
                        System.out.println("[0] Voltar");
                        System.out.print("Escolha uma opção: ");
                        subOption = scanner.nextInt();

                        switch (subOption) {
                            case 1:
                                // Código para cadastrar uma empresa
                                Log.logAction("Empresa cadastrada");
                                break;
                            case 2:
                                // Código para buscar uma empresa
                                break;
                            case 3:
                                // Código para atualizar uma empresa
                                break;
                            case 4:
                                // Código para deletar uma empresa
                                break;
                            case 0:
                                System.out.println("Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } while (subOption != 0);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (option != 0);
    }
}

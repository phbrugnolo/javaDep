import java.util.Scanner;
import controller.DepartamentoController;
import controller.Empresa;
import controller.FornecedorController;
import controller.FuncionarioController;
import util.CriarLista;
import view.*;
import java.util.InputMismatchException;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        int subOption = -1;

        DepartamentoController departamentoController = new DepartamentoController(CriarLista.criarListaDepartamento());
        FuncionarioController funcionarioController = new FuncionarioController(CriarLista.criarListaFuncionario());
        FornecedorController fornecedorController = new FornecedorController(CriarLista.criarListaFornecedor());
        Empresa empresa = new Empresa("NoxEnterprises", departamentoController, funcionarioController, fornecedorController);

        do {
            System.out.println("Menu Principal:");
            System.out.println("[1] Departamento");
            System.out.println("[2] Funcionário");
            System.out.println("[3] Fornecedor");
            System.out.println("[4] Empresa");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Por favor, insira um número.");
                scanner.next();
                continue;
            }

            switch (option) {
                case 1:
                    do {
                        System.out.println("Menu Departamento:");
                        System.out.println("[1] Cadastrar Departamento");
                        System.out.println("[2] Buscar Departamento");
                        System.out.println("[3] Atualizar Departamento");
                        System.out.println("[4] Deletar Departamento");
                        System.out.println("[0] Voltar");
                        System.out.print("Escolha uma opção: ");
                        try {
                            subOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Opção inválida! Por favor, insira um número.");
                            scanner.next();
                            continue;
                        }
                        scanner.nextLine();

                        switch (subOption) {
                            case 1:
                                DepartamentoView.cadastroDepartamento(departamentoController, scanner);
                                break;
                            case 2:
                                DepartamentoView.buscaDepartamento(departamentoController, scanner);
                                break;
                            case 3:
                                DepartamentoView.editaDepartamento(departamentoController, scanner);
                                break;
                            case 4:
                                DepartamentoView.removeDepartamento(departamentoController, scanner);
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
                        try {
                            subOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Opção inválida! Por favor, insira um número.");
                            scanner.next();
                            continue;
                        }
                        scanner.nextLine();

                        switch (subOption) {
                            case 1:
                                FuncionarioView.cadastroFuncionario(funcionarioController, departamentoController, empresa, scanner);
                                break;
                            case 2:
                                FuncionarioView.buscaFuncionario(funcionarioController, scanner);
                                break;
                            case 3:
                                FuncionarioView.editaFuncionario(funcionarioController, empresa, scanner);
                                break;
                            case 4:
                                FuncionarioView.removeFuncionario(funcionarioController, scanner);
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
                        System.out.println("Menu Fornecedor:");
                        System.out.println("[1] Cadastrar Fornecedor");
                        System.out.println("[2] Buscar Fornecedor");
                        System.out.println("[3] Atualizar Fornecedor");
                        System.out.println("[4] Deletar Fornecedor");
                        System.out.println("[5] Registrar um Forneciemento");
                        System.out.println("[0] Voltar");
                        System.out.print("Escolha uma opção: ");
                        try {
                            subOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Opção inválida! Por favor, insira um número.");
                            scanner.next();
                            continue;
                        }
                        scanner.nextLine();

                        switch (subOption) {
                            case 1:
                                FornecedorView.cadastroFornecedor(fornecedorController, scanner);
                                break;
                            case 2:
                                FornecedorView.buscaFornecedor(fornecedorController, scanner);
                                break;
                            case 3:
                                FornecedorView.editaFornecedor(fornecedorController, scanner);
                                break;
                            case 4:
                                FornecedorView.removeFornecedor(fornecedorController, scanner);
                                break;
                            case 5:
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
                        System.out.println("Menu da Empresa: " + empresa.getNome() + ":");
                        System.out.println("[1] Lista geral da Empresa");
                        System.out.println("[2] Relatório de Folha Salarial");
                        System.out.println("[3] Lista de Departamentos");
                        System.out.println("[4] Lista de Funcionários em um Departamento");
                        System.out.println("[5] Lista de Funcionários");
                        System.out.println("[6] Lista de Fornecedores");
                        System.out.println("[0] Voltar");
                        System.out.print("Escolha uma opção: ");
                        try {
                            subOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Opção inválida! Por favor, insira um número.");
                            scanner.next();
                            continue;
                        }
                        scanner.nextLine();

                        switch (subOption) {
                            case 1:
                                EmpresaView.listaGeral(empresa);
                                break;
                            case 2:
                                EmpresaView.folhaSalarial(empresa);
                                break;
                            case 3:
                                EmpresaView.listaDepartamento(empresa);
                                break;
                            case 4:
                                EmpresaView.listaFuncionariosEmUmDepartamento(empresa, scanner);
                                break;
                            case 5:
                                EmpresaView.listaFuncionarios(empresa);
                                break;
                            case 6:
                                EmpresaView.listaFornecedores(empresa);
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

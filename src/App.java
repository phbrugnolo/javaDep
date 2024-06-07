import java.util.Scanner;
import controller.*;
import view.*;

public class App{
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int option;
        int subOption;

        Empresa empresa = new Empresa("NoxEnterprises");
        PessoaController pessoaController = new PessoaController(empresa.getPessoas());
        DepartamentoController departamentoController = new DepartamentoController(empresa.getDepartamentos());
        FuncionarioController funcionarioController = new FuncionarioController(empresa.getFuncionarios());

        do {
            System.out.println("Menu Principal:");
            System.out.println("[1] Pessoa");
            System.out.println("[2] Departamento");
            System.out.println("[3] Funcionário");
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
                        scanner.nextLine(); 

                        switch (subOption) {
                            case 1:
                                PessoaView.cadastroPessoa(pessoaController, scanner);
                                break;

                            case 2:
                                PessoaView.buscaPessoa(pessoaController, scanner);
                                break;

                            case 3:
                                PessoaView.editaPessoa(pessoaController, scanner);
                                break;

                            case 4:
                                PessoaView.removePessoa(pessoaController, scanner);
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
                    System.out.println("Menu Departamento:");
                    System.out.println("[1] Cadastrar Departamento");
                    System.out.println("[2] Buscar Departamento");
                    System.out.println("[3] Atualizar Departamento");
                    System.out.println("[4] Deletar Departamento");
                    System.out.println("[0] Voltar");
                    System.out.print("Escolha uma opção: ");
                    subOption = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (subOption) {
                        case 1:
                            DepartamentoView.cadastroDepartamento(departamentoController, empresa, scanner);
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
                case 3:
                do {
                    System.out.println("Menu Funcionário:");
                    System.out.println("[1] Cadastrar Funcionário");
                    System.out.println("[2] Buscar Funcionário");
                    System.out.println("[3] Atualizar Funcionário");
                    System.out.println("[4] Deletar Funcionário");
                    System.out.println("[0] Voltar");
                    System.out.print("Escolha uma opção: ");
                    subOption = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (subOption) {
                        case 1:
                            FuncionarioView.cadastroFuncionario(funcionarioController, pessoaController, departamentoController, empresa, scanner);
                            break;
                        case 2:
                           FuncionarioView.buscaFuncionario(funcionarioController, scanner);
                            break;
                        case 3:
                            FuncionarioView.editaFuncionario(funcionarioController, scanner);
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
                case 4:
                    System.out.println("Menu da Empresa: " + empresa.getNome() + ":");
                    System.out.println("[1] Lista geral da Empresa");
                    System.out.println("[2] Relatório de Folha Salarial");
                    System.out.println("[3] Lista de Departamento");
                    System.out.println("[4] Lista de Funcionários em um Departamento");
                    System.out.println("[5] Lista de Funcionários");
                    System.out.println("[6] Lista de Pessoas");
                    System.out.println("[0] Voltar");
                    System.out.println("Escolha uma opção: ");
                    subOption = scanner.nextInt();
                    scanner.nextLine(); 
                    do {
                        switch (subOption) {
                            case 1:
                                System.out.println(empresa);
                                break;
                            case 2:
                                empresa.relatorioFolhaSalarial();
                                break;
                            case 3:
                                System.out.println(empresa.getDepartamentos());
                                break;
                            case 4:

                                break;
                            case 5:
                                System.out.println(empresa.getFuncionarios());
                                break;
                            case 6:
                                System.out.println(empresa.getPessoas());
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

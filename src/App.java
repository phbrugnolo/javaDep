import java.util.Scanner;
import java.util.List;
import controller.*;
import models.*;
import views.*;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int option;
        int subOption;

        List<Pessoa> pessoas = CriarLista.criarLista();
        List<Funcionario> funcionarios = CriarLista.criarLista();
        List<Departamento> departamentos = CriarLista.criarLista();
        Empresa empresa = new Empresa("Teste", pessoas, funcionarios, departamentos);

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
                        scanner.nextLine(); 

                        switch (subOption) {
                            case 1:
                                _Pessoa.cadastroPessoa(empresa, scanner);
                                break;

                            case 2:
                                _Pessoa.buscaPessoa(empresa, scanner);
                                break;

                            case 3:
                                _Pessoa.editaPessoa(empresa, scanner);
                                break;

                            case 4:
                                _Pessoa.removePessoa(empresa, scanner);
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
                        scanner.nextLine(); 

                        switch (subOption) {
                            case 1:
                                _Funcionario.cadastroFuncionario(empresa, scanner);
                                break;
                            case 2:
                               _Funcionario.buscaFuncionario(empresa, scanner);
                                break;
                            case 3:
                                // Código para atualizar um funcionário
                                // Pode ser implementado de forma semelhante ao cadastrar
                                break;

                            case 4:
                                _Funcionario.removeFuncionario(empresa, scanner);
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
                        scanner.nextLine(); 

                        switch (subOption) {
                            case 1:
                                _Departamento.cadastroDepartamento(empresa, scanner);
                                break;

                            case 2:
                                _Departamento.buscaDepartamento(empresa, scanner);
                                break;

                            case 3:
                                // Código para atualizar um departamento
                                // Pode ser implementado de forma semelhante ao cadastrar
                                break;

                            case 4:
                                _Departamento.removeDepartamento(empresa, scanner);
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
                    System.out.println("Empresa " + empresa.getNome() + ":");
                    System.out.println("Lista de Pessoas: " + empresa.listaPessoas());
                    System.out.println("Lista de Funcionários: " + empresa.listaFuncionarios());
                    System.out.println("Lista de Departamentos: " + empresa.listaDepartamentos());
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

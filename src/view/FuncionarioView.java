package view;

import java.util.Scanner;

import controller.*;
import model.*;

public class FuncionarioView {

    public static void cadastroFuncionario(FuncionarioController fController,PessoaController pController, DepartamentoController dController, Empresa emp, Scanner scanner){
        System.out.print("Digite o nome da Pessoa: ");
        String nome = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Salário: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite o nome do Departamento: ");
        String nomeDep = scanner.nextLine();
        

        try {
            
            Pessoa p = pController.buscaPessoa(nome).orElseThrow(() -> new Exception("Pessoa não encontrada"));
            Departamento d = dController.buscaDepartamento(nomeDep).orElseThrow(()-> new Exception("Departamento não encontrado"));
            String email = p.getNome().toLowerCase() + "." + p.getSobrenome().toLowerCase() + "@" + emp.getNome() + ".com";
            p.setTrabalhando(true);

            Funcionario f = Funcionario.criarFuncionario(p.getNome(), p.getSobrenome(), p.getDataNasc(), p.getEndereco(), p.getCpf(), cargo, salario, email, d);
            System.out.println("Funcionário cadastrado com sucesso!");

            fController.adicionaFuncionario(f);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buscaFuncionario(FuncionarioController fController, Scanner scanner) {
        System.out.println("Buscar Funcionário:");
        System.out.println("[1] Buscar por Nome");
        System.out.println("[2] Buscar por ID");
        System.out.print("Escolha uma opção: ");
        int buscaOption = scanner.nextInt();
        scanner.nextLine();
        switch (buscaOption) {
            case 1:
                System.out.print("Nome do funcionário: ");
                String nome = scanner.nextLine();
                try {
                    fController.buscaFuncionario(nome).ifPresentOrElse(
                        funcionario -> System.out.println("Funcionário encontrado: " + funcionario.exibiPessoa()),
                        () -> System.out.println("Funcionário não encontrado."));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                System.out.print("ID do funcionário: ");
                int id = scanner.nextInt();
                try {
                    scanner.nextLine();
                    fController.buscaFuncionario(id).ifPresentOrElse(
                        funcionario -> System.out.println("Funcionário encontrado: " + funcionario),
                        () -> System.out.println("Funcionário não encontrado."));
                   
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public static void removeFuncionario(FuncionarioController fController, Scanner scanner) {
        System.out.print("Id do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            fController.removeFuncionario(id);
            System.out.println("Funcionário removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editaFuncionario(FuncionarioController fController, Scanner scanner) {
        System.out.println("Digite o ID do funcionário que deseja editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Salário: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();
        try {
            fController.editaFuncionario(id, salario, cargo);
            System.out.println("Funcionário editado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

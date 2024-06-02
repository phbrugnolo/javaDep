package views;

import models.Pessoa;
import controller.Empresa;
import util.Log;
import java.util.Scanner;

public class _Pessoa {
    
    public static void cadastroPessoa(Empresa listaPessoa, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();


        Pessoa newPerson = new Pessoa(nome, sobrenome, idade, endereco, cpf);
        try {
            listaPessoa.adicionaPessoa(newPerson);
            System.out.println("Pessoa cadastrada com sucesso!");
            Log.logAction("Pessoa cadastrada");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buscaPessoa(Empresa listaPessoa, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        try {
            Pessoa pessoa = listaPessoa.buscaPessoa(nome);
            System.out.println(pessoa);
            Log.logAction("Pessoa " + nome + " buscada com sucesso.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removePessoa(Empresa listaPessoa, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        try {
            listaPessoa.removePessoa(nome);
            System.out.println("Pessoa removida com sucesso!");
            Log.logAction("Pessoa " + nome + " removida");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

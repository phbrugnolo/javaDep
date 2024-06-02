package views;

import models.Departamento;
import util.Log;
import controller.Empresa;
import java.util.Scanner;;

public class _Departamento {

    public static void cadastroDepartamento(Empresa empresa, Scanner scanner) {
        System.out.print("Nome do Departamento: ");
        String nome = scanner.nextLine();

        Departamento departamento = new Departamento(nome);
        try {
            empresa.adicionaDepartamento(departamento);
            System.out.println("Departamento cadastrado com sucesso.");
            Log.logAction("Departamento cadastrado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeDepartamento(Empresa empresa, Scanner scanner) {
        System.out.print("Nome do Departamento: ");
        String nome = scanner.nextLine();
        try {
            empresa.removeDepartamento(nome);
            System.out.println("Departamento removido com sucesso.");
            Log.logAction("Departamento removido");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buscaDepartamento(Empresa empresa, Scanner scanner) {
        System.out.print("Nome do Departamento: ");
        String nome = scanner.nextLine();
        try {
            Departamento departamento = empresa.buscaDepartamento(nome);
            System.out.println(departamento);
            Log.logAction("Departamento " + nome + " buscado com sucesso.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    
}
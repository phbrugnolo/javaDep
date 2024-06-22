package view;

import java.util.Scanner;

import controller.Empresa;

public class EmpresaView {
    public static void listaGeral(Empresa empresa){
        System.out.println("\n" + empresa.listaGeral());
    }

    public static void listaDepartamento(Empresa empresa){
        System.out.println("\n" + empresa.listarDepartamentos());
    }

    public static void listaFuncionariosEmUmDepartamento(Empresa empresa, Scanner scanner){
        System.out.print("Digite o nome do departamento: ");
        String nome = scanner.nextLine();

        System.out.println("\n" + empresa.listarFuncionariosEmDepartamento(nome));
    }

    public static void folhaSalarial(Empresa empresa){
        System.out.println("\n" + empresa.calcularFolhaSalarial());
    }

    public static void listaFuncionarios(Empresa empresa){
        System.out.println("\n" + empresa.listarFuncionarios());
    }

    public static void listaFornecedores(Empresa empresa){
        System.out.println("\n" + empresa.listarFornecedores());
    }
}

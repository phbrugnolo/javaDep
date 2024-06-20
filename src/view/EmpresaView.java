package view;

import java.util.Scanner;

import controller.Empresa;

public class EmpresaView {
    public static void listaGeral(Empresa empresa){
        System.out.println(empresa.listaGeral());
    }

    public static void listaDepartamento(Empresa empresa){
        System.out.println(empresa.listarDepartamentos());
    }

    public static void listaFuncionariosEmUmDepartamento(Empresa empresa, Scanner scanner){
        System.out.println("Digite o nome do departamento: ");
        String nome = scanner.nextLine();

        System.out.println(empresa.listarFuncionariosEmDepartamento(nome));
    }

    public static void folhaSalarial(Empresa empresa){
        System.out.println(empresa.calcularFolhaSalarial());
    }

    public static void listaFuncionarios(Empresa empresa){
        System.out.println(empresa.listarFuncionarios());
    }

    public static void listaFornecedores(Empresa empresa){
        System.out.println(empresa.listarFornecedores());
    }
}

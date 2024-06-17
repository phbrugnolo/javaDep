package util;

import java.util.List;

import model.*;

import java.util.ArrayList;
public abstract class CriarLista {

    public static List<Departamento> criarListaDepartamento() {
        return new ArrayList<Departamento>();
    }

    public static List<Funcionario> criarListaFuncionario() {
        return new ArrayList<Funcionario>();
    }

    public static List<Fornecedor> criarListaFornecedor() {
        return new ArrayList<Fornecedor>();
    }
}
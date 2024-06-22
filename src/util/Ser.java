package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import model.*;

public abstract class Ser {
    private static final File ARQUIVO_FORNECEDOR = new File("src/obj/fornecedor.ser");
    private static final File ARQUIVO_DEPARTAMENTO = new File("src/obj/departamento.ser");
    private static final File ARQUIVO_FUNCIONARIO = new File("src/obj/funcionario.ser");

    public static <T> void salvar(List<T> lista, File arquivo) throws Exception {
        try {
            arquivo.getParentFile().mkdirs();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
                oos.writeObject(lista);
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo", e);
        }
    }

    public static <T> List<T> ler(File arquivo) throws Exception {
        try {
            if (arquivo.exists() && arquivo.isFile()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                    return (List<T>) ois.readObject();
                }
            }
            throw new Exception("Arquivo inválido");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Não foi possível ler o arquivo", e);
        }
    }

    public static void salvarFornecedor(List<Fornecedor> lista) throws Exception {
        salvar(lista, ARQUIVO_FORNECEDOR);
    }

    public static List<Fornecedor> lerFornecedores() throws Exception {
        return ler(ARQUIVO_FORNECEDOR);
    }

    public static void salvarDepartamento(List<Departamento> lista) throws Exception {
        salvar(lista, ARQUIVO_DEPARTAMENTO);
    }

    public static List<Departamento> lerDepartamentos() throws Exception {
        return ler(ARQUIVO_DEPARTAMENTO);
    }

    public static void salvarFuncionario(List<Funcionario> lista) throws Exception {
        salvar(lista, ARQUIVO_FUNCIONARIO);
    }

    public static List<Funcionario> lerFuncionarios() throws Exception {
        return ler(ARQUIVO_FUNCIONARIO);
    }
}

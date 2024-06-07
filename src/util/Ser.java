package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import model.*;

public abstract class Ser {
    private static final File ARQUIVOP = new File("src/obj/pessoa.ser");
    private static final File ARQUIVOD= new File("src/obj/departamento.ser");
    private static final File ARQUIVOF = new File("src/obj/funcionario.ser");

    public static void salvarPessoa(List<Pessoa> lista) throws Exception{
        try {
            ARQUIVOP.getParentFile().mkdirs();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVOP));
            oos.writeObject(lista);
            oos.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo");
        }
    }

    public static List<Pessoa> lerPessoas() throws Exception{
        try {
            if (ARQUIVOP.exists() && ARQUIVOP.isFile()) {
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(ARQUIVOP));

                return (List<Pessoa>) ois.readObject();
            }
            throw new Exception("Arquivo inválido"); 
            
            
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo");
        }
    }
    public static void salvarFuncionario(List<Funcionario> lista) throws Exception{
        try {
            ARQUIVOF.getParentFile().mkdirs();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVOF));
            oos.writeObject(lista);
            oos.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo");
        }
    }

    public static List<Funcionario> lerFuncionarios() throws Exception{
        try {
            if (ARQUIVOF.exists() && ARQUIVOF.isFile()) {
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(ARQUIVOF));

                return (List<Funcionario>) ois.readObject();
            }
            throw new Exception("Arquivo inválido"); 
            
            
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo");
        }
    }
    public static void salvarDepartamento(List<Departamento> lista) throws Exception{
        try {
            ARQUIVOD.getParentFile().mkdirs();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVOD));
            oos.writeObject(lista);
            oos.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo");
        }
    }

    public static List<Departamento> lerDepartamentos() throws Exception{
        try {
            if (ARQUIVOD.exists() && ARQUIVOD.isFile()) {
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(ARQUIVOD));

                return (List<Departamento>) ois.readObject();
            }
            throw new Exception("Arquivo inválido"); 
            
            
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo");
        }
    }
}

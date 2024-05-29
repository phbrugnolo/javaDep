package models;

import java.util.List;

public class Departamento extends CRUD<Funcionario>{
    
    public Departamento(List<Funcionario> lista) {
        super(lista);
    }

    @Override
    public void adicionar(Funcionario funcionario) throws Exception {
        if (funcionario == null) {
            lista.add(funcionario);
        } else {
            throw new Exception("Funcionário já cadastrado");
        }
    }

    @Override
    public void remover(Funcionario funcionario) throws Exception {
        if (funcionario != null) {
            lista.remove(funcionario);
        }else {
            throw new Exception("Funcionário não encontrado");
        }
    }

    @Override
    public List<Funcionario> listar() {
        return lista;
    }

    @Override
    public Funcionario buscar(String nome) {
        return lista.stream().filter(f -> f.getNome().equals(nome)).findFirst().orElse(null);
    }

    public Funcionario buscar(int id) {
        return lista.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }
}

package models;

import java.util.List;

public abstract class CRUD<T> {

    protected List<T> lista;

    public CRUD(List<T> lista) {
        this.lista = lista;
    }

    public T buscar(String nome) {
        return lista.stream().filter(f -> f.equals(nome)).findFirst().orElse(null);
    }

    public void adicionar(T obj) throws Exception {
        if (obj == null) {
            lista.add(obj);
        } else {
            throw new Exception("Objeto já cadastrado");
        }
    }

    public void remover(T obj) throws Exception {
        if (obj != null) {
            lista.remove(obj);
        } else {
            throw new Exception("Objeto não encontrado");
        }
    }

    public List<T> listar() {
        return lista;
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }
}
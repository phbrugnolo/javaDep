package controller;

import java.util.List;
import java.util.ArrayList;

public class CriarLista<T> {

    public static <T> List<T> criarLista() {
        return new ArrayList<T>();
    }
}
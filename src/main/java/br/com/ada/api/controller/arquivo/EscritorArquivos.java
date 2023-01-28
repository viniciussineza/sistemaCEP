package br.com.ada.api.controller.arquivo;

import java.io.IOException;

public interface EscritorArquivos<T> {

    void escrever(T object, String arquivo) throws IOException;

    T apagar(String arquivo) throws IOException;
}

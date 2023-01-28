package br.com.ada.api.controller.arquivo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface LeitorArquivos<T> {

    T ler(String arquivo) throws FileNotFoundException, ClassNotFoundException;

    List<T> ler() throws IOException, ClassNotFoundException;
}

package br.com.ada.api.controller.arquivo;

import java.io.File;

public abstract class AbstractArquivo {

    public void criarDiretorio(String diretorio) {
        File arquivo = new File(diretorio);
        if(!arquivo.exists()) arquivo.mkdir();
    }

    public void apagarArquivo(String diretorio, String nome) {
        File arquivo = new File(diretorio, nome);
        if(arquivo.exists()) arquivo.delete();
    }
}

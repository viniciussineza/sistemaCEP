package br.com.ada.api.view;

import java.util.Scanner;
import java.util.UUID;

public interface CepView<T>{

    Scanner scan = new Scanner(System.in);

    void cadastrar();

    void listar(UUID id);

    void listar();

    void atualizar();

    void atualizarProcessoInterno(T object);

    void apagar();

    void apagarProcessoInterno(UUID id);

    void exibirMenuInterno();

}

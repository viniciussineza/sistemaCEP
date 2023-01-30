package br.com.ada.api;

import br.com.ada.api.view.MenuPrincipal;

public class Main {



    public static void main(String[] args) {

        System.out.println("Bem vindo ao sistema de gerenciamento!");
        System.out.println("C - Cidades");
        System.out.println("E - Estados");
        System.out.println("P - Países");
        System.out.println("--------------------------------------");

        MenuPrincipal.exibir();

    }

}

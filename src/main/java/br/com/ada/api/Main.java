package br.com.ada.api;

import br.com.ada.api.controller.cep.CEPController;
import br.com.ada.api.controller.cep.CEPControllerFactory;
import br.com.ada.api.view.CepView;
import br.com.ada.api.view.CidadeView;
import java.util.Scanner;

public class Main {

    private static Integer opcao;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        CEPController cepcontroller = CEPControllerFactory
                .getInstance()
                .criar();

        System.out.println("Bem vindo ao sistema de gerenciamento!");
        System.out.println("C - Cidades");
        System.out.println("E - Estados");
        System.out.println("P - Países");
        System.out.println("--------------------------------------");

        menuPrincipal(cepcontroller);

    }

    private static void menuPrincipal(CEPController cepcontroller) {

        System.out.println("Informe uma das opções abaixo:");
        System.out.println("1 - Menu Cidades");
        System.out.println("2 - Menu Estados");
        System.out.println("3 - Menu Países");
        System.out.println("0 - Fechar sistema");

        opcao = Integer.parseInt(scan.nextLine());

        switch (opcao) {
            case 1 -> {
                CepView menuCidade = new CidadeView(cepcontroller);
                menuCidade.exibirMenuInterno();
            }
            case 0 -> System.exit(0);
            default -> System.out.println("Informe uma opção válida");
        }

        menuPrincipal(cepcontroller);
    }
}

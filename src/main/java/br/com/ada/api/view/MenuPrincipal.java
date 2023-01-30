package br.com.ada.api.view;

import br.com.ada.api.controller.cep.CEPController;
import br.com.ada.api.controller.cep.CEPControllerFactory;

import java.util.Scanner;

public class MenuPrincipal {

    private static Integer opcao;
    private static Scanner scan = new Scanner(System.in);
    private static CEPController cepcontroller;

    public static void exibir() {

        System.out.println("Informe uma das opções abaixo:");
        System.out.println("1 - Menu Cidades");
        System.out.println("2 - Menu Estados");
        System.out.println("3 - Menu Países");
        System.out.println("0 - Fechar sistema");

        opcao = Integer.parseInt(scan.nextLine());
        cepcontroller = CEPControllerFactory
                .getInstance()
                .criar(opcao);

        switch (opcao) {
            case 1 -> {
                CepView menuCidade = new CidadeView(cepcontroller);
                menuCidade.exibirMenuInterno();
            }
            case 2 -> {
                CepView menuEstado = new EstadoView(cepcontroller);
                menuEstado.exibirMenuInterno();
            }
            case 3 -> {
                CepView menuPais = new PaisView(cepcontroller);
                menuPais.exibirMenuInterno();
            }
            case 0 -> System.exit(0);
            default -> System.out.println("Informe uma opção válida");
        }

        exibir();
    }
}

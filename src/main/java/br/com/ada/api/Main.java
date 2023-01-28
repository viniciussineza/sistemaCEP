package br.com.ada.api;

import br.com.ada.api.controller.cep.CEPController;
import br.com.ada.api.controller.cep.CEPControllerFactory;

public class Main {

    public static void main(String[] args) {

        CEPController cepcontroller = CEPControllerFactory
                .getInstance()
                .criar();
    }
}

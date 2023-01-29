package br.com.ada.api.view;

import br.com.ada.api.controller.cep.CEPController;
import br.com.ada.api.controller.exception.ControllerException;
import br.com.ada.api.model.pais.Pais;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PaisView implements CepView<Pais> {

    private CEPController<Pais> controller;
    private Scanner scan;
    private Integer opcao;

    public PaisView(CEPController controller) {
        this.controller = controller;
        this.scan = CepView.scan;
    }
    @Override
    public void cadastrar() {
        System.out.println("Informe o nome do país: ");
        String nomeDoPais = scan.nextLine();

        System.out.println("Informe a sigla do pais: ");
        String sigladoPais = scan.nextLine();

        Pais pais = new Pais(UUID.randomUUID(),
                nomeDoPais,
                sigladoPais);
        controller.cadastrar(pais);
    }

    @Override
    public void listar(UUID id) {
        Pais pais = controller.listar(id);
        System.out.println(pais);
    }

    @Override
    public void listar() {
        List<Pais> paises = controller.listar()
                .stream()
                .sorted()
                .toList();

        for (int i = 0; i < paises.size(); i++) {
            System.out.println((i + 1) + " - " + paises.get(i));
        }
    }

    @Override
    public void atualizar() {

    }

    @Override
    public void atualizarProcessoInterno(Pais object) {

    }

    @Override
    public void apagar() {
        listar();
        System.out.println("Informe o número do país que deseja apagar: ");
        Integer numero = Integer.parseInt(scan.nextLine());
        Pais pais = controller.listar().get(numero - 1);
        apagarProcessoInterno(pais.getId());
    }

    @Override
    public void apagarProcessoInterno(UUID id) {
        try {
            Pais pais = controller.delete(id);
            System.out.println("País apagado foi:\n" + pais);
        } catch (ControllerException e) {
            System.out.println("Occorreu um erro!");
        }
    }

    @Override
    public void exibirMenuInterno() {
        System.out.println("Escolha a opção desejada: ");
        System.out.println("1 - Cadastrar Pais");
        System.out.println("2 - Listar Pais");
        System.out.println("3 - Atualizar Pais");
        System.out.println("4 - Excluir Pais");
        System.out.println("0 - Encerrar");

        opcao = Integer.parseInt(scan.nextLine());

        switch (opcao) {
            case 1 -> cadastrar();
            case 2 -> listar();
            case 3 -> System.out.println("Proxima versão");
            case 4 -> apagar();
            case 0 -> System.exit(0);
            default -> System.out.println("Informe uma opção válida");
        }

        exibirMenuInterno();
    }

}

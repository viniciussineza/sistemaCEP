package br.com.ada.api.view;

import br.com.ada.api.controller.cep.CEPController;
import br.com.ada.api.controller.exception.ControllerException;
import br.com.ada.api.model.estado.Estado;
import br.com.ada.api.model.pais.Pais;
import java.util.*;

public class EstadoView implements CepView<Estado> {

    private CEPController<Estado> controller;
    private Scanner scan;
    private Integer opcao;

    public EstadoView(CEPController controller) {
        this.controller = controller;
        this.scan = CepView.scan;
    }
    @Override
    public void cadastrar() {
        System.out.println("Informe o nome do estado: ");
        String nomeDoEstado = scan.nextLine();

        System.out.println("Informe a sigla do estado: ");
        String siglaDoestado = scan.nextLine();

        System.out.println("Informe o nome do país: ");
        String nomeDoPais = scan.nextLine();

        System.out.println("Informe a sigla do pais: ");
        String sigladoPais = scan.nextLine();

        Estado estado = new Estado(UUID.randomUUID(),
                nomeDoEstado,
                siglaDoestado,
                new Pais(UUID.randomUUID(), nomeDoPais, sigladoPais));

        controller.cadastrar(estado);
    }

    @Override
    public void listar(UUID id) {
        Estado estado = controller.listar(id);
        System.out.println(estado);
    }

    @Override
    public void listar() {
        List<Estado> estados = controller.listar()
                .stream()
                .sorted()
                .toList();
        for (int i = 0; i < estados.size(); i++) {
            System.out.println((i + 1) + " - " + estados.get(i));
        }
    }

    @Override
    public void atualizar() {

    }

    @Override
    public void atualizarProcessoInterno(Estado estado) {

    }

    @Override
    public void apagar() {
        listar();
        System.out.println("Informe o número do estado que deseja apagar: ");
        Integer numero = Integer.parseInt(scan.nextLine());
        Estado estado = controller.listar().get(numero - 1);
        apagarProcessoInterno(estado.getId());
    }

    @Override
    public void apagarProcessoInterno(UUID id) {
        try {
            Estado estado = controller.delete(id);
            System.out.println("Estado apagado foi:\n" + estado);
        } catch (ControllerException e) {
            System.out.println("Occorreu um erro!");
        }
    }

    @Override
    public void exibirMenuInterno() {
        System.out.println("Escolha a opção desejada: ");
        System.out.println("1 - Cadastrar Estado");
        System.out.println("2 - Listar Estado");
        System.out.println("3 - Atualizar Estado");
        System.out.println("4 - Excluir Estado");
        System.out.println("5 - Retornar para o menu principal");
        System.out.println("0 - Encerrar");

        opcao = Integer.parseInt(scan.nextLine());

        switch (opcao) {
            case 1 -> cadastrar();
            case 2 -> listar();
            case 3 -> System.out.println("Proxima versão");
            case 4 -> apagar();
            case 5 -> MenuPrincipal.exibir();
            case 0 -> System.exit(0);
            default -> System.out.println("Informe uma opção válida");
        }

        exibirMenuInterno();
    }
}

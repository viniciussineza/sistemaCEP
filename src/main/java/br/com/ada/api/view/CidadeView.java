package br.com.ada.api.view;

import br.com.ada.api.controller.cep.CEPController;
import br.com.ada.api.model.cidade.Cidade;
import br.com.ada.api.model.estado.Estado;
import br.com.ada.api.model.pais.Pais;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CidadeView implements CepView<Cidade> {

    private CEPController controller;
    private Scanner scan;
    private Integer opcao;

    public CidadeView(CEPController controller) {
        this.controller = controller;
        this.scan = CepView.scan;
    }

    @Override
    public void cadastrar() {
        System.out.println("Informe o nome da cidade: ");
        String nomeDaCidade = scan.nextLine();

        System.out.println("Informe o estado da cidade: ");
        String nomeDoEstado = scan.nextLine();

        System.out.println("Informe a sigla do Estado: ");
        String siglaDoestado = scan.nextLine();

        System.out.println("Informe o nome do País: ");
        String nomeDoPais = scan.nextLine();

        System.out.println("Informe a sigla do Pais: ");
        String sigladoPais = scan.nextLine();

        Cidade cidade = new Cidade(nomeDaCidade,
                new Estado(UUID.randomUUID(),
                        nomeDoEstado,
                        siglaDoestado,
                        new Pais(UUID.randomUUID(), nomeDoPais, sigladoPais)
                )
        );

        controller.cadastrar(cidade);
    }

    @Override
    public void listar(UUID id) {

    }

    @Override
    public void listar() {

    }

    @Override
    public void atualizar() {

    }

    @Override
    public void atualizarProcessoInterno(Cidade object) {

    }

    @Override
    public void apagar() {

    }

    @Override
    public void apagarProcessoInterno(UUID id) {

    }

    @Override
    public void exibirMenuInterno() {
        System.out.println("Escolha a opção desejada: ");
        System.out.println("1 - Cadastrar Cidade");
        System.out.println("2 - Listar Cidade");
        System.out.println("3 - Atualizar Cidade");
        System.out.println("4 - Excluir Cidade");
        System.out.println("0 - Encerrar");

        opcao = Integer.parseInt(scan.nextLine());

        switch (opcao) {
            case 1 -> cadastrar();
            case 2 -> listar();
            case 3 -> atualizar();
            case 4 -> apagar();
            case 0 -> System.exit(0);
            default -> System.out.println("Informe uma opção válida");
        }

        exibirMenuInterno();
    }
}

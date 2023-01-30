package br.com.ada.api.view;

import br.com.ada.api.Constantes;
import br.com.ada.api.controller.cep.CEPController;
import br.com.ada.api.controller.exception.ControllerException;
import br.com.ada.api.controller.exception.PessoaValidacaoException;
import br.com.ada.api.model.pais.Pais;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class PaisView implements CepView<Pais> {

    private static final String DIRETORIO_PAIS = Constantes.DIRETORIO_RAIZ + "paises";
    private CEPController<Pais> controller;
    private Scanner scan;
    private Integer opcao;

    public PaisView(CEPController controller) {
        this.controller = controller;
        this.scan = CepView.scan;
    }
    @Override
    public void cadastrar() {

        Pais pais;
        // if diretorio is empty, cadastrar
        // else diretorio tem arquivo, checar se o arquivo não é repetido
        // carregar todos os arquivos do diretorio e checar

        File pasta = new File(DIRETORIO_PAIS);

        if (!(Objects.requireNonNull(pasta.list()).length == 0)) {

            Set<Pais> paises = new HashSet<>(controller.listar());

            pais = cadastrarInterno();
            if (!paises.contains(pais)) {
                paises.add(pais);
                controller.cadastrar(pais);
                System.out.println("Pais adicionado:\n" + pais);

            } else {
                System.out.println("País já cadastrado com esse nome");
            }

        }

        pais = cadastrarInterno();
        controller.cadastrar(pais);
    }

    private Pais cadastrarInterno() {

        System.out.println("Informe o nome do país: ");
        String nomeDoPais = scan.nextLine();

        System.out.println("Informe a sigla do pais: ");
        String sigladoPais = scan.nextLine();

        Pais pais = new Pais(UUID.randomUUID(),
                nomeDoPais,
                sigladoPais);

        return pais;
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
        listar();
        System.out.println("Informe o número do país que deseja atualizar:");
        Integer numero = Integer.parseInt(scan.nextLine());

        try {
            Pais pais = controller.listar().get(numero - 1);
            atualizarProcessoInterno(pais);
        } catch (PessoaValidacaoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void atualizarProcessoInterno(Pais pais) {
        System.out.println(pais);
//        System.out.println("Escolha uma das opções");
//        System.out.println("1 - Atualizar todos os dados");
//        System.out.println("2 - Atualizar apenas o nome");
//        System.out.println("3 - Atualizar apenas a sigla");
//
//        opcao = Integer.parseInt(scan.nextLine());
//
//        switch (opcao) {
//            case 1 -> {
//                pais = cadastrarInterno();
//            }
//        }

        pais = cadastrarInterno();
        try {
            controller.atualizar(pais.getId(), pais);
        } catch (PessoaValidacaoException e) {
            System.out.println(e.getMessage());
        }
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
        System.out.println("5 - Retornar para o menu principal");
        System.out.println("0 - Encerrar");

        opcao = Integer.parseInt(scan.nextLine());

        switch (opcao) {
            case 1 -> cadastrar();
            case 2 -> listar();
            case 3 -> atualizar();
            case 4 -> apagar();
            case 5 -> MenuPrincipal.exibir();
            case 0 -> System.exit(0);
            default -> System.out.println("Informe uma opção válida");
        }

        exibirMenuInterno();
    }

}

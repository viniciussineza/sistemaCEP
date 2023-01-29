package br.com.ada.api.controller.cep;

import br.com.ada.api.Constantes;
import br.com.ada.api.controller.cep.impl.CEPArmazenamentoArquivoController;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CEPControllerFactory {

    private static final CEPControllerFactory INSTANCE = new CEPControllerFactory();
    private static final String CONTROLLER_TIPO = "cep.controller.tipo";
    private CEPArmazenamentoTipo tipo;

    private CEPControllerFactory() {}

    public static CEPControllerFactory getInstance() { return INSTANCE; }

    public CEPController criar(Integer opcao) {
        if(tipo == null) carregarTipoArmazenamento();

        CepDAOFactory cepdaoFactory = CepDAOFactory.getInstance();
        CEPController controller = null;

        if (tipo == CEPArmazenamentoTipo.ARQUIVO) controller = new CEPArmazenamentoArquivoController(cepdaoFactory.criar(opcao));
        else throw new RuntimeException("Nenhuma implementação disponível");

        return controller;
    }

    private void carregarTipoArmazenamento() {
        try{
            Properties properties = new Properties();
            properties.load(new FileReader(Constantes.ARQUIVO_PROPRIEDADES));
            String valorPropriedade = properties.getProperty(CONTROLLER_TIPO);
            tipo = CEPArmazenamentoTipo.valueOf(valorPropriedade);
        } catch (IOException e) {
            throw new RuntimeException("Falha no carregamento do arquivo de propriedades", e);
        }
    }

}

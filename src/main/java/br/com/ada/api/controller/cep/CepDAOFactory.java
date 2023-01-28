package br.com.ada.api.controller.cep;

import br.com.ada.api.Constantes;
import br.com.ada.api.controller.arquivo.EscritorArquivos;
import br.com.ada.api.controller.arquivo.LeitorArquivos;
import br.com.ada.api.controller.arquivo.impl.CidadeArquivoXML;
import br.com.ada.api.model.cep.PersistenciaTipo;
import br.com.ada.api.model.cidade.dao.impl.CidadeArquivoDAO;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CepDAOFactory {

    private static final CepDAOFactory INSTANCE = new CepDAOFactory();
    private static final String PERSISTENCIA_TIPO = "pessoa.persistencia.tipo";
    private PersistenciaTipo tipo;

    private CepDAOFactory() {}

    public static CepDAOFactory getInstance() { return INSTANCE; }

    public CEPController criar() {
        if (tipo == null) carregarTipoPersistencia();
        EscritorArquivos escritorArquivos = null;
        LeitorArquivos leitorArquivos = null;

        if (tipo == PersistenciaTipo.XML) {
            escritorArquivos = new CidadeArquivoXML();
            leitorArquivos = new CidadeArquivoXML();
        }

        return new CidadeArquivoDAO(
                escritorArquivos,
                leitorArquivos
        );
    }

    private void carregarTipoPersistencia() {
        try{
            Properties properties = new Properties();
            properties.load(new FileReader(Constantes.ARQUIVO_PROPRIEDADES));
            String valorPropriedade = properties.getProperty(PERSISTENCIA_TIPO);
            tipo = PersistenciaTipo.valueOf(valorPropriedade);
        } catch (IOException e) {
            throw new RuntimeException("Falha no carregamento do arquivo de propriedades", e);
        }
    }

}

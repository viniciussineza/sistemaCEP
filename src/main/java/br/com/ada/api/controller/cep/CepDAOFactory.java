package br.com.ada.api.controller.cep;

import br.com.ada.api.Constantes;
import br.com.ada.api.controller.arquivo.EscritorArquivos;
import br.com.ada.api.controller.arquivo.LeitorArquivos;
import br.com.ada.api.controller.arquivo.impl.cidade.CidadeArquivoXML;
import br.com.ada.api.controller.arquivo.impl.estado.EstadoArquivoXML;
import br.com.ada.api.model.cep.PersistenciaTipo;
import br.com.ada.api.model.cidade.dao.CidadeArquivoDAO;
import br.com.ada.api.model.estado.dao.EstadoArquivoDAO;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CepDAOFactory {

    private static final CepDAOFactory INSTANCE = new CepDAOFactory();
    private static final String PERSISTENCIA_TIPO = "pessoa.persistencia.tipo";
    private PersistenciaTipo tipo;

    private CepDAOFactory() {}

    public static CepDAOFactory getInstance() { return INSTANCE; }

    public CEPController criar(Integer opcao) {
        if (tipo == null) carregarTipoPersistencia();
        EscritorArquivos escritorArquivos = null;
        LeitorArquivos leitorArquivos = null;

        if (tipo == PersistenciaTipo.XML) {
        if (opcao == 1) {
            return new CidadeArquivoDAO(
                    escritorArquivos = new CidadeArquivoXML(),
                    leitorArquivos = new CidadeArquivoXML()
            );
        }
        if (opcao == 2) {
            return new EstadoArquivoDAO(
                    escritorArquivos = new EstadoArquivoXML(),
                    leitorArquivos = new EstadoArquivoXML()
            );
        }
        if (opcao == 3 ) {
            return null;
        }

        }


        return null;
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

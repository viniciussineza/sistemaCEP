package br.com.ada.api.controller.arquivo.impl;

import br.com.ada.api.controller.arquivo.AbstractXMLArquivo;
import br.com.ada.api.controller.arquivo.EscritorArquivos;
import br.com.ada.api.controller.arquivo.LeitorArquivos;
import br.com.ada.api.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.api.model.cidade.Cidade;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class CidadeArquivoXML extends AbstractXMLArquivo implements EscritorArquivos<Cidade>, LeitorArquivos<Cidade> {

    private static final String EXTENSAO = ".xml";
    private String diretorio = "database/xml/cidades";

    @Override
    public void escrever(Cidade cidade, String arquivo) throws IOException, ArquivoEscritaException {
        try {
            Document documento = criarNovoDocumento();
            Element elementoCidade = documento.createElement("cidade");
            documento.appendChild(elementoCidade);

            adicionarElemento(documento,
                    "id",
                    cidade.getId().toString(),
                    elementoCidade);
            adicionarElemento(documento,
                    "cidade",
                    cidade.getNomeDaCidade(),
                    elementoCidade);
            adicionarElemento(documento,
                    "estado",
                    cidade.getEstado().getEstadoESigla(),
                    elementoCidade);
            adicionarElemento(documento,
                    "pais",
                    cidade.getPais().getPaisESigla(),
                    elementoCidade);

            escreverArquivo(diretorio, arquivo + EXTENSAO, documento);

        } catch (ParserConfigurationException e) {
            throw new ArquivoEscritaException("Falha na conversão do xml.",e);
        }
    }

    @Override
    public Cidade apagar(String arquivo) throws IOException {
        return null;
    }

    @Override
    public Cidade ler(String arquivo) throws FileNotFoundException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Cidade> ler() throws IOException, ClassNotFoundException {
        return null;
    }

}

package br.com.ada.api.controller.arquivo.impl.pais;

import br.com.ada.api.Constantes;
import br.com.ada.api.controller.arquivo.AbstractXMLArquivo;
import br.com.ada.api.controller.arquivo.EscritorArquivos;
import br.com.ada.api.controller.arquivo.LeitorArquivos;
import br.com.ada.api.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.api.controller.arquivo.exception.ArquivoLeituraException;
import br.com.ada.api.model.pais.Pais;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PaisArquivoXML extends AbstractXMLArquivo implements EscritorArquivos<Pais>, LeitorArquivos<Pais> {

    private String DIRETORIO_PAIS = Constantes.DIRETORIO_RAIZ + "paises";
    @Override
    public void escrever(Pais pais, String arquivo) throws IOException {
        try {
            Document documentoPais = criarNovoDocumento();
            Element elementoPais = documentoPais.createElement("pais");
            adicionarElemento(documentoPais,
                    "id",
                    pais.getId().toString(),
                    elementoPais);
            adicionarElemento(documentoPais,
                    "nome",
                    pais.getNomeDoPais(),
                    elementoPais);
            adicionarElemento(documentoPais,
                    "sigla",
                    pais.getSiglaPais(),
                    elementoPais);
            escreverArquivo(DIRETORIO_PAIS, arquivo + Constantes.EXTENSAO, documentoPais);
        } catch (ParserConfigurationException e) {
            throw new ArquivoEscritaException("Falha na conversão do xml.",e);
        }
    }

    @Override
    public Pais apagar(String arquivo) throws IOException {
        File arquivoApagar = new File(DIRETORIO_PAIS, arquivo + Constantes.EXTENSAO);
        Pais pais = leituraArquivo(arquivoApagar);
        arquivoApagar.delete();
        return pais;
    }

    @Override
    public Pais ler(String arquivo) throws FileNotFoundException, ClassNotFoundException {
        File arquivoLeitura = new File(DIRETORIO_PAIS, arquivo + Constantes.EXTENSAO);
        return leituraArquivo(arquivoLeitura);
    }

    @Override
    public List<Pais> ler() throws IOException, ClassNotFoundException {
        List<Pais> paises = new ArrayList<>();

        FilenameFilter filtro = (DIRETORIO_PAIS, arquivo) -> arquivo.endsWith(Constantes.EXTENSAO);
        File pasta = new File(DIRETORIO_PAIS);
        for (File arquivo : Objects.requireNonNull(pasta.listFiles(filtro))) {
            Pais pais = leituraArquivo(arquivo);
            paises.add(pais);
        }
        return paises;
    }

    private Pais leituraArquivo(File arquivo) {
        Pais pais;

        try {
            Document documento = parse(arquivo);
            Element elementoPais = documento.getDocumentElement();

            String idPais = getValorElemento("id", elementoPais);
            String nomePais = getValorElemento("nome", elementoPais);
            String siglaPais = getValorElemento("sigla", elementoPais);

            pais = new Pais(UUID.fromString(idPais),
                    nomePais,
                    siglaPais);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ArquivoLeituraException("Não foi possível realizar a leitura do arquivo.",e);
        }

        return pais;
    }
}

package br.com.ada.api.controller.arquivo.impl.estado;

import br.com.ada.api.Constantes;
import br.com.ada.api.controller.arquivo.AbstractXMLArquivo;
import br.com.ada.api.controller.arquivo.EscritorArquivos;
import br.com.ada.api.controller.arquivo.LeitorArquivos;
import br.com.ada.api.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.api.controller.arquivo.exception.ArquivoLeituraException;
import br.com.ada.api.model.estado.Estado;
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

public class EstadoArquivoXML extends AbstractXMLArquivo implements EscritorArquivos<Estado>, LeitorArquivos<Estado> {

    private String DIRETORIO_ESTADO = Constantes.DIRETORIO_RAIZ + "estados";
    @Override
    public void escrever(Estado estado, String arquivo) throws ArquivoEscritaException {
        try {
            Document documentoEstado = criarNovoDocumento();
            Element elementoEstado = documentoEstado.createElement("estado");
            documentoEstado.appendChild(elementoEstado);

            adicionarElemento(documentoEstado,
                    "id",
                    estado.getId().toString(),
                    elementoEstado);
            adicionarElemento(documentoEstado,
                    "estado",
                    estado.getNomeDoEstado(),
                    elementoEstado);
            adicionarElemento(documentoEstado,
                    "sigla",
                    estado.getSiglaEstado(),
                    elementoEstado);

            Element elementoPais = documentoEstado.createElement("pais");
            elementoEstado.appendChild(elementoPais);
            adicionarElemento(documentoEstado,
                    "id",
                    estado.getPais().getId().toString(),
                    elementoPais);
            adicionarElemento(documentoEstado,
                    "nome",
                    estado.getPais().getNomeDoPais(),
                    elementoPais);
            adicionarElemento(documentoEstado,
                    "sigla",
                    estado.getPais().getSiglaPais(),
                    elementoPais);

            escreverArquivo(DIRETORIO_ESTADO,arquivo + Constantes.EXTENSAO, documentoEstado);

        } catch (ParserConfigurationException e) {
            throw new ArquivoEscritaException("Falha na conversão do xml.",e);
        }
    }

    @Override
    public Estado apagar(String arquivo) throws IOException {
        File arquivoApagar = new File(DIRETORIO_ESTADO, arquivo + Constantes.EXTENSAO);
        Estado estado = leituraArquivo(arquivoApagar);
        arquivoApagar.delete();
        return estado;
    }

    @Override
    public Estado ler(String arquivo) throws FileNotFoundException, ClassNotFoundException {
        File arquivoLeitura = new File(DIRETORIO_ESTADO, arquivo + Constantes.EXTENSAO);
        return leituraArquivo(arquivoLeitura);
    }

    @Override
    public List<Estado> ler() throws IOException, ClassNotFoundException {
        List<Estado> estados = new ArrayList<>();

        FilenameFilter filtro = (DIRETORIO_ESTADO, arquivo) -> arquivo.endsWith(Constantes.EXTENSAO);
        File pasta = new File(DIRETORIO_ESTADO);
        for (File arquivo : Objects.requireNonNull(pasta.listFiles(filtro))) {
            Estado estado = leituraArquivo(arquivo);
            estados.add(estado);
        }
        return estados;
    }

    private Estado leituraArquivo(File arquivo) {

        Estado estado;

        try {
            Document documento = parse(arquivo);
            Element elementoEstado = documento.getDocumentElement();
            Element elementoPais = (Element) elementoEstado.getElementsByTagName("pais").item(0);

            String idEstado = getValorElemento("id", elementoEstado);
            String nomeEstado = getValorElemento( "nome", elementoEstado);
            String siglaEstado = getValorElemento( "sigla", elementoEstado);
            String idPais = getValorElemento("id", elementoPais);
            String nomePais = getValorElemento("nome", elementoPais);
            String siglaPais = getValorElemento("sigla", elementoPais);

            estado = new Estado(
                    UUID.fromString(idEstado),
                    nomeEstado,
                    siglaEstado,
                    new Pais(UUID.fromString(idPais), nomePais, siglaPais)
                    );
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ArquivoLeituraException("Não foi possível realizar a leitura do arquivo.",e);
        }

        return estado;
    }
}

package br.com.ada.api.controller.arquivo.impl.cidade;

import br.com.ada.api.Constantes;
import br.com.ada.api.controller.arquivo.AbstractXMLArquivo;
import br.com.ada.api.controller.arquivo.EscritorArquivos;
import br.com.ada.api.controller.arquivo.LeitorArquivos;
import br.com.ada.api.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.api.controller.arquivo.exception.ArquivoLeituraException;
import br.com.ada.api.model.cidade.Cidade;
import br.com.ada.api.model.estado.Estado;
import br.com.ada.api.model.pais.Pais;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CidadeArquivoXML extends AbstractXMLArquivo implements EscritorArquivos<Cidade>, LeitorArquivos<Cidade> {

    private String DIRETORIO_CIDADE = Constantes.DIRETORIO_RAIZ + "cidades";
    @Override
    public void escrever(Cidade cidade, String arquivo) throws ArquivoEscritaException {
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

            Element elementoEstado = documento.createElement("estado");
            elementoCidade.appendChild(elementoEstado);
            adicionarElemento(documento,
                    "id",
                    cidade.getEstado().getId().toString(),
                    elementoEstado);
            adicionarElemento(documento,
                    "nome",
                    cidade.getEstado().getNomeDoEstado(),
                    elementoEstado);
            adicionarElemento(documento,
                    "sigla",
                    cidade.getEstado().getSiglaEstado(),
                    elementoEstado);

            Element elementoPais = documento.createElement("pais");
            elementoCidade.appendChild(elementoPais);
            adicionarElemento(documento,
                    "id",
                    cidade.getPais().getId().toString(),
                    elementoPais);
            adicionarElemento(documento,
                    "nome",
                    cidade.getPais().getNomeDoPais(),
                    elementoPais);
            adicionarElemento(documento,
                    "sigla",
                    cidade.getPais().getSiglaPais(),
                    elementoPais);

            escreverArquivo(DIRETORIO_CIDADE, arquivo + Constantes.EXTENSAO, documento);

        } catch (ParserConfigurationException e) {
            throw new ArquivoEscritaException("Falha na conversão do xml.",e);
        }
    }

    @Override
    public Cidade apagar(String arquivo) throws IOException {
        File arquivoApagar = new File(DIRETORIO_CIDADE, arquivo + Constantes.EXTENSAO);
        Cidade cidade = leituraArquivo(arquivoApagar);
        arquivoApagar.delete();
        return cidade;
    }

    @Override
    public Cidade ler(String arquivo) {
        File arquivoLeitura = new File(DIRETORIO_CIDADE, arquivo + Constantes.EXTENSAO);
        return leituraArquivo(arquivoLeitura);
    }

    @Override
    public List<Cidade> ler() {
        List<Cidade> cidades = new ArrayList<>();

        FilenameFilter filtro = (DIRETORIO_CIDADE, arquivo) -> arquivo.endsWith(Constantes.EXTENSAO);
        File pasta = new File(DIRETORIO_CIDADE);
        for(File arquivo : Objects.requireNonNull(pasta.listFiles(filtro))) {
            Cidade cidade = leituraArquivo(arquivo);
            cidades.add(cidade);
        }

        return cidades;
    }

    private Cidade leituraArquivo(File arquivo) {

        Cidade cidade;

        try {
            Document documento = parse(arquivo);
            Element elementoCidade = documento.getDocumentElement();
            Element elementoEstado = (Element) elementoCidade.getElementsByTagName("estado").item(0);
            Element elementoPais = (Element) elementoCidade.getElementsByTagName("pais").item(0);

            String idCidade = getValorElemento("id", elementoCidade);
            String nomeCidade = getValorElemento("cidade", elementoCidade);
            String idEstado = getValorElemento("id", elementoEstado);
            String nomeEstado = getValorElemento( "nome", elementoEstado);
            String siglaEstado = getValorElemento( "sigla", elementoEstado);
            String idPais = getValorElemento("id", elementoPais);
            String nomePais = getValorElemento("nome", elementoPais);
            String siglaPais = getValorElemento("sigla", elementoPais);

            cidade = new Cidade(
                    UUID.fromString(idCidade),
                    nomeCidade,
                    new Estado(UUID.fromString(idEstado),
                            nomeEstado,
                            siglaEstado,
                            new Pais(UUID.fromString(idPais), nomePais, siglaPais)
                    )
            );
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ArquivoLeituraException("Não foi possível realizar a leitura do arquivo.",e);
        }
        return cidade;
    }

}

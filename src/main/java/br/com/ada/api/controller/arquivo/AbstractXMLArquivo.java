package br.com.ada.api.controller.arquivo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class AbstractXMLArquivo extends AbstractArquivo {

    public void escreverArquivo(String diretorio, String nome, Document documento) {
        File arquivo = new File(diretorio, nome);

        try (FileOutputStream fileOutputStream = new FileOutputStream(arquivo)) {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource fonte = new DOMSource(documento);
            StreamResult resultado = new StreamResult(fileOutputStream);

            transformer.transform(fonte,resultado);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    protected void adicionarElemento(Document documento, String nomeDoElemento, String valorDoElemento, Node noPai) {
        Element elemento = documento.createElement(nomeDoElemento);
        elemento.setTextContent(valorDoElemento);
        noPai.appendChild(elemento);
    }

    protected Document criarNovoDocumento() throws ParserConfigurationException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        return builder.newDocument();
    }

    public Document parse(File arquivo) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        return builder.parse(arquivo);
    }

    protected String getValorElemento(String nomeDoElemento, Element elementoPai) {
        Node node = elementoPai.getElementsByTagName(nomeDoElemento).item(0);
        return node.getTextContent();
    }
}

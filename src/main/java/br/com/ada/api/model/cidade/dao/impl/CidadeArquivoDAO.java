package br.com.ada.api.model.cidade.dao.impl;

import br.com.ada.api.controller.arquivo.EscritorArquivos;
import br.com.ada.api.controller.arquivo.LeitorArquivos;
import br.com.ada.api.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.api.controller.cep.CEPController;
import br.com.ada.api.controller.exception.ControllerException;
import br.com.ada.api.model.cidade.Cidade;
import br.com.ada.api.model.cidade.dao.DAOException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CidadeArquivoDAO implements CEPController<Cidade> {

    private EscritorArquivos<Cidade> escritorArquivos;
    private LeitorArquivos<Cidade> leitorArquivos;

    public CidadeArquivoDAO(EscritorArquivos<Cidade> escritorArquivos,
                            LeitorArquivos<Cidade> leitorArquivos) {
        this.escritorArquivos = escritorArquivos;
        this.leitorArquivos = leitorArquivos;
    }

    @Override
    public void cadastrar(Cidade cidade) {
        try {
            escritorArquivos.escrever(cidade, cidade.getId().toString());
        } catch (IOException | ArquivoEscritaException e) {
            throw new DAOException("Não foi possível gravar a cidade!", e);
        }
    }

    @Override
    public Cidade listar(UUID id) {
        try {
            return leitorArquivos.ler(id.toString());
        } catch (DAOException e) {
            throw new ControllerException("Falha ao localizar a cidade.", e);
        } catch (FileNotFoundException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cidade> listar() {
        List<Cidade> cidades = new ArrayList<>();
        try {
            return leitorArquivos.ler();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Não foi possível ler a lista de pessoas",e);
        }
    }

    @Override
    public void atualizar(UUID id, Cidade cidade) {
        try {
            escritorArquivos.escrever(cidade, cidade.getId().toString());
        } catch (IOException e) {
            throw new DAOException("Não foi possível atualizar", e);
        }
    }

    @Override
    public Cidade delete(UUID id) {
        try {
            return escritorArquivos.apagar(id.toString());
        } catch (IOException e) {
            throw new DAOException("Falha ao pagar a cidade", e);
        }
    }

}

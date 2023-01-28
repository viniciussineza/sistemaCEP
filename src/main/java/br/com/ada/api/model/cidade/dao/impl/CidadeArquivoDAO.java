package br.com.ada.api.model.cidade.dao.impl;

import br.com.ada.api.controller.arquivo.EscritorArquivos;
import br.com.ada.api.controller.arquivo.LeitorArquivos;
import br.com.ada.api.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.api.controller.cep.CEPController;
import br.com.ada.api.model.cidade.Cidade;
import br.com.ada.api.model.cidade.dao.DAOException;

import java.io.IOException;
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
        return null;
    }

    @Override
    public List<Cidade> listar() {
        return null;
    }

    @Override
    public void atualizar(UUID id, Cidade object) {

    }

    @Override
    public Cidade delete(UUID id) {
        return null;
    }
}

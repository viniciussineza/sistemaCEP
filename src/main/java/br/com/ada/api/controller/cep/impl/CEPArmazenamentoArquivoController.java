package br.com.ada.api.controller.cep.impl;

import br.com.ada.api.controller.cep.CEPController;

import java.util.List;
import java.util.UUID;

public class CEPArmazenamentoArquivoController implements CEPController {

    private CEPController dao;

    public CEPArmazenamentoArquivoController( CEPController dao) {
        this.dao = dao;
    }
    @Override
    public void cadastrar(Object object) {
        dao.cadastrar(object);
    }

    @Override
    public Object listar(UUID id) {
        return null;
    }

    @Override
    public List listar() {
        return null;
    }

    @Override
    public void atualizar(UUID id, Object object) {

    }

    @Override
    public Object delete(UUID id) {
        return null;
    }
}

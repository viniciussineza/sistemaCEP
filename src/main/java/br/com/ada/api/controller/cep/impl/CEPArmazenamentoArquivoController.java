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
    public Object listar(UUID id) { return dao.listar(id); }

    @Override
    public List listar() { return dao.listar(); }

    @Override
    public void atualizar(UUID id, Object object) { dao.atualizar(id, object); }

    @Override
    public Object delete(UUID id) { return dao.delete(id); }
}

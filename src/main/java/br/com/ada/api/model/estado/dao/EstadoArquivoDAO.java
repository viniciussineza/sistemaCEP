package br.com.ada.api.model.estado.dao;

import br.com.ada.api.controller.arquivo.EscritorArquivos;
import br.com.ada.api.controller.arquivo.LeitorArquivos;
import br.com.ada.api.controller.arquivo.exception.ArquivoEscritaException;
import br.com.ada.api.controller.cep.CEPController;
import br.com.ada.api.controller.exception.ControllerException;
import br.com.ada.api.model.estado.Estado;
import br.com.ada.api.model.exception.DAOException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class EstadoArquivoDAO implements CEPController<Estado> {

    private EscritorArquivos<Estado> escritorArquivos;
    private LeitorArquivos<Estado> leitorArquivos;

    public EstadoArquivoDAO(EscritorArquivos<Estado> escritorArquivos,
                            LeitorArquivos<Estado> leitorArquivos) {
        this.escritorArquivos = escritorArquivos;
        this.leitorArquivos = leitorArquivos;
    }

    @Override
    public void cadastrar(Estado estado) {
        try {
            escritorArquivos.escrever(estado, estado.getId().toString());
        } catch (IOException | ArquivoEscritaException e) {
            throw new DAOException("Não foi possível gravar a cidade!", e);
        }
    }

    @Override
    public Estado listar(UUID id) {
        try {
            return leitorArquivos.ler(id.toString());
        } catch (DAOException e) {
            throw new ControllerException("Falha ao localizar a cidade.", e);
        } catch (FileNotFoundException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Estado> listar() {
        try {
            return leitorArquivos.ler();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Não foi possível ler a lista de pessoas",e);
        }
    }

    @Override
    public void atualizar(UUID id, Estado estado) {
        try {
            escritorArquivos.escrever(estado, estado.getId().toString());
        } catch (IOException e) {
            throw new DAOException("Não foi possível atualizar", e);
        }
    }

    @Override
    public Estado delete(UUID id) {
        try {
            return escritorArquivos.apagar(id.toString());
        } catch (IOException e) {
            throw new DAOException("Falha ao pagar a cidade", e);
        }
    }
}

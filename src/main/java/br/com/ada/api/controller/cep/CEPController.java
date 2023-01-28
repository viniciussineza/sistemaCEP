package br.com.ada.api.controller.cep;

import java.util.List;
import java.util.UUID;

public interface CEPController<T> {

    void cadastrar(T object);

    T listar (UUID id);

    List<T> listar();

    void atualizar(UUID id, T object);

    T delete(UUID id);

}

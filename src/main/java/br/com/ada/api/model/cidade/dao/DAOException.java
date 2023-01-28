package br.com.ada.api.model.cidade.dao;

public class DAOException extends RuntimeException {

    public DAOException(String mensagem) { super(mensagem);}

    public DAOException(String mensagem, Exception causa) { super(mensagem, causa); }

}

package br.com.ada.api.model.exception;

public class DAOException extends RuntimeException {

    public DAOException(String mensagem) { super(mensagem);}

    public DAOException(String mensagem, Exception causa) { super(mensagem, causa); }

}

package br.com.ada.api.controller.exception;

public class PessoaValidacaoException extends RuntimeException{

    public PessoaValidacaoException(String mensagem, Exception causa) { super(mensagem, causa); }
}

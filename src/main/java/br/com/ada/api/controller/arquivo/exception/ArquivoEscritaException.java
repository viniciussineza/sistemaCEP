package br.com.ada.api.controller.arquivo.exception;

public class ArquivoEscritaException extends RuntimeException {

    public ArquivoEscritaException (String mensagem, Exception causa) { super(mensagem, causa); }
}

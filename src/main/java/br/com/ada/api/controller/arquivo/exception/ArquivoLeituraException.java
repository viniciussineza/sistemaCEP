package br.com.ada.api.controller.arquivo.exception;

public class ArquivoLeituraException extends RuntimeException {
    public ArquivoLeituraException(String mensagem, Exception causa) {
        super(mensagem, causa);
    }
}

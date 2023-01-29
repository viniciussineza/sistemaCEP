package br.com.ada.api.controller.exception;

public class ControllerException extends RuntimeException {

    public ControllerException(String mensagem, Exception causa) { super(mensagem, causa); }
}

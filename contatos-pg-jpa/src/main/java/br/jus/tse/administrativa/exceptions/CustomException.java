package br.jus.tse.administrativa.exceptions;

public class CustomException extends RuntimeException {

    public CustomException(){
    }
    public CustomException(String message){
        super(message);
    }

    public CustomException(CustomException e){
        super(e);
    }


}

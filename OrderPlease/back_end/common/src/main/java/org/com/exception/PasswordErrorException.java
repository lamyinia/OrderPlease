package org.com.exception;

public class PasswordErrorException extends BaseException {
    public PasswordErrorException(){}
    public PasswordErrorException(String msg){
        super(msg);
    }
}

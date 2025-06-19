package org.com.exception;

public class PasswordEditFailedException extends BaseException {
    public PasswordEditFailedException(){}
    public PasswordEditFailedException(String msg){
        super(msg);
    }
}

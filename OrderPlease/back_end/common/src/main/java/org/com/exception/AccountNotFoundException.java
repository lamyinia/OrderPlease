package org.com.exception;

public class AccountNotFoundException extends BaseException {
    public AccountNotFoundException(){
        super();
    }
    public AccountNotFoundException(String msg){
        super(msg);
    }
}

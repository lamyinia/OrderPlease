package org.com.exception;

public class AddressBookBusinessException extends BaseException {
    public AddressBookBusinessException(){
        super();
    }
    public AddressBookBusinessException(String msg){
        super(msg);
    }
}

package org.com.exception;

public class DeletionNotAllowedException extends BaseException {
    public DeletionNotAllowedException(){
        super();
    }
    public DeletionNotAllowedException(String msg){
        super(msg);
    }
}

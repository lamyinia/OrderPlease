package org.com.exception;

public class OrderBusinessException  extends BaseException {
    public OrderBusinessException(){}
    public OrderBusinessException(String msg){
        super(msg);
    }
}

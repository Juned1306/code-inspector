package com.ai.codeinspector.excpetion;

public class AIServiceException  extends RuntimeException{
    public AIServiceException(){
        super();
    }
    public AIServiceException(String message){
        super(message);
    }
    public AIServiceException(String message,Throwable cause){
        super(message,cause);
    }
}

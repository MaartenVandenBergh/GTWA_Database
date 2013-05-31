package Exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Maarten
 */
@ApplicationException
public class ChatMessageException extends RuntimeException{
    public ChatMessageException(){
    }
    public ChatMessageException(String s){
        super("[ChatMessageException]" + s);
    }
}
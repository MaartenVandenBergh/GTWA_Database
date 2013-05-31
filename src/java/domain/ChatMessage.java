/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Exceptions.ChatMessageException;
import domain.entities.Account;
import java.util.Calendar;

/**
 *
 * @author Maarten
 */
public class ChatMessage {
    private Long posterId;
    private Calendar postTime;
    private String message;

    public Long getPosterId() {
        return posterId;
    }

    public void setPosterId(Long posterId) {
        if(posterId >= 0){
            this.posterId = posterId;
        }
        else{
            throw new ChatMessageException("Invalid user.");
        }
    }

    public Calendar getPostTime() {
        return postTime;
    }

    public void setPostTime(Calendar postTime) {
        if(postTime != null){
            this.postTime = postTime;
        }
        else{
            throw new ChatMessageException("Invalid postTime.");
        }
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) throws ChatMessageException {
        if(message != null && message != ""){
            this.message = message;
        }
    }
    @Override
    public boolean equals(Object o){
        boolean equal = false;
        
        if(o instanceof ChatMessage){
            ChatMessage cm = (ChatMessage)o;
            
            if(this.getPostTime().equals(cm.getPostTime()) && this.getPosterId() == cm.getPosterId()){
                equal = true;
            }
        }
        
        return equal;
    }
}

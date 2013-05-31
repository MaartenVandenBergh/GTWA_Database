/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.entities;

import Exceptions.entities.AccountException;
import domain.ChatMessage;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Maarten
 */
@Entity
public class Conversation implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    //Track info
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dateOfInitialisation;
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER)
    private List<Account> users;
    
    //Messaging
    private List<ChatMessage> messages; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDateOfInitialisation() {
        return dateOfInitialisation;
    }

    public void setDateOfInitialisation(Calendar dateOfInitialisation) {
        if(dateOfInitialisation != null){
            this.dateOfInitialisation = dateOfInitialisation;
        }
        else{
            throw new AccountException("Invalid date of initialisation.");
        }
    }

    public List<Account> getUsers() {
        return users;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.entities;

import Exceptions.entities.CountryException;
import Exceptions.entities.AccountException;
import domain.enums.Status;
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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Maarten
 */
@Entity
public class Account implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    //Login info
    private String username;
    private String password;
    
    //Personal info
    private String name;
    private String surname;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dateOfBirth;
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER)
    private Country country;
    
    //Contact info
    private String emailAddress;
    
    //Chat
    private Status status;
    @ManyToMany(mappedBy = "users", cascade={CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER)
    private List<Conversation> conversations;
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER)
    private List<Account> friends;
    
    public Account(){
    }
    
    public Account(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws AccountException {
        if(username != null && !username.equals("")){
            this.username = username;
        }
        else{
            throw new AccountException("Invalid username.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws AccountException {
        if(password != null && !password.equals("")){
            this.password = password;
        }
        else{
            throw new AccountException("Invalid password.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws AccountException {
        if(name != null && !name.equals("")){
            this.name = name;
        }
        else{
            throw new AccountException("Invalid name.");
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws AccountException {
        if(surname != null){
            this.surname = surname;
        }
        else{
            throw new AccountException("Invalid surname.");
        }
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) throws AccountException {
        if(dateOfBirth != null){
            this.dateOfBirth = dateOfBirth;
        }
        else{
            throw new AccountException("Invalid date of birth.");
        }
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) throws AccountException {
        if(country != null){
            this.country = country;
        }
        else{
            throw new AccountException("Invalid country.");
        }
    }
    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) throws AccountException {
        if(emailAddress != null && emailAddress != ""){
            this.emailAddress = emailAddress;
        }
        else{
            throw new AccountException("Invalid emailaddress.");
        }
    }
    
    public Status getStatus(){
        return this.status;
    }
    
    public void setStatus(Status s){
        if(s != null){
            this.status = s;
        }
        else{
            throw new AccountException("Invalid status.");
        }
    }

    public List<Conversation> getConversations() {
        return conversations;
    }
    
    public List<Account> getFriends() {
        return friends;
    }
    
    @Override
    public boolean equals(Object o){
        boolean equal = false;
        
        if(o instanceof Account){
            Account u = (Account)o;
            if(this.username.equals(u.username)){
                equal = true;
            }
        }
            
        return equal;
    }
    
}

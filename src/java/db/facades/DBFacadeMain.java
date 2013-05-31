/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.facades;

import Exceptions.facades.DBFacadeMainException;
import db.facades.interfaces.ConversationDBFacade;
import db.facades.interfaces.CountryDBFacade;
import db.facades.interfaces.AccountDBFacade;
import db.facades.interfaces.remotes.DBFacadeRemote;
import domain.entities.Conversation;
import domain.entities.Country;
import domain.entities.Account;
import domain.enums.Status;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maarten
 */
@Singleton
@Startup
public class DBFacadeMain implements DBFacadeRemote{
    
    @PersistenceContext(name="main")
    private EntityManager em;
    
    private AccountDBFacade accountDB;
    private ConversationDBFacade conversationDB;
    private CountryDBFacade countryDB;
    
    public DBFacadeMain(){
    }
    
    @Override
    public void init(){
        this.initialiseDBFacadeMain();
        Account admin = this.findAccount("admin");
            if(admin != null){
                this.removeAccount(admin);
            }
            Account a1 = new Account();
            a1.setUsername("admin");
            a1.setPassword("admin");
            a1.setSurname("Maarten");
            a1.setName("Van den Bergh");
            a1.setStatus(Status.OFFLINE);
            Calendar cal1 = new GregorianCalendar();
            cal1.set(1990, Calendar.SEPTEMBER, 4);
            a1.setDateOfBirth(cal1);
            
            Country c1 = new Country("Belgium");
            c1 = this.createCountry(c1);
            
            a1.setCountry(c1);
            a1.setEmailAddress("admin@admin.com");
            a1 = this.createAccount(a1);
            System.out.println("-Admin added.(Username:admin, Pass:admin)");
            
            for(int i = 0;i<10;i++){
                String username= "User"+i;
                Account user1 = this.findAccount(username);
                if(user1 != null){
                    this.removeAccount(user1);
                }
                Account a2 = new Account();
                a2.setUsername(username);
                a2.setPassword(username);
                a2.setSurname(username+"son");
                a2.setName(username+"ke");
                a2.setStatus(Status.OFFLINE);
                Calendar cal2 =  new GregorianCalendar();
                cal2.set(1950+1*2, Calendar.DECEMBER, 12+i);
                a2.setDateOfBirth(cal2);

                Country c2 = new Country(username+"land");
                c2 = this.createCountry(c2);

                a2.setCountry(c2);
                a2.setEmailAddress(username+"@ChatSite.com");
                a2 = this.createAccount(a2);
                System.out.println("-"+username+" added.(Username:"+username+", Pass:"+a2.getPassword()+")");
            }
            
            System.out.println("--Complete--\n");
    }

    private void initialiseDBFacadeMain() {
        this.accountDB = new AccountDBFacadeMain(em);
        this.conversationDB = new ConversationDBFacadeMain(em);
        this.countryDB = new CountryDBFacadeMain(em);
    }
    
    //Main functionality
    ////User
    @Override
    public Account createAccount(Account a) {
        return this.accountDB.create(a);
    }

    @Override
    public Account editAccount(Account a) {
        return this.accountDB.edit(a);
    }

    @Override
    public void removeAccount(Account a) {
        this.accountDB.remove(a);
    }
    
    ////Conversation
    @Override
    public Conversation createConversation(Conversation c) {
        return this.conversationDB.create(c);
    }

    @Override
    public Conversation editConversation(Conversation c) {
        return this.conversationDB.edit(c);
    }

    @Override
    public void removeConversation(Conversation c) {
        this.conversationDB.remove(c);
    }

    ////Country
    @Override
    public Country createCountry(Country c) {
        return this.countryDB.create(c);
    }

    @Override
    public Country editCountry(Country c) {
        return this.countryDB.edit(c);
    }

    @Override
    public void removeCountry(Country c) {
        this.countryDB.remove(c);
    }

    //Bidirectional functionality
    //-Account
    //--Account
    @Override
    public Map<String, Object> friendTwoAccounts(Account a1, Account a2) throws DBFacadeMainException{
        Map<String, Object> output = new HashMap<>();
        a1 = this.accountDB.findAccount(a1.getUsername());
        a2 = this.accountDB.findAccount(a2.getUsername());

        if(a1 != null && a2 != null){
            if(!a2.getFriends().contains(a1)){
                if(!a1.getFriends().contains(a2)){
                    a2.getFriends().add(a1);
                    a1.getFriends().add(a2);
                    
                    a2 = this.accountDB.edit(a2);
                    a1 = this.accountDB.edit(a1);
                    
                    output.put("Account1", a1);
                    output.put("Account2", a2);
                    
                }
                else{
                    throw new DBFacadeMainException("Given account is already friend of given friend.");
                }
            }
            else{
                throw new DBFacadeMainException("Given account is already friend of given user.");
            }
        }
        else{
            throw new DBFacadeMainException("Unknown account.");
        }
        
        return output; 
    }

    @Override
    public Map<String, Object> unfriendTwoAccounts(Account a1, Account a2) {
        Map<String, Object> output = new HashMap<>();
        a1 = this.accountDB.findAccount(a1.getId());
        //a2 = this.accountDB.findAccount(a2.getId());

        if(a1 != null && a2 != null){
            //if(a2.getFriends().contains(a1)){
                if(a1.getFriends().contains(a2)){
                    //a2.getFriends().remove(a1);
                    a1.getFriends().remove(a2);
                    
                    //a2 = this.accountDB.edit(a2);
                    a1 = this.accountDB.edit(a1);
                    
                    output.put("Account1", a1);
                    output.put("Account2", a2);
                    
                }
                else{
                    throw new DBFacadeMainException("Account2 is no friend of account1.");
                }
            /*}
            else{
                throw new DBFacadeMainException("Account1 is no friend of account2.");
            }*/
        }
        else{
            throw new DBFacadeMainException("Unknown input.");
        }
        
        return output; 
    }
    
    //--Country
    @Override
    public Account setCountryOfAccount(Country c, Account a) {
        if(c != null){
            c = this.countryDB.findCountry(c.getId());
        }
        a = this.accountDB.findAccount(a.getId());

        if(a != null){
            a.setCountry(c);
            
            a = this.accountDB.edit(a);
        }
        else{
            throw new DBFacadeMainException("Unknown input.");
        }
        
        return a; 
    }

    //-Conversation
    @Override
    public Map<String, Object> addAccountToConversation(Conversation c, Account u) {
       Map<String, Object> output = new HashMap<>();
        c = this.conversationDB.findConversation(c.getId());
        u = this.accountDB.findAccount(u.getId());

        if(c != null && u != null){
            if(!c.getUsers().contains(u)){
                if(!u.getConversations().contains(c)){
                    c.getUsers().add(u);
                    u.getConversations().add(c);
                    
                    c = this.conversationDB.edit(c);
                    u = this.accountDB.edit(u);
                    
                    output.put("Conversation", c);
                    output.put("Account", u);
                    
                }
                else{
                    throw new DBFacadeMainException("Conversation is already in account.");
                }
            }
            else{
                throw new DBFacadeMainException("Account is already in conversation");
            }
        }
        else{
            throw new DBFacadeMainException("Unknown input.");
        }
        
        return output; 
    }

    @Override
    public Map<String, Object> removeAccountFromConversation(Conversation c, Account a) {
        Map<String, Object> output = new HashMap<>();
        c = this.conversationDB.findConversation(c.getId());
        a = this.accountDB.findAccount(a.getId());

        if(c != null && a != null){
            if(!c.getUsers().contains(a)){
                if(!a.getConversations().contains(c)){
                    c.getUsers().remove(a);
                    a.getConversations().remove(c);
                    
                    c = this.conversationDB.edit(c);
                    a = this.accountDB.edit(a);
                    
                    output.put("Conversation", c);
                    output.put("Account", a);
                    
                }
                else{
                    throw new DBFacadeMainException("Conversation is already in account.");
                }
            }
            else{
                throw new DBFacadeMainException("Account is already in conversation");
            }
        }
        else{
            throw new DBFacadeMainException("Unknown input.");
        }
        
        return output; 
    }

    @Override
    public Account findAccount(long id) {
        return this.accountDB.findAccount(id);
    }
    @Override
    public Account findAccount(String username) {
        return this.accountDB.findAccount(username);
    }

    @Override
    public List<Account> findAllAccounts() {
        return this.accountDB.findAllAccounts();
    }

    @Override
    public Conversation findConversation(long id) {
        return this.conversationDB.findConversation(id);
    }

    @Override
    public List<Conversation> findAllConversations() {
        return this.conversationDB.findAllConversations();
    }

    @Override
    public Country findCountry(long id) {
        return this.countryDB.findCountry(id);
    }

    @Override
    public List<Country> findAllCountries() {
        return this.countryDB.findAllCountries();
    }

}

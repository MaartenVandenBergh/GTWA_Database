/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.facades.interfaces.remotes;

import domain.entities.Conversation;
import domain.entities.Country;
import domain.entities.Account;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author Maarten
 */
@Remote
public interface DBFacadeRemote {
    
    public void init();
    
    //Main functionality
    ////User
    public Account createAccount(Account a);
    public Account editAccount(Account a);
    public void removeAccount(Account a);
    public Account findAccount(long id);
    public Account findAccount(String username);
    public List<Account> findAllAccounts();
    
    ////Conversation
    public Conversation createConversation(Conversation c);
    public Conversation editConversation(Conversation c);
    public void removeConversation(Conversation c);
    public Conversation findConversation(long id);
    public List<Conversation> findAllConversations();
    
    ////Country
    public Country createCountry(Country c);
    public Country editCountry(Country c);
    public void removeCountry(Country c);
    public Country findCountry(long id);
    public List<Country> findAllCountries();
    
    //Bidirectional functionality
    //-Account
    //--Account
    public Map<String, Object> friendTwoAccounts(Account a1, Account a2);
    public Map<String, Object> unfriendTwoAccounts(Account a1, Account a2);
    
    //--Country
    public Account setCountryOfAccount(Country c, Account a);
    
    //-Conversation
    public Map<String, Object> addAccountToConversation(Conversation c, Account a);
    public Map<String, Object> removeAccountFromConversation(Conversation c, Account a);

}

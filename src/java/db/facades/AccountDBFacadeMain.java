/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.facades;

import db.facades.interfaces.AccountDBFacade;
import domain.entities.Country;
import domain.entities.Account;
import domain.enums.Status;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maarten
 */
public class AccountDBFacadeMain implements AccountDBFacade{
    
    private EntityManager em;

    public AccountDBFacadeMain(EntityManager em){
        this.em = em;
    }
    
    @Override
    public Account create(Account a) {
        em.persist(a);
        em.flush();
        return a;
    }

    @Override
    public Account edit(Account u) {
        u = em.merge(u);
        return u;
    }

    @Override
    public void remove(Account u) {
         em.remove(this.edit(u));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Account findAccount(long id) {
        return em.find(Account.class, id);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Account findAccount(String username) {
        Account account = null;
        List<Account> accounts = this.findAllAccounts();
        for(int i = 0;i<accounts.size();i++){
            if(accounts.get(i).getUsername().equals(username)){
                account = accounts.get(i);
            }
        }
        return account;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Account> findAllAccounts() {
        return em.createQuery("select object(a) from Account as a").getResultList();
    }
}

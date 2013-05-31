/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.facades;

import db.facades.interfaces.ConversationDBFacade;
import domain.entities.Conversation;
import domain.entities.Account;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maarten
 */
public class ConversationDBFacadeMain implements ConversationDBFacade{
    private EntityManager em;
    
    public ConversationDBFacadeMain(EntityManager em){
        this.em = em;
    }

    @Override
    public Conversation create(Conversation c) {
        em.persist(c);
        em.flush();
        return c;
    }

    @Override
    public Conversation edit(Conversation c) {
        c = em.merge(c);
        return c;
    }

    @Override
    public void remove(Conversation c) {
         em.remove(this.edit(c));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Conversation findConversation(long id) {
        return em.find(Conversation.class, id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Conversation> findAllConversations() {
        return em.createQuery("select object(c) from Conversation as c").getResultList();
    }
}

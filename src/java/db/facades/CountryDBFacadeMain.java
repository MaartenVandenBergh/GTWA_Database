/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.facades;

import db.facades.interfaces.CountryDBFacade;
import domain.entities.Country;
import domain.entities.Account;
import java.util.List;
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
public class CountryDBFacadeMain implements CountryDBFacade{
    
    private EntityManager em;

    public CountryDBFacadeMain(EntityManager em){
        this.em = em;
        
    }

    @Override
    public Country create(Country c) {
        em.persist(c);
        em.flush();
        return c;
    }

    @Override
    public Country edit(Country c) {
        c = em.merge(c);
        return c;
    }

    @Override
    public void remove(Country c) {
         em.remove(this.edit(c));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Country findCountry(long id) {
        return em.find(Country.class, id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Country> findAllCountries() {
        return em.createQuery("select object(c) from Country as c").getResultList();
    }
}

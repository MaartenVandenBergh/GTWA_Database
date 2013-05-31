/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.facades.interfaces;

import domain.entities.Country;
import java.util.List;

public interface CountryDBFacade{

    public Country create(Country c);
    public Country edit(Country c);
    public void remove(Country c);
    public Country findCountry(long id);
    public List<Country> findAllCountries();
}

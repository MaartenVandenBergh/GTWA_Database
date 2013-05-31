/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.entities;

import Exceptions.entities.CountryException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Maarten
 */
@Entity
public class Country implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private String flagPath;
    
    private List<Account> users;
    
    public Country(){
        
    }
    public Country(String name){
        this(name,"images/NoFlag.png");
    }
    
    public Country(String name, String path){
        this.setName(name);
        //this.setFlag(path);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) throws CountryException{
        if(name != null && name != ""){
            this.name = name;
        }
        else{
            throw new CountryException("Invalid name.");
        }
    }

    public Image getFlag() throws IOException {
        Image image = ImageIO.read(Country.class.getResourceAsStream("/Resources/Images/Flags/" + this.flagPath));
        
        return image;
    }

    public void setFlag(String flagPath) throws CountryException {
        if(flagPath != null){
            try{
                File sourceimage = new File(this.flagPath);
                Image image = ImageIO.read(sourceimage);
                
                this.flagPath = flagPath;
            }
            catch(IOException ex){
                throw new CountryException("Flag not found.");
            }
        }
        else{
            throw new CountryException("Invalid flag.");
        }
    }
    
    public List<Account> getUsers(){
        return this.users;
    }
    
    @Override
    public boolean equals(Object o){
        boolean equal = false;
        
        if(o instanceof Country){
            Country c = (Country)o;
            if(this.getName().equals(c.getName())){
                equal = true;
            }
        }
        return equal;
    }
}

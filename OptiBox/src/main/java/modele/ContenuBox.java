/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Collection;
import java.util.Deque;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author agpou
 */
@Entity
@Access(AccessType.FIELD)
public class ContenuBox extends TypeBox implements Serializable{
    
    @OneToMany(mappedBy="instanceProd",cascade = CascadeType.PERSIST)
    private Collection<PileProduit> setPileProduits;
    
    
    public ContenuBox(){
       // this.setPileProduits= new Collection<PileProduit>();
    }
}

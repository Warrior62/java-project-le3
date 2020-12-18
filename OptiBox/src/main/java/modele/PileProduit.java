/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author agpou
 */
@Entity
@Access(AccessType.FIELD)
public class PileProduit implements Serializable {

    @Id
    private Long id;

    @OneToMany(mappedBy="pileProd",cascade = CascadeType.PERSIST)
    private Deque<TypeProduit> pileProduits;

    public PileProduit() {
        this.pileProduits = new ArrayDeque<>();
    }

    public PileProduit(Deque<TypeProduit> pileProduits) {
        this.pileProduits = pileProduits;
    }


    
    
    public Deque<TypeProduit> getPileProduits() {
        return pileProduits;
    }
    public void setPileProduits(Deque<TypeProduit> pileProduits) {
        this.pileProduits = pileProduits;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
}

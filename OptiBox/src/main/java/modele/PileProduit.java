/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author agpou
 */
@Entity
@Access(AccessType.FIELD)
public class PileProduit implements Serializable {
        private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @ManyToOne
    private ContenuBox monContenuBox;
     
    @OneToMany(mappedBy="pileProd",cascade = CascadeType.PERSIST)
    private List<TypeProduit> pileProduits;
    
    

    public PileProduit() {
        this.pileProduits = new ArrayList<>();
    }

    public PileProduit(List<TypeProduit> pileProduits) {
        this();
        this.pileProduits = pileProduits;
    }


    

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<TypeProduit> getPileProduits() {
        return pileProduits;
    }
    public void setPileProduits(List<TypeProduit> pileProduits) {
        this.pileProduits = pileProduits;
    }
    public ContenuBox getMonContenuBox() {
        return monContenuBox;
    }
    public void setMonContenuBox(ContenuBox monContenuBox) {
        this.monContenuBox = monContenuBox;
    } 
}

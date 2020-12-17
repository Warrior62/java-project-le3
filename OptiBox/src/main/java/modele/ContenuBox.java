/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author agpou
 */
@Entity
@Access(AccessType.FIELD)
public class ContenuBox implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    
    @ManyToOne
    private PileProduit maPile;

    @OneToOne(mappedBy="monContenuBox")
    private TypeBox maBox;

    /**
     * Constructeur par défaut du contenu de box
     * On initialise le Set
     */
    public ContenuBox() {
        //this.setPileProduits = new HashSet();
    }
    
    /**
     * Constructeur par données du contenu de box
     * @param setPileProduits
     * @param maBox 
     */
    public ContenuBox(Collection<PileProduit> setPileProduits, TypeBox maBox) {
      //  this.setPileProduits = setPileProduits;
        this.maBox = maBox;
    }

    
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    
}

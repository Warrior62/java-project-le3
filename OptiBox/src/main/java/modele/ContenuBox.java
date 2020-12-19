/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
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
    
    /*@OneToMany(mappedBy="monContenuBox",cascade = CascadeType.PERSIST)
    private List<PileProduit> maListeProduits;*/

    @OneToOne(mappedBy="monContenuBox")
    private TypeBox maBox;

    /**
     * Constructeur par défaut du contenu de box
     * On initialise le Set
     */
    public ContenuBox() {
        this.maBox = new TypeBox();
        //this.maListeProduits = new ArrayList<>();
    }

    public ContenuBox(List<PileProduit> maListeProduits, TypeBox maBox) {
        this();
        //this.maListeProduits = maListeProduits;
        this.maBox = maBox;
    }


    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*public List<PileProduit> getMaListeProduits() {
        return maListeProduits;
    }
    public void setMaListeProduits(List<PileProduit> maListeProduits) {
        this.maListeProduits = maListeProduits;
    }*/
    public TypeBox getMaBox() {
        return maBox;
    }
    public void setMaBox(TypeBox maBox) {
        this.maBox = maBox;
    }
    
}

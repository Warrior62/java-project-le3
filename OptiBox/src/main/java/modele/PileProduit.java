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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private ContenuBox monContenuBox;
    
    @OneToMany(mappedBy="pileProd",cascade = CascadeType.PERSIST)
    private List<TypeProduit> listeProduits;
     
    

    public PileProduit() {
        this.listeProduits = new ArrayList<>();
        this.monContenuBox = new ContenuBox();
    }

    public PileProduit(List<TypeProduit> listeProduits) {
        this();
        this.listeProduits = listeProduits;
    }

    /**
     * Fonction qui permet de convertir une pile en liste pour l'ajout en bdd
     * @param pile : la pile à convertir en liste
     * @return list : la liste des produits
     */
    public void DequeToList(Deque<TypeProduit> pile) {
        int size = pile.size();
        TypeProduit p = new TypeProduit();
        for(int i=0;i<size;i++){
            p = pile.peekLast();
            this.listeProduits.add(p);
            pile.removeLastOccurrence(p);
        }
    }
    
    /**
     * Fonction qui permet de convertir une liste en pile après l'avoir récupérer de la bdd
     * @param list : la liste à transformer en pile
     * @return pile : la pile finale des produits
     */
    public Deque<TypeProduit> ListToDeque(List<TypeProduit> list) {
        Deque<TypeProduit> pileP = new ArrayDeque<TypeProduit>();
        for(TypeProduit p : list){
            pileP.push(p);
            list.remove(p);
        }
        return pileP;
    }


    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<TypeProduit> getPileProduits() {
        return listeProduits;
    }
    public void setPileProduits(List<TypeProduit> listeProduits) {
        this.listeProduits = listeProduits;
    }
    public ContenuBox getMonContenuBox() {
        return monContenuBox;
    }
    public void setMonContenuBox(ContenuBox monContenuBox) {
        this.monContenuBox = monContenuBox;
        monContenuBox.getMaListeProduits().add(this);
    }
}

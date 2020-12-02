/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author agpou
 */
@Entity
@Access(AccessType.FIELD)
public class Instance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NOM_INSTANCE",
            nullable=false)
    private String nom_instance;
    
    @OneToMany(mappedBy="instance_box",cascade = CascadeType.PERSIST)
    private Collection<TypeBox> liste_box;

    @OneToMany(mappedBy="instance_prod",cascade = CascadeType.PERSIST)
    private Collection<TypeProduit> liste_produit;
   
    
    /*
    * Contructeur par défaut de l'instance
    */
    public Instance() {
        this.nom_instance = "NOM INSTANCE";
        this.liste_box = new HashSet<>();
        this.liste_produit = new HashSet<>();
    }
    
    /**
     * Constructeur par données de l'instance
     * @param nom_instance : nom de l'instance
     */
    public Instance(String nom_instance) {
        this();
        this.nom_instance = nom_instance;
    }
    
    /**
     * Constructeur par données de l'instance
     * @param nom_instance : nom de l'instance
     * @param liste_box : liste de TypeBox
     * @param liste_produit : liste de TypeProduit
     */
    public Instance(String nom_instance, Collection<TypeBox> liste_box, Collection<TypeProduit> liste_produit) {
        this();
        this.nom_instance = nom_instance;
        this.liste_box = liste_box;
        this.liste_produit = liste_produit;
    }

    
    /********************************************
     ************ GETTER ET SETTER **************
     *******************************************/
    public String getNom_instance() {
        return nom_instance;
    }
    public void setNom_instance(String nom_instance) {
        this.nom_instance = nom_instance;
    }
    public Collection<TypeBox> getListe_box() {
        return liste_box;
    }
    public void setListe_box(Collection<TypeBox> liste_box) {
        this.liste_box = liste_box;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Collection<TypeProduit> getListe_produit() {
        return liste_produit;
    }
    public void setListe_produit(Collection<TypeProduit> liste_produit) {
        this.liste_produit = liste_produit;
    }  

    @Override
    public String toString() {
        return nom_instance ;
    }
    
}

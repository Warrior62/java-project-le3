/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToOne;

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
    private String nomInstance;
    
    @OneToMany(mappedBy="instanceBox",cascade = CascadeType.PERSIST)
    private List<TypeBox> setBox;

    @OneToMany(mappedBy="instanceProd",cascade = CascadeType.PERSIST)
    private List<TypeProduit> setProduits;
   
    @OneToOne(cascade = CascadeType.PERSIST)
    private Solution maSolution;
    
    
    
    /*
    * Contructeur par défaut de l'instance
    */
    public Instance() {
        this.nomInstance = "NOM INSTANCE";
        this.setBox = new ArrayList<>();
        this.setProduits = new ArrayList<>();
        //this.maSolution = new Solution();
    }
    
    /**
     * Constructeur par données de l'instance
     * @param nomInstance : nom de l'instance
     */
    public Instance(String nomInstance) {
        this();
        this.nomInstance = nomInstance;
    }
    
    /**
     * Constructeur par données de l'instance
     * @param nomInstance : nom de l'instance
     * @param setBox : liste de TypeBox
     * @param setProduits : liste de TypeProduit
     */
    public Instance(String nomInstance, List<TypeBox> setBox, List<TypeProduit> setProduits) {
        this();
        this.nomInstance = nomInstance;
        this.setBox = setBox;
        this.setProduits = setProduits;
    }

    @Override
    public String toString() {
        return nomInstance;
    }

    /********************************************
     ************ GETTER ET SETTER **************
     *******************************************/
    public String getNomInstance() {
        return nomInstance;
    }
    public void setNomInstance(String nomInstance) {
        this.nomInstance = nomInstance;
    }

    public List<TypeBox> getSetBox() {
        return setBox;
    }

    public void setSetBox(List<TypeBox> setBox) {
        this.setBox = setBox;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<TypeProduit> getSetProduits() {
        return setProduits;
    }
    public void setSetProduits(List<TypeProduit> setProduits) {
        this.setProduits = setProduits;
    }  
    public Solution getMaSolution() {
        return maSolution;
    }
    public void setMaSolution(Solution maSolution) {
        this.maSolution = maSolution;
    }
    
}

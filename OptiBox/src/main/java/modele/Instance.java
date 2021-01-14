/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
   
    @OneToOne(mappedBy="InstanceSolution")
    private Solution maSolution;
    
    
    
    /**
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

    /**
     * Méthode toString pour l'affichage d'une instance
     * @return nomInstance
     */
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
    
    /********************************************
     ************ METHODE **************
     *******************************************/
    
    /**
     * Fonction qui permet d'affecter une couleur à chaque produit de l'instance
     */
    public void setUneCouleurAChaqueProduit(){
        int oldGroup=-1; int actualGroup=-1;
        Color couleur = null;
        for(TypeProduit p : this.getSetProduits()){
            actualGroup = p.getGrpProduit();
            //si le groupe du produit n'est pas le même que celui d'avant
            if(oldGroup == -1 || oldGroup != actualGroup){
                couleur = RandomColor();
            }
            oldGroup = actualGroup;
            p.setCouleur(couleur);
        }
    }
    
    /**
     * Fonction prise de stack overflow pour avoir une couleur aléatoire
     * https://stackoverflow.com/questions/4246351/creating-random-colour-in-java
     * @return Color
     */
    public Color RandomColor(){
       Random rand = new Random();
       float r = rand.nextFloat();
       float g = rand.nextFloat();
       float b = rand.nextFloat();
       
       return new Color(r, g, b);
    }
    
}

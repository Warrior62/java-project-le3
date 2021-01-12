/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Color;
import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author agpou
 */
@Entity
@Table(
        uniqueConstraints={
            @UniqueConstraint(
                columnNames={"ID_P","INSTANCE_PROD_ID"}
            )
        }
)
@Access(AccessType.FIELD)
public class TypeProduit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="ID_P",
            nullable=false)
    private String idP;
    
    @Column(name="LONGUEUR_PRODUIT",
            nullable=false)
    private int Lproduit;
    
    @Column(name="HAUTEUR_PRODUIT",
            nullable=false)
     private int Hproduit;
    
    @Column(name="NB_PRODUITS",
            nullable=false)
     private int NBproduit;

    @Column(name="GROUPE_PRODUITS",
            nullable=false)
     private int GrpProduit;
    
    //test afin de pouvoir utiliser le fichier readerInstance
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="INSTANCE_PROD_ID")
    private Instance instanceProd;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PileProduit pileProd;
    
     private Color couleur;
    /**
     * Constructeur par défaut du type de produit
     * On définit la longuer, hauteur et quantité à 0
     */
    public TypeProduit() {
        this.Lproduit = 0;
        this.Hproduit = 0;
        this.NBproduit = 0;
        this.GrpProduit = 0;
        this.instanceProd = new Instance();
        this.pileProd = new PileProduit();
        this.couleur = Color.BLACK;
    }
    
    /**
     * Constructeur par données du type de produit
     * @param idP
     * @param Lproduit : longueur du produit
     * @param Hproduit : hauteur du produit
     * @param NBproduit : quantité du produit
     */
    public TypeProduit(String idP,int Lproduit, int Hproduit, int NBproduit) {
        this();
        if(idP != null) this.idP = idP;
        if(Lproduit != 0) this.Lproduit = Lproduit;
        if(Hproduit != 0) this.Hproduit = Hproduit;
        if(NBproduit != 0) this.NBproduit = NBproduit;
    }

    public TypeProduit(String idP, int Lproduit, int Hproduit, int NBproduit, int GrpProduit) {
        this();
        this.idP = idP;
        this.Lproduit = Lproduit;
        this.Hproduit = Hproduit;
        this.NBproduit = NBproduit;
        this.GrpProduit = GrpProduit;
    }

    

 
    

    /********************************************
     ************ GETTER ET SETTER **************
     *******************************************/
    public Instance getInstance() {
        return instanceProd;
    }
    public void setInstance(Instance instanceProd) {
        this.instanceProd = instanceProd;
        instanceProd.getSetProduits().add(this);
    }
    public String getId() {
        return idP;
    }
    public void setId(String id) {
        this.idP = id;
    }
    public int getLproduit() {
        return Lproduit;
    }
    public void setLproduit(int Lproduit) {
        this.Lproduit = Lproduit;
    }
    public int getHproduit() {
        return Hproduit;
    }
    public void setHproduit(int Hproduit) {
        this.Hproduit = Hproduit;
    }
    public int getNBproduit() {
        return NBproduit;
    }
    public void setNBproduit(int NBproduit) {
        this.NBproduit = NBproduit;
    }  
    public Color getCouleur() {
        return couleur;
    }
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    public PileProduit getPileProd() {
        return pileProd;
    }
    public void setPileProd(PileProduit pileProd) {
        this.pileProd = pileProd;
    }
    public int getGrpProduit() {
        return GrpProduit;
    }
    public void setGrpProduit(int GrpProduit) {
        this.GrpProduit = GrpProduit;
    }
    
}

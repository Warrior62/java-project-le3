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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author agpou
 */
@Entity
@Access(AccessType.FIELD)
public class TypeBox implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="ID_B",
            nullable=false)
    private String idB;
    
    @Column(name="LONGUEUR_BOX",
            nullable=false)
    private int Lbox;
    
    @Column(name="HAUTEUR_BOX",
            nullable=false)
     private int Hbox;
    
    @Column(name="PRIX_BOX",
            nullable=false)
     private double Prixbox;

    @ManyToOne
    @JoinColumn(name="INSTANCE_BOX_ID")
    private Instance instanceBox;
    
    @OneToOne
    private ContenuBox ContenuBox;
    
    private Color couleur;
    
    /**
     * Constructeur par défaut du type de box
     * On définit la longueur, la hauteur et le prix à 0
     */
    public TypeBox() {
        this.Lbox = 0;
        this.Hbox = 0;
        this.Prixbox = 0;
        this.instanceBox = new Instance();
        //this.ContenuBox = new ContenuBox();
        this.couleur = Color.BLUE;
    }
    
    /**
     * Constructeur par données du type de box
     * @param Lbox : longueur de la box
     * @param Hbox : hauteur de la box
     * @param Prixbox : prix de la box
     */
    public TypeBox(String idB,int Lbox, int Hbox, double Prixbox) {
        this();
        if(idB != null) this.idB = idB;
        if(Lbox != 0) this.Lbox = Lbox;
        if(Hbox != 0) this.Hbox = Hbox;
        if(Prixbox != 0) this.Prixbox = Prixbox;
    }

    

    /********************************************
     ************ GETTER ET SETTER **************
     *******************************************/
    public Instance getInstance() {
        return instanceBox;
    }
    public void setInstance(Instance C_instance) {
        this.instanceBox = C_instance;
    }
    public int getLbox() {
        return Lbox;
    }
    public void setLbox(int Lbox) {
        this.Lbox = Lbox;
    }
    public int getHbox() {
        return Hbox;
    }
    public void setHbox(int Hbox) {
        this.Hbox = Hbox;
    }
    public double getPrixbox() {
        return Prixbox;
    }
    public void setPrixbox(double Prixbox) {
        this.Prixbox = Prixbox;
    }
    public String getId() {
        return idB;
    }
    public void setId(String id) {
        this.idB = id;
    }
    public Color getCouleur() {
        return couleur;
    }
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    public Instance getInstanceBox() {
        return instanceBox;
    }
    public void setInstanceBox(Instance instanceBox) {
        this.instanceBox = instanceBox;
    }
    public ContenuBox getMonContenuBox() {
        return ContenuBox;
    }
    public void setMonContenuBox(ContenuBox ContenuBox) {
        this.ContenuBox = ContenuBox;
    }  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.ArrayList;
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
public class ContenuBox implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy="monContenuBox",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<PileProduit> maPileDeProduits;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private TypeBox maTypeBox;
    
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Solution nomSolution;

    /**
     * Constructeur par défaut du contenu de box
     * On initialise le Set
     */
    public ContenuBox() {
        //this.maTypeBox = new TypeBox();
        this.maPileDeProduits = new ArrayList<>();
        //this.nomSolution = new Solution();
    }

    /**
     * Constructeur par données du contenu de box
     * @param maListeProduits
     * @param maBox
     * @param nomSolution 
     */
    public ContenuBox(List<PileProduit> maListeProduits, TypeBox maBox, Solution nomSolution) {
        this();
        this.maPileDeProduits = maListeProduits;
        this.maTypeBox = maBox;
        this.nomSolution = nomSolution;
    }

    
    /********************************************
     ************ GETTER ET SETTER **************
     *******************************************/
    public Solution getNomSolution() {
        return nomSolution;
    }
    public void setNomSolution(Solution nomSolution) {
        this.nomSolution = nomSolution;
        nomSolution.getListeContenuBox().add(this);
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<PileProduit> getMaListeProduits() {
        return maPileDeProduits;
    }
    public void setMaListeProduits(List<PileProduit> maListeProduits) {
        this.maPileDeProduits = maListeProduits;
    }
    public TypeBox getMaTypeBox() {
        return maTypeBox;
    }
    public void setMaTypeBox(TypeBox maTypeBox) {
        this.maTypeBox = maTypeBox;
        maTypeBox.getListMesContenuBox().add(this);
    }
 
}

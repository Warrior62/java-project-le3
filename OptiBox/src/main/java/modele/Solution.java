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
public class Solution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NOM_SOLUTION",
            nullable=false)
    private String nomSolution;
    
    @Column(name="PRIX_FINAL",
            nullable=false)
    private Integer prixFinal;
    
    @OneToMany(mappedBy="nomSolution",cascade = CascadeType.PERSIST)
    private List<ContenuBox> listeContenuBox;
    
    @OneToOne(mappedBy="maSolution",cascade = CascadeType.PERSIST)
    private Instance InstanceSolution;

    
    public Solution() {
        this.nomSolution = "S000";
        this.prixFinal = 0;
        this.listeContenuBox = new ArrayList<>();
       // this.InstanceSolution = new Instance();
    }
    
    public Solution(String nomSolution, Integer prixFinal) {
        this();
        this.nomSolution = nomSolution;
        this.prixFinal = prixFinal;
    }
    
    public Solution(String nomSolution, Integer prixFinal, List<ContenuBox> listeContenuBox, Instance InstanceSolution) {
        this();
        this.nomSolution = nomSolution;
        this.prixFinal = prixFinal;
        this.listeContenuBox = listeContenuBox;
        this.InstanceSolution = InstanceSolution;
    }
    
    

    public String getNomSolution() {
        return nomSolution;
    }
    public void setNomSolution(String nomSolution) {
        this.nomSolution = nomSolution;
    }
    public List<ContenuBox> getListeContenuBox() {
        return listeContenuBox;
    }
    public void setListeContenuBox(List<ContenuBox> listeContenuBox) {
        this.listeContenuBox = listeContenuBox;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getPrixFinal() {
        return prixFinal;
    }
    public void setPrixFinal(Integer prixFinal) {
        this.prixFinal = prixFinal;
    }
    public Instance getInstanceSolution() {
        return InstanceSolution;
    }
    public void setInstanceSolution(Instance InstanceSolution) {
        this.InstanceSolution = InstanceSolution;
        InstanceSolution.setMaSolution(this);
    } 
  
}

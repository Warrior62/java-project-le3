/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import modele.ContenuBox;
import modele.TypeBox;
import modele.TypeProduit;
import modele.ReqBDD;
import modele.Solution;


/**
 *
 * @author jules
 */
public class TestSolution {
 
    private ReqBDD re;
    private Set<TypeBox> boxTest;
    private Set<TypeProduit> produitTest;
    
    private TypeBox boxMaxInstance;

    public TestSolution() {
        this.boxTest = new HashSet();
        this.produitTest = new HashSet<>();
        this.boxMaxInstance= new TypeBox();
        
         initConnexion();
    }
   
    private void initConnexion(){
        try
        {
           this.re= ReqBDD.getInstance(); 
        } catch(Exception ex){
            System.out.println("Connexion à la bdd impossible, vérifiez que vous êtes connecté");
            
        }
    }
    
    public Solution testSolution0() throws SQLException, ClassNotFoundException{
        long idInstance=1;
        int nbBox = 0;
        Solution s = new Solution();
        this.produitTest= re.findProdByInstanceId(idInstance);
        this.boxTest= re.findBoxByInstanceId(idInstance);
        
        //On récupère la box la plus grande
        for (TypeBox b : boxTest)
        {
            if(b.getLbox()*b.getHbox()>boxMaxInstance.getHbox()*boxMaxInstance.getLbox())
            {
                boxMaxInstance = b;
            }
        }
        
        
        // On va tout mettre dans cette box !!! 
        //UNE SEULE BOX D UTILE POUR LE TEST 0
        TypeProduit p;
        Object[] arrayItem = this.produitTest.toArray(); 
        
      while(this.produitTest!= null)
      {
          //Recupérer premier élément liste 
        
        //    p = (TypeProduit) arrayItem[0];
            //System.out.println(p.getLproduit());
          
          
          // On regarde si la liste est vide on ajoute notre boite
         /* if (s.getListeBoxs() == null){
            ContenuBox cb1 = new ContenuBox();
            nbBox++;
            s.setNbBoxs(nbBox);
        }*/
          
          
        //On remplit nos box
         // ETAPE 1 : prendre la box 0
         //ETAPE 2 on regarde la première pile dans a box 
          // on regarde si on peut empiler 
          //si oui on sort de la boucle
          //si non on continue on passe à la pile d'après 
          
          //ETAPE 3 : on ne peut pas mettre dans la box 0
          // On passe à la box 1 et on répète jusqu'à temps que ca marche
          
          // Si on sort de la boucle sans avoir ajouté
          // on crée une box et on l'ajoute dedans (on ajoute la box dans notre liste de box)
          
          
          
        //for()   
        //if(p.getHproduit()<= boxMaxInstance.getHbox())
        
        //Des que la box est remplie
        //s.Ajouter(cb1); // Créer fonction dans solution qui ajoute dans la liste
         
        
        
        // A décommenter des qu'on a résolu : Recupérer premier élément liste 
        this.produitTest.remove(p);
          
      }
     
      return s;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TestSolution te = new TestSolution();
        te.testSolution0(); 
    }
}

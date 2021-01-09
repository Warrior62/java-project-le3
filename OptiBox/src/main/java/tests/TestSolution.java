/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import modele.ContenuBox;
import modele.PileProduit;
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
    private List<TypeBox> boxTest;
    private List<TypeProduit> produitTest;
    
    private TypeBox boxMaxInstance;

    public TestSolution() {
        this.boxTest = new ArrayList<>();
        this.produitTest = new ArrayList<>();
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
    
    
    public List<TypeProduit> creerListeProduit(){
        
        List<TypeProduit> listeProd = new ArrayList<>();
        TypeProduit p1 =new TypeProduit("1",50,50,1); 
        TypeProduit p2 =new TypeProduit("2",30,60,1); 
        TypeProduit p3 =new TypeProduit("3",100,80,1); 
        TypeProduit p4 =new TypeProduit("4",20,20,2); 
        TypeProduit p5 =new TypeProduit("5",120,20,1); 
        
        listeProd.add(p1);
        listeProd.add(p2);
        listeProd.add(p3);
        listeProd.add(p4);
        listeProd.add(p5);
        
        //Trier liste
        //on peut trier la liste comme ça :
            Collections.sort(listeProd,(o1, o2)-> o2.getLproduit()-o1.getLproduit());
        //source : https://discord.com/channels/@me/704627618087043145/797384532017283113
        
        return listeProd;
    }
    
    public List<TypeBox> creerListeBox(){
        
        List<TypeBox> listBox = new ArrayList<>();
        TypeBox b1= new TypeBox("1", 150, 150, 200);
        TypeBox b2 = new TypeBox("2", 130, 130, 130);
        listBox.add(b2);
        listBox.add(b1);
        
         Collections.sort(listBox,(o1, o2)-> o2.getLbox()*o2.getHbox()-o1.getLbox()*o1.getHbox());
        
        return listBox;
    }
    
    
    public Solution testSolution0() throws SQLException, ClassNotFoundException{
        
        int nbBox = 0;
        Solution s = new Solution();
        
        
      //Si on veut récup de la bdd  
      //long idInstance=1;
      //  this.produitTest= re.findProdByInstanceId(idInstance);
      //  this.boxTest= re.findBoxByInstanceId(idInstance);
        
      
      // On récupère les produits triés selon la longueur !
      this.produitTest= creerListeProduit();
      this.boxTest=creerListeBox();
      
       //On récupère la box la plus grande
       // On va tout mettre dans cette box !!! 
       //UNE SEULE BOX D UTILE POUR LE TEST 0
      boxMaxInstance = this.boxTest.get(0);
         
      
      //On crée déjà une boîte
      
        ContenuBox cb1 = new ContenuBox();
        nbBox++;
        cb1.setMaTypeBox(boxMaxInstance);
        s.getListeContenuBox().add(cb1);
        // s.setPrixFinal(boxMaxInstance.getPrixbox()); PRIX

        
        
      while(this.produitTest.size()>0)
      {
          //Recupérer premier élément liste 
            TypeProduit p = this.produitTest.get(0);
          
           System.out.println("Box: " +boxMaxInstance.getLbox()); 
           System.out.println(p.getHproduit()+" Long:  "+p.getLproduit()); 
   
         //On remplit nos box
         
         
         // On met un flag pour savoir si il est placé
         int ProduitPlace=0;
          
         
          for(ContenuBox c : s.getListeContenuBox())
          {
              // ETAPE 1 : prendre la box 0
              
              if (c.getMaTypeBox().getLbox()>p.getLproduit() && c.getMaTypeBox().getHbox()>p.getHproduit())
              {
                  
                  System.out.println("On peut mettre le produit dans cette box");
                  
                  if(c.getMaListeProduits().size()==0){
                      PileProduit p1= new PileProduit();
                      p1.getPileProduits().add(p);
                      c.getMaListeProduits().add(p1);
                      System.out.println("On peut ajouter le produit dans la box 1");
                      ProduitPlace= 1;
                  }
                  
                  //ETAPE 2 on regarde la première pile dans a box 
          // on regarde si on peut empiler 
          //si oui on sort de la boucle
          //si non on continue on passe à la pile d'après 
        
                 // Si le produit est deja inséré, inutile qu'il recherche une pile
         /*   if (ProduitPlace == 0){     
            for(PileProduit pp : c.getMaListeProduits()) 
            {
               int hauteurMaxInserer =p.getHproduit();
               
                for (TypeProduit prodPile : pp.getPileProduits()){
                   
                hauteurMaxInserer += prodPile.getHproduit();
                if( prodPile.getLproduit() > p.getLproduit() && hauteurMaxInserer < c.getMaTypeBox().getHbox())
                {
                    pp.getPileProduits().add(p);
                    ProduitPlace=1;
                }
                    
                }
                                
            }
            
                 
                 }
            
            
            //On sort de la boucle qui vérifie chaque pile 
            //Maintenant on revérifie si le p a été ajouté
            // Le produit n'a pas été ajouté dans les piles existantes de la box
            //On regarde si on peut en créer une et l'insérer dans la box
            if (ProduitPlace == 0){
                int longueurRestante=0;
                for (PileProduit ppCalc : c.getMaListeProduits())
                {
                    longueurRestante += ppCalc.getPileProduits().get(0).getLproduit();
                }
                
            if ((c.getMaTypeBox().getLbox()-longueurRestante)>p.getLproduit())
            {
               PileProduit pp1= new PileProduit();
               pp1.getPileProduits().add(p);
               c.getMaListeProduits().add((pp1));
               ProduitPlace=1;
            }
            } */
            
              }
              else{
                  System.out.println("Ajout Impossible, Un des produits ne peut pas rentrer dans la boîte");
                  return null;
              }
              
          }
          
          //ETAPE 3 : on ne peut pas mettre dans la box 0
          // On passe à la box 1 et on répète jusqu'à temps que ca marche
          //BOUCLE FOR QUI FAIT CA
          
          // Si on sort de la boucle sans avoir ajouté
          // on crée une box et on l'ajoute dedans (on ajoute la box dans notre liste de box)
          if (ProduitPlace ==0){
              
               ContenuBox cb2 = new ContenuBox();
               cb2.setMaTypeBox(boxMaxInstance);
            nbBox++;
            PileProduit pp1= new PileProduit();
            pp1.getPileProduits().add(p);
            cb2.getMaListeProduits().add(pp1);
            s.getListeContenuBox().add(cb2);
            
              System.out.println("On a ajouté le produit dans une nouvelle box");
              
          }
          
          
        //for()   
        //if(p.getHproduit()<= boxMaxInstance.getHbox())
        
        //Des que la box est remplie
        //s.Ajouter(cb1); // Créer fonction dans solution qui ajoute dans la liste
         
          System.out.println("Nombre de box crées: "+ nbBox);
        
        this.produitTest.remove(p);
          
      }
     
      return s;
    }
    
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TestSolution te = new TestSolution();
        te.testSolution0(); 
    }
}

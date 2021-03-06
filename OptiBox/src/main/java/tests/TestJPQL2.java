/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Collections;
import java.util.List;
import modele.Algorithme;
import modele.ContenuBox;
import modele.Instance;
import modele.PileProduit;
import modele.Solution;
import modele.TypeBox;
import modele.TypeProduit;

/**
 *
 * @author agpou
 */
public class TestJPQL2 {
    
    public static Solution algorithme(Instance ins){
        //on crée une solution que l'on va return à la fin
        Solution solution = new Solution();
        //On fait le lien entre solution et instance
        solution.setInstanceSolution(ins);
        
        //on initialise les listes de TypeProduits et TypeBox
        List<TypeProduit> listProd = ins.getSetProduits();
        List<TypeBox> listBox = (List<TypeBox>) ins.getSetBox();
       //on trie les listes du plus grand au plus petit
        Collections.sort(listBox, (o1,o2) -> o2.getLbox()-o1.getLbox());
        Collections.sort(listProd, (o1,o2) -> o2.getLproduit()-o1.getLproduit());
        
        resolutionAlgo(listBox,listProd,solution);
        
        return solution;
    }
    
    public static void resolutionAlgo(List<TypeBox >listBox, List<TypeProduit> listProd,Solution solution){
        int largeurUtilise=0, hauteurUtilise=0, nbAjout=0;
        boolean flag=false;
        TypeProduit produit = null;
        //Création d'une ContenuBox
        ContenuBox contenu_box1 = new ContenuBox();
        //on fait la liaison dans les 2 sens entre ContenuBox et Solution
        contenu_box1.setNomSolution(solution);
        //on récupère la première box et on l'ajoute dans une ContenuBox
        TypeBox maBox = listBox.get(0);
        contenu_box1.setMaTypeBox(maBox);
        
        //Tant que le nbAjout est inférieur à la taille de la listeProduit
        int sizeP = listProd.size();
        System.out.println("size : "+sizeP);
        while(nbAjout < sizeP && flag == false){
            //System.out.println("nb ajout premiere boucle : "+nbAjout);
            //System.out.println("hauteurUtile while 1 : "+hauteurUtilise);
            //on prend le produit x 
            produit = listProd.get(nbAjout);
            //si la largeur utilise + largeurProduit est inférieur à la largeur de la box
            if( ( largeurUtilise+produit.getLproduit() ) < maBox.getLbox()){
                //on incrémente les valeurs de la largeur et hauteur
                largeurUtilise+=produit.getLproduit();
                hauteurUtilise+=produit.getHproduit();
                //on créé une pile et on ajoute le premier produit
                PileProduit pileP =new PileProduit();
                //on fait un lien bidirectionnel entre les deux
                pileP.getPileProduits().add(produit);
                produit.setPileProd(pileP);
                
                nbAjout++;
                //on prend le produit suivant dans la liste
                produit = listProd.get(nbAjout);
                //Tant que la hauteur de la pile ne dépasse pas la hauteur de la box
                //System.out.println("maBox hauteur "+maBox.getHbox());
                while(hauteurUtilise+produit.getHproduit() < maBox.getHbox() && flag == false){
                    //System.out.println("nb ajout deuxieme boucle : "+nbAjout);
                    //System.out.println("hauteurUtile while 2 : "+hauteurUtilise);
                    //System.out.println("maBox hauteur "+maBox.getHbox());
                    //System.out.println("Hauteur prod + utilise "+(hauteurUtilise+produit.getHproduit()));
                    //on ajoute le produit suivant de la liste
                    pileP.getPileProduits().add(produit);
                    produit.setPileProd(pileP);

                    hauteurUtilise+=produit.getHproduit();
                    nbAjout++;
                    //on regarde si il y a toujours un produit, sinon c'est fini
                    if(nbAjout < sizeP){
                        produit = listProd.get(nbAjout);
                    }
                    //si c'est le dernier on passe le flag à 1 pour quitter les boucles while
                    else flag = true;
                }
                //on fait le lien entre contenuBox et les Piles
                //contenu_box1.getMaListeProduits().add(pileP);
                pileP.setMonContenuBox(contenu_box1);
                hauteurUtilise=0;
            }
            else{
                hauteurUtilise=0; largeurUtilise=0;
                contenu_box1 = new ContenuBox();
                //on fait la liaison dans les 2 sens entre ContenuBox et Solution
                contenu_box1.setNomSolution(solution);
                //on récupère la première box et on l'ajoute dans une ContenuBox
                maBox = listBox.get(0);
                contenu_box1.setMaTypeBox(maBox);
            }
        }
        System.out.println("nb contenuBox : "+solution.getListeContenuBox().size());
        for(ContenuBox cb : solution.getListeContenuBox()){
            System.out.println("nb pile : "+cb.getMaListeProduits().size());
        }
    }
    
    public static void main(String[] args) {
        //création de TypeProduits
        TypeProduit p1 = new TypeProduit("P001_0",200,300,4);    
        TypeProduit p2 = new TypeProduit("P001_1",200,300,4);    
        TypeProduit p3 = new TypeProduit("P001_2",200,300,4);  
        
        TypeProduit p4 = new TypeProduit("P002_0",23,50,2);
        
        TypeProduit p5 = new TypeProduit("P003_0",90,290,2);
        
        TypeProduit p6 = new TypeProduit("P004_0",300,160,1);
        
        TypeProduit p7 = new TypeProduit("P005_0",250,100,1);
        
        TypeProduit p8 = new TypeProduit("P006_0",100,100,4);    
        TypeProduit p9 = new TypeProduit("P006_1",100,100,4);    
        TypeProduit p10 = new TypeProduit("P006_2",100,100,4);  
        TypeProduit p11 = new TypeProduit("P006_3",100,100,4);
        
        TypeProduit p12 = new TypeProduit("P007_1",90,230,1);
        
        TypeProduit p13 = new TypeProduit("P008_0",80,150,3);
        TypeProduit p14 = new TypeProduit("P008_1",80,150,3);
        TypeProduit p15 = new TypeProduit("P008_2",80,150,3);
        
        TypeProduit p16 = new TypeProduit("P009_0",120,170,2);
        TypeProduit p17 = new TypeProduit("P009_1",120,170,2);
        
        TypeProduit p18 = new TypeProduit("P0010_0",160,160,1);
        
        TypeProduit p19 = new TypeProduit("P0011_0",330,220,2);
        TypeProduit p20 = new TypeProduit("P0012_0",330,220,2);

        //création d'une TypeBox
        TypeBox b1 = new TypeBox("B001",40,20,8);
        TypeBox b2 = new TypeBox("B002",300,200,8);
        TypeBox b3 = new TypeBox("B003",400,300,10);


        //on crée une instance dans laquelle on ajoute les TypeBox et TypeProduits
        Instance ins = new Instance("I1000");
        //liste de box dans instance
        b1.setInstance(ins);
        b2.setInstance(ins);
        b3.setInstance(ins);
        //liste de produits dans l'instance, liste qui n'est pas triée
        p1.setInstance(ins);
        p2.setInstance(ins);
        p3.setInstance(ins);
        p4.setInstance(ins);
        p5.setInstance(ins);
        p6.setInstance(ins);
        p7.setInstance(ins);
        p8.setInstance(ins);
        p9.setInstance(ins);
        p10.setInstance(ins);
        p11.setInstance(ins);
        p12.setInstance(ins);
        p13.setInstance(ins);
        p14.setInstance(ins);
        p15.setInstance(ins);
        p16.setInstance(ins);
        p17.setInstance(ins);
        p18.setInstance(ins);
        p19.setInstance(ins);
        p20.setInstance(ins);
        
        Solution sol_final = Algorithme.algorithme(ins);
        Algorithme.ajoutSolutionBDD(sol_final);
        
    }
    
    
}

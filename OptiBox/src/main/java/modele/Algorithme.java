/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author agpou
 */
public class Algorithme {
    /**
     * @name Solution algorithme(Instance ins) 
     * Fonction d'un algorithme version 1 qui prend en paramètre une instance 
     * Dans cet algorithme, on tri les TypeBox et TypeProduit du plus grand au plus petit
     * et on envoie ces listes dans la fonction de résolution
     * @param ins : l'instance
     * @return 
     */
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
    
    /**
     * @name resolutionAlgo(List<TypeBox >listBox, List<TypeProduit> listProd,Solution solution)
     * Fonction qui permet de générer une solution, on prend toujours la plus grande box
     * puis on insère les produits du plus grand au plus petit (ils sont triés). Dès que la box est
     * pleine on change de box mais elle fait toujours la même taille.
     * @param listBox : liste des box de l'instance
     * @param listProd : liste des produits de l'instance
     * @param solution : solution de cette instance
     */
    public static void resolutionAlgo(List<TypeBox >listBox, List<TypeProduit> listProd,Solution solution){
        int largeurUtilise=0, hauteurUtilise=0, nbAjout=0;
        double prixFinalSolu = 0;
        boolean flag=false;
        TypeProduit produit = null;
        //Création d'une ContenuBox
        ContenuBox contenu_box1 = new ContenuBox();
        //on fait la liaison dans les 2 sens entre ContenuBox et Solution
        contenu_box1.setNomSolution(solution);
        //on récupère la première box et on l'ajoute dans une ContenuBox
        TypeBox maBox = listBox.get(0);
        contenu_box1.setMaTypeBox(maBox);
        //on ajoute le prix de la box dans le prix final
        prixFinalSolu = maBox.getPrixbox();
        //System.out.println("prix 1 :"+prixFinalSolu);
        
        //Tant que le nbAjout est inférieur à la taille de la listeProduit
        int sizeP = listProd.size();
        System.out.println("nb produits : "+sizeP);
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
                if(nbAjout < sizeP){
                        produit = listProd.get(nbAjout);
                    }
                //si c'est le dernier on passe le flag à 1 pour quitter les boucles while
                else flag = true;
                
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
                //on ajoute le prix de la box dans le prix final
                prixFinalSolu += maBox.getPrixbox();
                //System.out.println("prix 2 :"+prixFinalSolu);
            }
        }
        //on set le prix final dans la solution
        solution.setPrixFinal(prixFinalSolu);
        
        System.out.println("nb contenuBox : "+solution.getListeContenuBox().size());
        for(ContenuBox cb : solution.getListeContenuBox()){
            System.out.println("nb pile : "+cb.getMaListeProduits().size());
            System.out.println("largeur box : "+ cb.getMaTypeBox().getLbox()+" | hauteur box : "+cb.getMaTypeBox().getHbox());
            for(PileProduit pileP : cb.getMaListeProduits()){
                System.out.println(pileP.toString());
                for(TypeProduit p : pileP.getPileProduits()){
                    System.out.println(p.toString());
                }
                System.out.println("\n");
            }
        }

        System.out.println("prix total : "+solution.getPrixFinal());

    }
    
    /**
     * @name ajoutSolutionBDD(Solution solution)
     * Ajoute la solution générée par l'algorithme en BDD
     * @param solution : solution de 'instance
     */
    public static void ajoutSolutionBDD(Solution solution) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        final EntityManager em = emf.createEntityManager();
        
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                em.persist(solution);
                
                System.out.println("Ajout de la solution dans la bdd réussi !!");
                et.commit();
            } catch (Exception ex) {
                et.rollback();
                System.out.println("erreur : "+ex);
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
    
    
    
}

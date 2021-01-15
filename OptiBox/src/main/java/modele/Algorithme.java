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
        //Tant que le nbAjout est inférieur à la taille de la listeProduit
        int sizeP = listProd.size();
        System.out.println("nb produits : "+sizeP);
        while(nbAjout < sizeP && flag == false){
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
                while(hauteurUtilise+produit.getHproduit() < maBox.getHbox() && flag == false){
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
                String nomInstance = solution.getInstanceSolution().getNomInstance();
                solution.setNomSolution("S_"+nomInstance);
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
    
    public static Solution algorithme_v2(Instance i){
        List<TypeBox> boxTest = i.getSetBox();
        // On récupère les produits triés selon la longueur !  
        List<TypeProduit> produitTest = i.getSetProduits();
        
        Solution s = new Solution();
        s.setInstanceSolution(i);
        int prixFinal = 0;
        int nbBox = 0;

        //On crée déjà une boîte
        ContenuBox cb1 = new ContenuBox();
        cb1.setNomSolution(s);
        nbBox++;
        TypeBox boxMaxInstance = boxTest.get(boxTest.size() - 1);
        cb1.setMaTypeBox(boxMaxInstance);
        TypeProduit p0 = produitTest.get(0);
       
        if (cb1.getMaListeProduits().isEmpty()) {    
            PileProduit p1 = new PileProduit();
            p1.setMonContenuBox(cb1);
            p1.getPileProduits().add(p0);
            p0.setPileProd(p1);
            cb1.getMaListeProduits().add(p1);
            
            produitTest.remove(p0);
            /*if (p0.getNBproduit() == 1) produitTest.remove(p0);
            else p0.setNBproduit(p0.getNBproduit() - 1);*/

            //Remplissage au max premiere box 
            int longueurRestante0 = p0.getLproduit();
            List<TypeProduit> poubelle = new ArrayList<>();
            int place0 = 0;
            
            for (TypeProduit p : produitTest) {   
                if ((cb1.getMaTypeBox().getLbox() - longueurRestante0) == p.getLproduit()) {
                    PileProduit p2 = new PileProduit();
                    p2.getPileProduits().add(p);
                    p.setPileProd(p2);
                    cb1.getMaListeProduits().add(p2);
                    place0 = 1;
                }

                if (place0 == 0) {
                    for (PileProduit pp0 : cb1.getMaListeProduits()) {
                        if (p0.getLproduit() == p.getLproduit() && p.getHproduit() + p0.getHproduit() <= cb1.getMaTypeBox().getHbox() && place0 == 0) {
                            pp0.getPileProduits().add(p);
                            p.setPileProd(pp0);
                            place0 = 1;
                        }
                    }
                }
                if (place0 == 1) {
                    //if (p.getNBproduit() == 1) {
                       poubelle.add(p);
                    //} else p.setNBproduit(p.getNBproduit() - 1);
                }
            }

            if (poubelle.size() > 0) {
                for (TypeProduit poub : poubelle) {
                    produitTest.remove(poub);
                }
                poubelle.clear();
            }
        }

        s.getListeContenuBox().add(cb1);
        prixFinal += boxMaxInstance.getPrixbox();
        s.setPrixFinal(prixFinal);
     
        while (produitTest.size() > 0) {
            //Recupérer premier élément liste 
            TypeProduit p = produitTest.get(0);
            System.out.println(" ");
            System.out.println("Produit : Hauteur : " + p.getHproduit() + " Longueur :  " + p.getLproduit());
            int ProduitPlace = 0;

            for (ContenuBox c : s.getListeContenuBox()) {
                // ETAPE 1 : prendre la box 0  
                System.out.println("passer la");
                if (c.getMaTypeBox().getLbox() >= p.getLproduit() && c.getMaTypeBox().getHbox() >= p.getHproduit()) {
                    if (ProduitPlace == 0) {
                        //System.out.println("On regarde la pile du contenuBox sa taille est de "+c.getMaListeProduits().get(0).getPileProduits().size());
                        for (PileProduit pp : c.getMaListeProduits()) {
                            int hauteurMaxInserer = p.getHproduit();
                            TypeProduit produitHauteur = new TypeProduit();

                            for (TypeProduit prodPile : pp.getPileProduits()) {
                                hauteurMaxInserer += prodPile.getHproduit();
                                produitHauteur = prodPile;
                            }
                            if (produitHauteur.getLproduit() >= p.getLproduit() && hauteurMaxInserer <= c.getMaTypeBox().getHbox()) {
                                pp.getPileProduits().add(p);
                                p.setPileProd(pp);
                                ProduitPlace = 1;
                            }
                        }
                    }
                 
                    if (ProduitPlace == 0) {
                        int longueurRestante = 0;
                        for (PileProduit ppCalc : c.getMaListeProduits()) {
                            longueurRestante += ppCalc.getPileProduits().get(0).getLproduit();
                        }
                        if ((c.getMaTypeBox().getLbox() - longueurRestante) >= p.getLproduit()) {
                            PileProduit pp1 = new PileProduit();
                            pp1.getPileProduits().add(p);
                            p.setPileProd(pp1);
                            c.getMaListeProduits().add((pp1));
                            ProduitPlace = 1;
                        }
                    }

                } else {
                    System.out.println("Ajout Impossible, Un des produits ne peut pas rentrer dans la boîte");
                    return null;
                }
            }

            if (ProduitPlace == 0) {
                ContenuBox opti = new ContenuBox();
                int nbBoxOpti = -1;
                List<TypeProduit> poubelle = new ArrayList<>();
                
                for ( TypeBox box : boxTest)
                { 
                    ContenuBox cb2 = new ContenuBox();
                    int nombreBoite = 0;
                    /*int boiteTrouve = 0;
                    int n = 1;
                    while (boiteTrouve == 0) {
                        if (boxTest.size() < n) {
                            boxMaxInstance = boxTest.get(0);
                            boiteTrouve = 1;
                        }
                        if (boiteTrouve == 0) {
                            TypeProduit pDernier = produitTest.get(produitTest.size()-1);
                            if (boxMaxInstance.getLbox() - p.getLproduit() < pDernier.getLproduit()) {
                                n++;
                            } else boiteTrouve = 1;
                            boxMaxInstance = boxTest.get(boxTest.size() - n);
                        }
                    }*/

                    cb2.setMaTypeBox(box);
                    nbBox++;
                    PileProduit pp1 = new PileProduit();
                    pp1.getPileProduits().add(p);
                    p.setPileProd(pp1);
                    cb2.getMaListeProduits().add(pp1);

                    int longueurRestante0 = p.getLproduit();
                    int place = 0;
                
                    for (TypeProduit prod : produitTest) {
                        for (PileProduit pp2 : cb1.getMaListeProduits()) {
                            if ((cb1.getMaTypeBox().getLbox() - longueurRestante0) == p.getLproduit()) {
                                PileProduit p2 = new PileProduit();
                                pp2.getPileProduits().add(prod);
                                prod.setPileProd(pp2);
                                cb1.getMaListeProduits().add(p2);
                                place = 1;
                            }

                            if (p.getLproduit() == prod.getLproduit() && p.getHproduit() + prod.getHproduit() <= cb1.getMaTypeBox().getHbox() && place == 0) {
                                pp2.getPileProduits().add(prod);
                                prod.setPileProd(pp2);
                                place = 1;
                            }

                            if (place == 1) {
                                nombreBoite++;
                                //if (p.getNBproduit() == 1) {
                                    poubelle.add(p);
                                //} 
                                //else p.setNBproduit(p.getNBproduit() - 1);
                            }
                        }
                    }

                    if (nombreBoite > nbBoxOpti) {
                        opti = cb2;
                        nbBoxOpti = nombreBoite;
                    }
                }

                if (poubelle.size() > 0) {
                    for (TypeProduit ppp : poubelle) {
                        produitTest.remove(ppp);
                    }
                    poubelle.clear();
                }
                //s.getListeContenuBox().add(opti);
                opti.setNomSolution(s);
                prixFinal += boxMaxInstance.getPrixbox();
                s.setPrixFinal(prixFinal);
            }

            System.out.println("Nombre de box crées: " + nbBox);
            //Ici si la quantité égale 1 on supprime le produit sinon on baisse sa quantité de 1
            //if (p.getNBproduit() == 1) {
                produitTest.remove(p);
            /*} else {
                int nombreProd = p.getNBproduit();
                nombreProd--;
                p.setNBproduit(nombreProd);
            }*/
        }

        System.out.println(" ");
        System.out.println("Nombre de box crées avec cette solution : " + nbBox);
        System.out.println("Prix solution : " + s.getPrixFinal());

        return s;
    }
    
}

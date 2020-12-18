/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import modele.*;


/**
 *
 * @author agpou
 */
public class AjoutSolution {
    /**
     * même principe que AjoutBDD()
     * création d'une pile puis convertion en list pour pouvoir ajouter en bdd
     * Soit un cast en list
     * ou on parcout la pile et on met dans une liste
     */
    
     public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        final EntityManager em = emf.createEntityManager();
        
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                TypeProduit p1 = new TypeProduit("P001",20,10,4);    
                TypeProduit p2 = new TypeProduit("P002",23,26,4);
                TypeProduit p3 = new TypeProduit("P003",230,260,4);
                TypeProduit p4 = new TypeProduit("P004",200,100,4);

                TypeBox b1 = new TypeBox("B001",40,20,8);
                TypeBox b2 = new TypeBox("B002",60,50,16);

                Instance ins = new Instance("I1000");
                ins.getSetBox().add(b2);
                ins.getSetBox().add(b1);

                b2.setInstance(ins);
                b1.setInstance(ins);

                ins.getSetProduits().add(p2);
                ins.getSetProduits().add(p1);
                ins.getSetProduits().add(p3);

                p2.setInstance(ins);
                p1.setInstance(ins);
                p3.setInstance(ins);
                
               /* Deque<TypeProduit> Pile1 = new ArrayDeque<TypeProduit>();
                Pile1.push(p2); Pile1.push(p1);
                
                Deque<TypeProduit> Pile2 = new ArrayDeque<TypeProduit>();
                Pile2.push(p3); Pile2.push(p4);
                
                PileProduit PileProd1 = new PileProduit((List<TypeProduit>) Pile1);
                p1.setPileProd(PileProd1);
                p2.setPileProd(PileProd1);
                
                PileProduit PileProd2 = new PileProduit((List<TypeProduit>) Pile2);
                p3.setPileProd(PileProd2);
                p4.setPileProd(PileProd2);
                
                ContenuBox cb1 = new ContenuBox();
                cb1.getMaListeProduits().add(PileProd1);
                cb1.getMaListeProduits().add(PileProd2);
                cb1.setMaBox(b1);
                b1.setMonContenuBox(cb1);
                
                PileProd1.setMonContenuBox(cb1);
                PileProd2.setMonContenuBox(cb1);
*/
                em.persist(ins);
                //em.persist(cb1);
                
                
                et.commit();
            } catch (Exception ex) {
                et.rollback();
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

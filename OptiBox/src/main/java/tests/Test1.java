/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import modele.Instance;
import modele.TypeBox;
import modele.TypeProduit;

/**
 *
 * @author agpou
 */
public class Test1 {
    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        final EntityManager em = emf.createEntityManager();
        
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                
                TypeProduit p1 = new TypeProduit("P001",20,10,3);    
                TypeProduit p2 = new TypeProduit("P002",23,26,8);
                TypeProduit p3 = new TypeProduit("P003",30,15,12);
                
                TypeBox b1 = new TypeBox("B001",40,20,8);
                TypeBox b2 = new TypeBox("B002",60,50,16);
                TypeBox b3 = new TypeBox("B003",100,60,20);

                Instance ins = new Instance("I1000");
                ins.getListe_box().add(b3);
                ins.getListe_box().add(b2);
                ins.getListe_box().add(b1);

                b3.set_instance(ins);
                b2.set_instance(ins);
                b1.set_instance(ins);

                ins.getListe_produit().add(p3);
                ins.getListe_produit().add(p2);
                ins.getListe_produit().add(p1);

                p3.set_instance(ins);
                p2.set_instance(ins);
                p1.set_instance(ins);

                
                em.persist(ins);

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

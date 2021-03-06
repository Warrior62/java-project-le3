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
                
                /*TypeProduit p1 = new TypeProduit("P001",20,10,3);    
                TypeProduit p2 = new TypeProduit("P002",23,26,8);
                TypeProduit p3 = new TypeProduit("P003",30,15,12);
                
                TypeBox b1 = new TypeBox("B001",40,20,8);
                TypeBox b2 = new TypeBox("B002",60,50,16);
                TypeBox b3 = new TypeBox("B003",100,60,20);

                Instance ins = new Instance("I1000");
                ins.getSetBox().add(b3);
                ins.getSetBox().add(b2);
                ins.getSetBox().add(b1);

                b3.setInstance(ins);
                b2.setInstance(ins);
                b1.setInstance(ins);

                ins.getSetProduits().add(p3);
                ins.getSetProduits().add(p2);
                ins.getSetProduits().add(p1);

                p3.setInstance(ins);
                p2.setInstance(ins);
                p1.setInstance(ins);*/
                TypeProduit p1 = new TypeProduit("P002-1",23,26,8,2);
                TypeProduit p2 = new TypeProduit("P002-2",23,26,8,2);
                TypeBox b1 = new TypeBox("B001",40,20,8);
                Instance ins = new Instance("I1000");
                b1.setInstance(ins);
                p2.setInstance(ins);
                p1.setInstance(ins);

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

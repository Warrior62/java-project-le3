/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import modele.Instance;
import modele.InstanceReader;
import modele.TypeBox;
import modele.TypeProduit;

/**
 *
 * @author agpou
 */
public class AjoutBDD {
    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        final EntityManager em = emf.createEntityManager();
        
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                InstanceReader reader = new InstanceReader();
                Set<Instance> list_ins = reader.lire_instances();
                System.out.println("Liste des instances présentes dans le dossier :");
                for(Instance c : list_ins){
                    System.out.println(c.getNom_instance());
                    em.persist(c);
                }
                System.out.println("Ajout des instances dans la bdd réussi !!");
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

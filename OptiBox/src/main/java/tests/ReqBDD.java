/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modele.Instance;

/**
 *
 * @author tryla
 */
public class ReqBDD {
    private static Instance findInstanceByName(String nomInstance)
    {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        final EntityManager em = emf.createEntityManager();
        try
        {
            final EntityTransaction et = em.getTransaction(); 
            try
            {
                et.begin();
                final String strQuery = "SELECT inst FROM Instance inst"
                        + " WHERE inst.nom_instance = :nomInstance ";
                Query queryTest = em.createQuery(strQuery);
                queryTest.setParameter("nomInstance", nomInstance);
                List<Instance> listeInstances = queryTest.getResultList();

                et.commit();
                return listeInstances.get(0);
            } 
            catch (Exception ex) 
            {
                et.rollback();
                System.out.println(ex);
            }
        }
        finally 
        {
            if(em != null && em.isOpen()){
                em.close();
            }
            if(emf != null && emf.isOpen()){
                emf.close();
            }
        } 
        return new Instance();
    }
    
    public static void main(String[] args) {
        findInstanceByName("C001");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modele.Instance;

/**
 *
 * @author agpou
 */
public class TestJPQL1 {
    static EntityManagerFactory emf;
    static EntityManager em;

    /**
     * Fonction qui permettra d'afficher toutes les instances dans une liste sur l'interface
     */
    static void DisplayInstance(){
        final String strQuery = "SELECT ins.nom_instance FROM Instance ins";
        Query query = em.createQuery(strQuery);
        List<String> MesInstances = query.getResultList();

        for(String i : MesInstances){
            System.out.println("instance " +i);
        }
    }
    
    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        em = emf.createEntityManager();
        DisplayInstance();
    }
}

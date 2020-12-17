/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modele.Instance;
import modele.TypeBox;
import modele.TypeProduit;

/**
 *
 * @author tryla
 */
public class ReqBDD {
    private static ReqBDD req;

    public ReqBDD() {
    }
    
    
     public static ReqBDD getInstance() {
        if (req == null) {
                req = new ReqBDD();   
        }
        return req;
    }
        
    /**
     * @def public static Instance findInstanceByName(String nomInstance)
     * @brief sélectionne l'Instance dans la Table Instance
     *        dont le nom est nomInstance puis retourne l'objet Instance
     *        en question
     * @note s'il y a un pb dans la compilation de cette méthode, cette dernière
     *       renvoie une nouvelle Instance vide
     * @param nomInstance
     * @return 
     */
    public static Instance findInstanceByName(String nomInstance)
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
    
    /**
     * @def public static List<Instance> findAllInstances()
     * @brief sélectionne l'ensemble des Instances dans la Table Instance
     *        puis les stock dans une List qu'il retourne
     * @note s'il y a un pb dans la compilation de cette méthode, cette dernière
     *       renvoie une ArrayList vide
     * @return une List contenant toutes les Instances dans la Table Instance
     */
    public static List<Instance> findAllInstances()
    {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        final EntityManager em = emf.createEntityManager();
        try
        {
            final EntityTransaction et = em.getTransaction(); 
            try
            {
                et.begin();
                final String strQuery = "SELECT inst FROM Instance inst";
                Query queryTest = em.createQuery(strQuery);
                List<Instance> listeInstances = queryTest.getResultList();

                et.commit();
                return listeInstances;
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
        return new ArrayList<Instance>();
    }
     /**
     * @def public static List<TypeBox> findBoxesByInstanceId(int idInstance)
     * @brief sélectionne tous les objets TypeBox dont l'id de l'Instance qui
     *        leur est à chacun associée, est égal à idInstance
     * @param idInstance
     * @note s'il y a un pb dans la compilation de cette méthode, cette dernière
     *       renvoie une ArrayList de TypeBox vide
     * @return une List de TypeBox dont l'id est idInstance
     */
    public static List<TypeBox> findBoxesByInstanceId(int idInstance)
    {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        final EntityManager em = emf.createEntityManager();
        try
        {
            final EntityTransaction et = em.getTransaction(); 
            try
            {
                et.begin();
                final String strQuery = "SELECT tb FROM TypeBox tb"
                        + " WHERE tb.instance_box.id = :idInstance";
                Query queryTest = em.createQuery(strQuery);
                queryTest.setParameter("idInstance", idInstance);
                List<TypeBox> listeTypeBox = queryTest.getResultList();

                et.commit();
                return listeTypeBox;
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
        return new ArrayList<TypeBox>();
    }
    
    /**
     * @def public static List<TypeProduit> findProductsByInstanceId(int idInstance)
     * @brief sélectionne tous les objets TypeProduit dont l'id de l'Instance qui
     *        leur est à chacun associée, est égal à idInstance
     * @param idInstance
     * @note s'il y a un pb dans la compilation de cette méthode, cette dernière
     *       renvoie une ArrayList de TypeProduit vide
     * @return une List de TypeProduit dont l'id est idInstance
     */
    public static List<TypeProduit> findProductsByInstanceId(int idInstance)
    {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        final EntityManager em = emf.createEntityManager();
        try
        {
            final EntityTransaction et = em.getTransaction(); 
            try
            {
                et.begin();
                final String strQuery = "SELECT tp FROM TypeProduit tp"
                        + " WHERE tp.instance_prod.id = :idInstance";
                Query queryTest = em.createQuery(strQuery);
                queryTest.setParameter("idInstance", idInstance);
                List<TypeProduit> listeTypeProduit = queryTest.getResultList();

                et.commit();
                return listeTypeProduit;
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
        return new ArrayList<TypeProduit>();
    }
    
    /**
     * @def public static ArrayList<String> findSmallestProduct(int idInstance)
     * @brief sélectionne le(s) produit(s) ayant la surface la plus petite 
     *          parmi tous les produits de l'instance idInstance
     * @note renvoie une liste vide si pb à la compilation
     * @param idInstance
     * @return la liste de l'id du ou des produit(s) dont la surface est la plus petite
     */
    public static ArrayList<String> findSmallestProduct(int idInstance)
    {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        final EntityManager em = emf.createEntityManager();
        try
        {
            final EntityTransaction et = em.getTransaction(); 
            try
            {
                et.begin();
                final String strQuery = "SELECT tp FROM TypeProduit tp"
                        + " WHERE tp.instance_prod.id = :idInstance";
                Query queryTest = em.createQuery(strQuery);
                queryTest.setParameter("idInstance", idInstance);
                List<TypeProduit> smallestProduct = queryTest.getResultList();
                
                Map<String, Integer> multiplications = new HashMap<String, Integer>();
                for(TypeProduit tp: smallestProduct)
                    multiplications.put(tp.getId(), tp.getHproduit()*tp.getLproduit());
                int min = Collections.min(multiplications.values());
                List<String> listeIdMin = new ArrayList<String>();
                Set mapSet = multiplications.entrySet();
                Iterator mapIterator = mapSet.iterator();
                String idMin = "erreur";
                while(mapIterator.hasNext()){
                    Map.Entry mapEntry = (Map.Entry) mapIterator.next();
                    String keyValue = (String) mapEntry.getKey();
                    Integer value = (Integer) mapEntry.getValue();
                    if(value == min) listeIdMin.add(keyValue);
                }
                
                et.commit();
                
                
//                final String strQueryFinal = "select tp.idP from TypeProduit tp where (:produitRes)="
//                        + "(select :minProduit from TypeProduit where tp.instance_prod.id = :idInstance)";

                return (ArrayList<String>) listeIdMin;
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
        return new ArrayList<String>();
    }
    
    

 
      
    public static void main(String[] args) {
//        findInstanceByName("C001");
//        for(Instance i : findAllInstances())
//            System.out.println(i.getNom_instance());
//        for(TypeBox tb : findBoxesByInstanceId(7))
//            System.out.println(tb.getPrixbox());
//        for(TypeProduit tp : findProductsByInstanceId(7))
//            System.out.println(tp.getLproduit());
        System.out.println(findSmallestProduct(7).toString());
    }
}

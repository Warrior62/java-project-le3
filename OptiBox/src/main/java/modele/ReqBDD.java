/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
    private Connection conn;
    
    private ReqBDD() throws Exception {
        connect();
    }
    
    public static ReqBDD getInstance() throws Exception {
        if (req == null) {
                req = new ReqBDD();   
        }
        return req;
    }
    
    private void connect() throws Exception {
        String DriverClass = "org.apache.derby.jdbc.ClientDriver";
        String urlDatabase = "jdbc:derby://localhost:1527/optibox";
        String user = "root";
        String pwd = "root";
        Class.forName(DriverClass);
        this.conn = DriverManager.getConnection(urlDatabase, user, pwd);
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
    public Instance findInstanceByName(String nomInstance) throws SQLException {
        Instance ins = new Instance();
        try{
            String requete = "SELECT * FROM Instance inst"
                    + " WHERE UPPER(inst.nom_instance) LIKE ? ";
            PreparedStatement pstmt = conn.prepareStatement(requete);
            pstmt.setString(1,nomInstance.toUpperCase());

            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                String nm = res.getString("nom");
                ins.setNomInstance(nm);
            }
            res.close();
            pstmt.close();

        } catch (SQLException ex) {
            System.out.println("La requete a échoué");
        }
        
        return ins;
}

    
    /**
     * @def public static List<Instance> findAllInstances()
     * @brief sélectionne l'ensemble des Instances dans la Table Instance
     *        puis les stock dans une List qu'il retourne
     * @note s'il y a un pb dans la compilation de cette méthode, cette dernière
     *       renvoie une ArrayList vide
     * @return une List contenant toutes les Instances dans la Table Instance
     */
    public List<Instance> findAllInstances() throws Exception {
        ArrayList<Instance> maList = new ArrayList<>();
            String requete = "SELECT * FROM instance ins ORDER BY ins.NOM_INSTANCE";
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            
            Set<TypeBox> mesBox = new HashSet();
            List<TypeProduit> mesProd = new ArrayList();
            
            while(res.next()){
                long id = res.getLong("ID");
                String nomInstance = res.getString("NOM_INSTANCE");
                mesBox = findBoxByInstanceId(id);
                mesProd = findProdByInstanceId(id);

                Instance ins = new Instance(nomInstance);
                ins.setId(id);
                ins.setSetBox(mesBox);
                ins.setSetProduits(mesProd);
                System.out.println(ins.getId() +" "+ ins.getNomInstance());
                maList.add(ins);
            }
            res.close();
            stmt.close();
        return maList;
    }
   
    
/**
     * @def public static List<TypeBox> findBoxesByInstanceId(int idInstance)
     * @brief sélectionne tous les objets TypeBox dont l'id de l'Instance qui
     *        leur est à chacun associée, est égal à idInstance
     * @param idI
     * @note s'il y a un pb dans la compilation de cette méthode, cette dernière
     *       renvoie une ArrayList de TypeBox vide
     * @return une List de TypeBox dont l'id est idInstance
     */
    public Set<TypeBox> findBoxByInstanceId(long idI) throws SQLException {
        String requete = "SELECT * FROM TYPEBOX WHERE INSTANCE_BOX_ID = ? ORDER BY ID_B";
        PreparedStatement pstmt = conn.prepareStatement(requete);
        pstmt.setLong(1,idI);

        ResultSet res = pstmt.executeQuery();
        Set<TypeBox> mesBox = new HashSet();
        while(res.next()){
            int h = res.getInt("HAUTEUR_BOX");
            int l = res.getInt("LONGUEUR_BOX");
            double prix= res.getInt("PRIX_BOX");
            String idB = res.getString("ID_B");
            
            TypeBox box = new TypeBox(idB,l,h,prix);
            mesBox.add(box);
            //System.out.println(box.getInstance().getNomInstance());
        }
        res.close();
        pstmt.close();
        return mesBox;
    }
    
    /**
     * @def public static List<TypeProduit> findProductsByInstanceId(int idInstance)
     * @brief sélectionne tous les objets TypeProduit dont l'id de l'Instance qui
     *        leur est à chacun associée, est égal à idInstance
     * @param idI
     * @note s'il y a un pb dans la compilation de cette méthode, cette dernière
     *       renvoie une ArrayList de TypeProduit vide
     * @return une List de TypeProduit dont l'id est idInstance
     */
    public List<TypeProduit> findProdByInstanceId(long idI) throws SQLException {
        String requete = "SELECT * FROM TYPEPRODUIT WHERE INSTANCE_PROD_ID = ? ORDER BY GROUPE_PRODUITS";
        PreparedStatement pstmt = conn.prepareStatement(requete);
        pstmt.setLong(1,idI);

        ResultSet res = pstmt.executeQuery();
        List<TypeProduit> mesProd = new ArrayList();
        while(res.next()){
            int h = res.getInt("HAUTEUR_PRODUIT");
            int l = res.getInt("LONGUEUR_PRODUIT");
            int nb= res.getInt("NB_PRODUITS");
            String idP= res.getString("ID_P");
            
            TypeProduit prod = new TypeProduit(idP,l,h,nb);
            mesProd.add(prod);
            //System.out.println(box.getInstance().getNomInstance());
        }
        res.close();
        pstmt.close();
        return mesProd;
    }
    
    /**
     * @def public static ArrayList<String> findSmallestProduct(int idInstance)
     * @brief sélectionne le(s) produit(s) ayant la surface la plus petite 
     *          parmi tous les produits de l'instance idInstance
     * @note renvoie une liste vide si pb à la compilation
     * @param idInstance
     * @return la liste de l'id du ou des produit(s) dont la surface est la plus petite
     */
 /*   public static ArrayList<String> findSmallestProduct(int idInstance)
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
    }*/
    

}

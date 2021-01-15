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
import java.util.TreeSet;
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
            String requete = "SELECT * FROM instance ins WHERE ins.NOM_INSTANCE NOT LIKE('NOM_INSTANCE') ORDER BY ins.NOM_INSTANCE";
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            
            List<TypeBox> mesBox = new ArrayList<>();
            List<TypeProduit> mesProd = new ArrayList<>();
            
            while(res.next()){
                long id = res.getLong("ID");
                String nomInstance = res.getString("NOM_INSTANCE");
                mesBox = findBoxByInstanceId(id);
                mesProd = findProdByInstanceId(id);

                Instance ins = new Instance(nomInstance);
                ins.setId(id);
                ins.setSetBox(mesBox);
                ins.setSetProduits(mesProd);
                ins.setUneCouleurAChaqueProduit();
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
    public List<TypeBox> findBoxByInstanceId(long idI) throws SQLException {
        String requete = "SELECT * FROM TYPEBOX WHERE INSTANCE_BOX_ID = ? ORDER BY ID_B";
        PreparedStatement pstmt = conn.prepareStatement(requete);
        pstmt.setLong(1,idI);

        ResultSet res = pstmt.executeQuery();
        List<TypeBox> mesBox = new ArrayList<>();
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
        List<TypeProduit> mesProd = new ArrayList<>();
        while(res.next()){
            int h = res.getInt("HAUTEUR_PRODUIT");
            int l = res.getInt("LONGUEUR_PRODUIT");
            int nb= res.getInt("NB_PRODUITS");
            String idP= res.getString("ID_P");
            int grp = res.getInt("GROUPE_PRODUITS");
            
            TypeProduit prod = new TypeProduit(idP,l,h,nb);
            prod.setGrpProduit(grp);
            mesProd.add(prod);
            
            //System.out.println(box.getInstance().getNomInstance());
        }
       
        res.close();
        pstmt.close();
        return mesProd;
        
    }
    
    /**
     * @fn      public boolean isSolutionExist(Instance inst, Solution sol) throws SQLException
     * @brief   vérifie si la solution de inst existe en db
     * @param   inst
     * @param   sol
     * @note    se base sur le prix de la solution passée en paramètre
     * @return  true si la solution de l'Instance inst existe en db, false sinon
     */
    public boolean isSolutionExist(Instance inst) throws SQLException{
        int id = -1;
        String requete = "SELECT * FROM SOLUTION s WHERE s.INSTANCESOLUTION_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(requete);
        pstmt.setLong(1, inst.getId());
        ResultSet res = pstmt.executeQuery();

        while(res.next()){
            id = res.getInt("ID");
        }
        res.close();
        pstmt.close();
        
        if(id == -1){
            System.out.println("Aucune solution n'a été trouvée pour cette instance");
            return false;
        }
        else{
            System.out.println("La solution est déjà en BDD");
            return true;
        }
    }

}

package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    /**
     * Entity manager factory thats manage persistense
     */
    public static EntityManagerFactory factory = null;

    static {
        init();
    }

    /**
     * Method that make references to persistence name
     */
    private static void init(){
        try {

            if(factory == null){
                factory = Persistence.createEntityManagerFactory("reciclagem-java-hibernate");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Get Entity Manager
     * @return
     */
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }

    public static Object getIdEntity(Object entity){
        return factory.getPersistenceUnitUtil().getIdentifier(entity);
    }
}

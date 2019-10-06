package dao;

import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


public class GenericDao<E>{

    private static EntityManager entityManager;
    private static EntityTransaction transaction;


    static{
        init();
    }

    /**
     * Init Entity Manager and Transaction to manage hibernate connection in all context about this class
     */
    private static void init(){
        entityManager = HibernateUtil.getEntityManager();
        transaction = entityManager.getTransaction();
    }

    /**
     * Insert entity on database
     * @param entity
     */
    public void save(E entity){
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    /**
     * Find entity data on database
     * @param entity
     * @return Generic Class
     */
    public E findById(E entity){
        Object identifier = HibernateUtil.getIdEntity(entity);
        return (E) entityManager.find(entity.getClass(),identifier);
    }

    /**
     * Find all registers of entity type
     * @param type
     * @return Generic List
     */
    public List<E> findAll(Class<E> type){
        transaction.begin();
        List<E> types = entityManager.createQuery(" from " + type.getName()).getResultList();
        transaction.commit();
        return types;
    }

    /**
     * Save or Update entity on database
     * @param entity
     * @return Generic Class
     */
    public E saveUpdate(E entity){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        E e = entityManager.merge(entity);
        transaction.commit();
        return e;
    }

    /**
     * Delete entity of database
     * @param entity
     */
    public void delete(E entity){
        Object idEntity = HibernateUtil.getIdEntity(entity);
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.createNativeQuery("delete from " + entity.getClass().getSimpleName().toLowerCase() +
                " where id = " + idEntity).executeUpdate();
        entityTransaction.commit();
    }
}

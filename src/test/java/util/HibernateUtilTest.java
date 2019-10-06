package util;

import dao.GenericDao;
import entity.UserPerson;
import org.junit.Test;

import java.util.List;

public class HibernateUtilTest {

    @Test
    public void HibernateUtilTest(){
        GenericDao<UserPerson> userPersonGenericDao = new GenericDao<UserPerson>();
        UserPerson userPerson = new UserPerson();
        userPerson.setId(1L);
        UserPerson userPersonUpdate = userPersonGenericDao.findById(userPerson);
        userPersonUpdate.setName("Nome Atualizado");
        UserPerson userPersonMerged = userPersonGenericDao.saveUpdate(userPersonUpdate);
        System.out.println(userPersonMerged);
    }

    @Test
    public void searchTest(){

        GenericDao<UserPerson> userPersonGenericDao = new GenericDao<UserPerson>();
        UserPerson userPerson = new UserPerson();
        userPerson.setId(1L);
        UserPerson userPerson1 = (UserPerson) userPersonGenericDao.findById(userPerson);
        System.out.println(userPerson1);

    }

    @Test
    public void deleteTest(){
        UserPerson userPerson = new UserPerson();
        userPerson.setId(1L);
        GenericDao<UserPerson> userPersonGenericDao = new GenericDao<UserPerson>();
        userPersonGenericDao.delete(userPerson);
        assert(userPersonGenericDao.findById(userPerson) == null);
    }

    @Test
    public void findAllTest(){
        GenericDao<UserPerson> userPersonGenericDao = new GenericDao<UserPerson>();
        List<UserPerson> userPersonList = userPersonGenericDao.findAll(UserPerson.class);
        assert (userPersonList.size() == 2);
    }

}

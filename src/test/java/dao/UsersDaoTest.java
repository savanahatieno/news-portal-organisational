package dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.*;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class UsersDaoTest {

    @Test
    public void addedUserReturned_Correctly(){
        Users users = setUpNewUser();
        sql2oUsersDao.add(user);
        assertEquals(user.getName(), sql20UsersDao.findById(user.getId()).getName())
    }


    @Test
    public void addingUserToDbSetsUserId() {
        Users user = setUpNewUser();
        int originalId= user.getId();
        sql2oUsersDao.add(user);
        assertNotEquals(originalId,user.getId());
    }


}

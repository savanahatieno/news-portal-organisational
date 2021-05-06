package dao;

import models.Departments;
import models.Users;

import java.util.List;

public class UsersDao {


    //CREATE

    void  add(Users user);

    //DISPLAY

    List<Users> getAll();
    List<Departments> getAllUserDepartments(int user_id);
    Users findById(int id);

    //UPDATE

    //FOR DELETION

    void clearAll();
}

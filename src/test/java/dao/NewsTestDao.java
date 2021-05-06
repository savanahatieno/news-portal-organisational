package dao;

import models.Users;
import models.Departments;
import models.News;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;

import java.sql.Connection;

import static org.junit.Assert.*;

public class NewsTestDao {

    private static Sql2oDepartmentsDao sql2oDepartmentsDao;
    private static Sql2oUsersDao sql2oUsersDao;
    private static Sql2oNewsDao sql2oNewsDao;
    private static Connection conn;

    @After
    public void tearDown() throws Exception {
        sql2oDepartmentsDao.clearAll();
        sql2oUsersDao.clearAll();
        sql2oNewsDao.clearAll();
        System.out.println("clearing database");
    }

    @Test
    public void addNews() {
        Users users=setUpNewUsers();
        sql2oUsersDao.add(users);
        Departments departments=setUpDepartments();
        sql2oDepartmentsDao.add(departments);
        News news=new News("Todays Meeting","We are building todays app in the next few weeks",users.getId());
        sql2oNewsDao.addNews(news);

        assertEquals(users.getId(),sql2oNewsDao.findById(news.getId()).getUser_id());
        assertEquals(news.getDepartment_id(),sql2oNewsDao.findById(news.getId()).getDepartment_id());
    }




//    @Test
//    public void addDepartmentNews() {
//        Users users=setUpNewUsers();
//        sql2oUsersDao.add(users);
//        Departments departments=setUpDepartment();
//        sql2oDepartmentsDao.add(departments);
//        Departments department =new Departments("Todays Meeting","We are building todays app in the next few weeks",departments.getId()
//                ,users.getId());
//        sql2oNewsDao.addDepartmentNews(departments);
//        assertEquals(users.getId(),sql2oNewsDao.findById(departments.getId()).getUser_id());
//        assertEquals(departments.getDepartment_id(),sql2oNewsDao.findById(departments.getId()).getDepartment_id());
//
//    }

    private Users setUpNewUsers() {
    }


//    @Test
//    public void getAll() {
//        Users users=setUpNewUsers();
//        sql2oUsersDao.add(users);
//        Departments departments=setUpDepartments();
//        sql2oDepartmentsDao.add(departments);
//        Departments department =new Departments("Todays Meeting","We are building todays app in the next few weeks",departments.getId()
//                ,users.getId());
//        sql2oNewsDao.addDepartmentNews(departments);
//        News news=new News("Todays Meeting","We are building todays app in the next few weeks",users.getId());
//        sql2oNewsDao.addNews(news);
//        assertEquals(2,sql2oNewsDao.getAll().size());
//    }

    private Departments setUpDepartments() {
    }


}

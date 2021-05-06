
import com.google.gson.Gson;
import dao.Sql2oDepartmentsDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUsersDao;
import exceptions.*;

import models.Departments;
import models.News;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.staticFileLocation;


public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        Sql2oNewsDao sql2oNewsDao;
        Sql2oUsersDao sql2oUsersDao;
        Sql2oDepartmentsDao sql2oDepartmentsDao;
        Connection conn;
        Gson gson = new Gson();
        staticFileLocation("/public");


        //Heroku deployment section
        String connectionString = "jdbc:postgresql://ec2-50-17-21-170.compute-1.amazonaws.com:5432/d8b8ehu0safpui"; //!
        Sql2o sql2o = new Sql2o(connectionString, "mihpivzxyyqmlv", "5b4f9d76874ad368465a325b3993140263c6d254771908c3d283842d54fcad11");

        sql2oDepartmentsDao = new Sql2oDepartmentsDao(sql2o);
        sql2oNewsDao = new Sql2oNewsDao(sql2o);
        sql2oUsersDao = new Sql2oUsersDao(sql2o);
        conn = sql2o.open();


        //GET SECTION FOR THE PATHS
        get("/users", (request, response) -> {

            if (sql2oDepartmentsDao.getAll().size() > 0) {
                return gson.toJson(sql2oUsersDao.getAll());
            } else {
                return "{\"message\":\"I'm sorry, but no users are currently listed in the database.\"}";
            }
        });

        get("/departments", (request, response) -> {
            if (sql2oDepartmentsDao.getAll().size() > 0) {
                return gson.toJson(sql2oDepartmentsDao.getAll());
            } else {
                return "{\"message\":\"I'm sorry, but no departments are currently listed in the database.\"}";
            }
        });
        get("/news/general", (request, response) -> {
            if (sql2oNewsDao.getAll().size() > 0) {
                return gson.toJson(sql2oNewsDao.getAll());
            } else {
                return "{\"message\":\"I'm sorry, but no news are currently listed in the database.\"}";
            }
        });
        get("/users/:id/departments", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            if (sql2oUsersDao.getAllUserDepartments(id).size() > 0) {
                return gson.toJson(sql2oUsersDao.getAllUserDepartments(id));
            } else {
                return "{\"message\":\"I'm sorry, but user is in no department.\"}";
            }
        });
        get("/users/:id", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            if (sql2oUsersDao.findById(id) == null) {
                throw new Apiexception(404, String.format("No user with the id: \"%s\" exists",
                        request.params("id")));
            } else {
                return gson.toJson(sql2oUsersDao.findById(id));
            }
        });
        get("/departments/:id/users", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            if (sql2oDepartmentsDao.getAllUsersInDepartment(id).size() > 0) {
                return gson.toJson(sql2oDepartmentsDao.getAllUsersInDepartment(id));
            } else {
                return "{\"message\":\"I'm sorry, but department has no users.\"}";
            }
        });
        get("/departments/:id", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            if (sql2oDepartmentsDao.findById(id) == null) {
                throw new Apiexception(404, String.format("No department with the id: \"%s\" exists",
                        request.params("id")));
            } else {
                return gson.toJson(sql2oDepartmentsDao.findById(id));
            }
        });
        get("/news/departments/:id", (request, response) -> {

            int id = Integer.parseInt(request.params("id"));
            Departments departments = sql2oDepartmentsDao.findById(id);
            if (departments == null) {
                throw new Apiexception(404, String.format("No department with the id: \"%s\" exists",
                        request.params("id")));
            }
            if (sql2oDepartmentsDao.getDepartmentNews(id).size() > 0) {
                return gson.toJson(sql2oDepartmentsDao.getDepartmentNews(id));
            } else {
                return "{\"message\":\"I'm sorry, but no news in this department.\"}";
            }
        });


    }
}

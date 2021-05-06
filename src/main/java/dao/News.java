package dao;

import models.News;
import models.Departments;

import java.util.List;

public class News {

    //CREATE


    void addNews(News news);
    void addDepartmentNews(Department_News department_news);

    //DISPLAY

    List<News> getAll();

    News findById(int id);
    //UPDATE

    //FOR DELETION

    void clearAll();

}

package models;


import java.util.Objects;

public class News {
    private int id;
    private int user_id;
    private int department_id;
    private String news;
    private String title;
    private String description;


    public News (String title,String description, int user_id){
        this.title = title;
        this.description = description;
        this.user_id = user_id;
        this.news = news;
        this.department_id = 0;

    }

    public News (String title, String description, int department_id, int user_id){
        this.title =title;
        this.description = description;
        this.user_id = user_id;
        this.department_id = department_id;
        this.news = "department";
    }

   public int getId() {
        return id;
   }

    public int getUser_id() {
        return user_id;
    }

    public String getNews() {
        return news;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                department_id == news.department_id &&
                user_id == news.user_id &&
                Objects.equals(news, news.news) &&
                Objects.equals(title, news.title) &&
                Objects.equals(description, news.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, news, department_id, user_id, title, description);
    }


}

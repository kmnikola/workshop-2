package pl.coderslab.entity;

public class MainUser {
    public static void main(String[] args) {
        User user = new User();
        UserDao userDao = new UserDao();
        userDao.findAll();
    }
}

package web.dao;

import web.users.User;

import java.util.List;

public interface UserDao {
    public List<User> allUsers ();

    public void createUser(User user);

    public User getById(long id);

    public void updateUser (User user);

    public void deleteUser(long id);

}

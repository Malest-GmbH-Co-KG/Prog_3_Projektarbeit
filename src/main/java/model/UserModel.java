package model;

import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.User;


import java.util.List;


public class UserModel {
    private final UserDao userDao;


    public UserModel(UserDao userDao) {
        this.userDao = userDao;}

    public boolean addUser(String username,String vorname, String nachname, String password) {
        if (isVornameEmpty(vorname) || isNachnameEmpty(nachname) || isUsernameEmpty(username) || isPasswordEmpty(password)) {
            return false;
        }

        if (isUsernameAvailable(username)) {
            User newUser = new User();
            newUser.setUserName(username);
            newUser.setVorname(vorname);
            newUser.setNachname(nachname);
            newUser.setPassword(password);
            userDao.insert(newUser);
            return true;
        }
        return false;
    }

    public User authenticateUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean updateUser(String oldusername, String newusername, String newpassword) {
        List<User> user = userDao.fetchByUserName(oldusername);

        if (!user.isEmpty() && isUsernameAvailable(newusername)){
            userDao.delete(user.get(0));
            user.get(0).setUserName(newusername);
            user.get(0).setPassword(newpassword);
            userDao.insert(user.get(0));

            return true;
        }
        return false;
    }



    public boolean isUsernameAvailable(String username) {
        return userDao.fetchByUserName(username).isEmpty();
    }
    private User getUserByUsername(String username) {
        List<User> users = userDao.fetchByUserName(username);
        return users.isEmpty() ? null : users.get(0);
    }

    public boolean isVornameEmpty(String vorname) {
        return vorname.isEmpty();
    }

    public boolean isNachnameEmpty(String nachname) {
        return nachname.isEmpty();
    }

    public boolean isUsernameEmpty(String username) {
        return username.isEmpty();
    }

    public boolean isPasswordEmpty(String password) {
        return password.isEmpty();
    }





}

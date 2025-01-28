package model;

import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Timestamp;
import java.util.Base64;


import java.util.List;


public class UserModel {
    private final UserDao userDao;


    public UserModel(UserDao userDao) {
        this.userDao = userDao;
    }

    public User addUser(String username, String vorname, String nachname, String password) {
        if (isVornameEmpty(vorname) || isNachnameEmpty(nachname) || isUsernameEmpty(username) || isPasswordEmpty(password)) {
            return null;
        }

        if (isUsernameAvailable(username)) {
            String salt = generateSalt();
            String hashedPassword = null;
            try {
                 hashedPassword = hashPassword(password, salt);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
            User newUser = new User();
            newUser.setUserName(username);
            newUser.setVorname(vorname);
            newUser.setNachname(nachname);
            newUser.setPassword(hashedPassword);
            newUser.setSalt(salt);
            newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            userDao.insert(newUser);
            return newUser;
        }
        return null;
    }



    public User authenticateUser(String username, String password) {
        User user = getUserByUsername(username);

        if (user != null ) {
            String inputPassword = null;
            try {
                inputPassword = hashPassword(password, user.getSalt());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
            if (inputPassword.equals(user.getPassword())){
                return user;
            }

        }
        return null;
    }

    public boolean updateUser(String oldusername, String newusername, String newpassword) {
        List<User> user = userDao.fetchByUserName(oldusername);

        if (!user.isEmpty() && isUsernameAvailable(newusername)) {
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

    private static String hashPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 66000;
        int keyLength = 256;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iterations, keyLength);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }

    private String generateSalt() {
        return Base64.getEncoder().encodeToString(new byte [16]);
    }
}

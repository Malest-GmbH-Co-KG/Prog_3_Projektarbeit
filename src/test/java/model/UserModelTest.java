package model;

import org.junit.jupiter.api.Test;


import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.User;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {
    private DSLContext dslContext;
    private Connection connection;
    private UserDao userDao;
    private UserModel userModel;

    @BeforeEach
    void setUp() throws SQLException {
        // In-Memory SQLite-Datenbank einrichten
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite::memory:");
        connection = dataSource.getConnection();
        dslContext = DSL.using(connection, SQLDialect.SQLITE);

        // Tabellen erstellen
        dslContext.query("""
                CREATE TABLE user (
                        user_name VARCHAR(255) UNIQUE NOT NULL PRIMARY KEY,
                        Vorname VARCHAR(255) NOT NULL,
                        Nachname VARCHAR(255) NOT NULL,
                        password VARCHAR(256) NOT NULL,
                        salt VARCHAR(256),
                        created_at TEXT
                )
            """).execute();



        // DAO und Presenter initialisieren
        userDao = new UserDao(dslContext.configuration());
        userModel = new UserModel(userDao);

    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void authenticateUser_validCredentials_returnsUser() {
        // Act
        userModel.addUser("testuser", "vor", "nach", "password123");
        User result = userModel.authenticateUser("testuser", "password123");

        // Assert
        assertNotNull(result, "The authenticated user should not be null");
        assertEquals("testuser", result.getUserName(), "The username should match");
    }

    @Test
    void authenticateUser_invalidCredentials_returnsNull() {
        userModel.addUser("testuser", "vor", "nach", "password123");
        // Act
         User result = userModel.authenticateUser("invaliduser", "wrongpassword");

        // Assert
        assertNull(result, "The result should be null for invalid credentials");
    }

    @Test
    void authenticateUser_emptyCredentials_returnsNull() {
        // Act
        User result = userModel.authenticateUser("", "");

        // Assert
        assertNull(result, "The result should be null for empty credentials");
    }

    @Test
    void updateUser_validCredentials_returnsTrue() {
        userModel.addUser("testuser", "vor", "nach", "password123");
        String newUsername = "newuser";

        String oldUsername = "testuser";

        boolean result = userModel.updateUser(oldUsername, newUsername);

        assertEquals(true, result, "The result should be true for valid credentials");
        List<User> updatedUser = userDao.fetchByUserName(newUsername);
        assertNotNull(updatedUser, "The updated user should not be null");
        assertEquals(newUsername, updatedUser.get(0).getUserName(), "The username should match");

    }

    @Test
    void updateUser_invalidCredentials_returnsFalse() {
        userModel.addUser("testuser", "vor", "nach", "password123");
        String newUsername = "testuser"; // This username already exists

        String oldUsername = "testuser";

        boolean result = userModel.updateUser(oldUsername, newUsername);
        assertEquals(false, result, "The result should be false for invalid credentials");

    }

    @Test
    void addUser_false(){
        userModel.addUser("testuser", "vor", "nach", "password123");
        String username = "testuser";
        String vorname = "vor";
        String nachname = "nach";
        String password = "password123";
        User result = userModel.addUser(username, vorname, nachname, password);
        assertNull(result, "The result should be false for existing user");
    }

    @Test
    void addUser_true() {
        String username = "newuser1";
        String vorname = "vor";
        String nachname = "nach";
        String password = "password123";
        User result = userModel.addUser(username,vorname, nachname, password);
        assertNotNull(result, "The result should be true for new user");
    }
}

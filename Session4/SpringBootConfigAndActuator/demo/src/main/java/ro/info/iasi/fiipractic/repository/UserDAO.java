package ro.info.iasi.fiipractic.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.info.iasi.fiipractic.exception.UserNotFoundException;
import ro.info.iasi.fiipractic.model.User;
import ro.info.iasi.fiipractic.repository.mapper.UserRowMapper;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM USERS", new UserRowMapper());
    }

    public int createUser(String firstName, String  lastName, String email, String password) {
        return jdbcTemplate.update("INSERT INTO USERS(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)", firstName, lastName, email, password);
    }

    public int updateUser(String firstName, String  lastName, String email, String password, Integer id) {
        return jdbcTemplate.update("UPDATE USERS SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PASSWORD = ? WHERE ID = ?", firstName, lastName, email, password, id);
    }

    public int deleteUser(int id) {
        return jdbcTemplate.update("DELETE FROM USERS WHERE ID = ?", id);
    }

    public User getUserById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE ID = ?", new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException(String.format("User with id %s was not found", id));
        }
    }

    public void create() {
        jdbcTemplate.execute("CREATE TABLE USERS ("
            + "ID serial PRIMARY KEY,"
            + "FIRST_NAME VARCHAR ( 50 ),"
            + "LAST_NAME VARCHAR ( 50 ) ,\n"
            + "EMAIL VARCHAR ( 255 ) UNIQUE NOT NULL,"
            + "PASSWORD VARCHAR ( 255 )  NOT NULL"
            + ");");
    }
}

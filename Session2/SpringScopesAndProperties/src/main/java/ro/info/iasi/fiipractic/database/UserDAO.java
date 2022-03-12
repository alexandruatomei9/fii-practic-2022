package ro.info.iasi.fiipractic.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getCountOfUsers() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER", Integer.class);
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM USER", new UserRowMapper());
    }

    public int addUser(String firstName, String  lastName, String email, String password) {
        return jdbcTemplate.update("INSERT INTO USER(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)", firstName, lastName, email, password);
    }
}

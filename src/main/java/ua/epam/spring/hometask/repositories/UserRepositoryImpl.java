package ua.epam.spring.hometask.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;
    private UserMapper userMapper = new UserMapper();

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User result = jdbcTemplate.queryForObject(sql, new Object[]{email}, userMapper);
        return result;
    }

    @Override
    public User save(@Nonnull User user) {
        if (user.getId() != null) {
            String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, birthday = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    Timestamp.valueOf(user.getDateOfBirth().atStartOfDay()),
                    user.getId()
            );
        } else {
            String sql = "INSERT INTO users(first_name, last_name, email, birthday) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(
                    sql,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    Timestamp.valueOf(user.getDateOfBirth().atStartOfDay())
            );
        }
        return user;
    }

    @Override
    public void remove(@Nonnull User user) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, user.getId());
    }

    @Nullable
    @Override
    public User getById(@Nonnull Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User result = jdbcTemplate.queryForObject(sql, new Object[]{id}, userMapper);
        return result;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(sql, userMapper);
        return users;
    }

    private final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setDateOfBirth(resultSet.getDate("birthday").toLocalDate());
            return user;
        }
    }
}

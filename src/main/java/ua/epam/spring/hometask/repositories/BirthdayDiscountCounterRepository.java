package ua.epam.spring.hometask.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.domain.User;

@Repository
public class BirthdayDiscountCounterRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BirthdayDiscountCounterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user, Long count) {
        if (this.getDiscountsNumberByUser(user) != null) {
            String sql = "UPDATE birthday_discount_counter SET count = ? WHERE user_id = ?";
            jdbcTemplate.update(sql, count, user.getId());
        }
        else {
            String sql = "INSERT INTO birthday_discount_counter(user_id, count) VALUES (?, ?)";
            jdbcTemplate.update(sql, user.getId(), count);
        }
    }

    public Long getDiscountsNumberByUser(User user) {
        String sql = "SELECT * FROM birthday_discount_counter WHERE user_id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{user.getId()},
                (resultSet, i) -> resultSet.getLong("count")
        );
    }
}

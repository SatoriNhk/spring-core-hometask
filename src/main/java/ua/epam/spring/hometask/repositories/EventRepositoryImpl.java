package ua.epam.spring.hometask.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private JdbcTemplate jdbcTemplate;
    private EventMapper eventMapper = new EventMapper();

    @Autowired
    public EventRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Event save(@Nonnull Event event) {
        if (event.getId() != null) {
            String sql = "UPDATE events SET name = ?, base_price = ?, rating = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    event.getName(),
                    event.getBasePrice(),
                    event.getRating().ordinal(),
                    event.getId()
            );
            return event;
        } else {
            String sql = "INSERT INTO events(name, base_price, rating) VALUES (?, ?, ?)";
            jdbcTemplate.update(
                    sql,
                    event.getName(),
                    event.getBasePrice(),
                    event.getRating().ordinal()
            );
            return event;
        }
    }

    @Override
    public void remove(@Nonnull Event event) {
        String sql = "DELETE FROM events WHERE id = ?";
        jdbcTemplate.update(sql, event.getId());
    }

    @Nullable
    @Override
    public Event getById(@Nonnull Long id) {
        String sql = "SELECT * FROM events WHERE id = ?";
        Event result = jdbcTemplate.queryForObject(sql, new Object[]{id}, eventMapper);
        return result;
    }


    @Override
    public List<Event> getAll() {
        String sql = "SELECT * FROM events";
        List<Event> events = jdbcTemplate.query(sql, eventMapper);
        return events;
    }


    private final class EventMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Event event = new Event();
            event.setId(resultSet.getLong("id"));
            event.setName(resultSet.getString("name"));
            event.setBasePrice(resultSet.getDouble("base_price"));
            event.setRating(EventRating.values()[resultSet.getInt("rating")]);

            return event;
        }
    }
}

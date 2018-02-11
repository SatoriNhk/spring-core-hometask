package ua.epam.spring.hometask.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.counters.EventCounterCases;
import ua.epam.spring.hometask.domain.Event;

@Repository
public class EventCounterRepository {
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EventCounterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long getCallingsByName(Event event) {
        String sql = "SELECT * FROM event_counter WHERE event_counter_case= ? AND event_id = ?";
        Long result = jdbcTemplate.queryForObject(
                sql,
                new Object[]{EventCounterCases.BY_NAME.ordinal()},
                (resultSet, i) -> resultSet.getLong("count")
        );
        return result;
    }

    public Long getCallingsByPrice(Event event) {
        String sql = "SELECT * FROM event_counter WHERE event_counter_case= ? AND event_id = ?";
        Long result = jdbcTemplate.queryForObject(
                sql,
                new Object[]{EventCounterCases.BY_PRICE.ordinal()},
                (resultSet, i) -> resultSet.getLong("count")
        );
        return result;
    }

    public Long getCallingsByBooking(Event event) {
        String sql = "SELECT * FROM event_counter WHERE event_counter_case= ? AND event_id = ?";
        Long result = jdbcTemplate.queryForObject(
                sql,
                new Object[]{EventCounterCases.BY_BOOKING.ordinal()},
                (resultSet, i) -> resultSet.getLong("count")
        );
        return result;
    }

    public void putCallingsByName(Event event, Long count) {
        String sql;
        if (this.getCallingsByName(event) != null) {
            sql = "UPDATE event_counter SET count = ? WHERE event_counter_case = ? AND event_id = ?";
        }
        else {
            sql = "UPDATE event_counter SET count = ? WHERE event_counter_case = ? AND event_id = ?";
        }
        jdbcTemplate.update(sql, count, EventCounterCases.BY_NAME.ordinal(),event.getId());
    }

    public void putCallingsByPrice(Event event, Long count) {
        String sql;
        if (this.getCallingsByName(event) != null) {
            sql = "UPDATE event_counter SET count = ? WHERE event_counter_case = ? AND event_id = ?";
        }
        else {
            sql = "UPDATE event_counter SET count = ? WHERE event_counter_case = ? AND event_id = ?";
        }
        jdbcTemplate.update(sql, count, EventCounterCases.BY_PRICE.ordinal(),event.getId());
    }

    public void putCallingsByBooking(Event event, Long count) {
        String sql;
        if (this.getCallingsByName(event) != null) {
            sql = "UPDATE event_counter SET count = ? WHERE event_counter_case = ? AND event_id = ?";
        }
        else {
            sql = "UPDATE event_counter SET count = ? WHERE event_counter_case = ? AND event_id = ?";
        }
        jdbcTemplate.update(sql, count, EventCounterCases.BY_BOOKING.ordinal(),event.getId());
    }
}

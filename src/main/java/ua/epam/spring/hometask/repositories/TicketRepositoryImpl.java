package ua.epam.spring.hometask.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Repository
public class TicketRepositoryImpl implements TicketRepository {

    private JdbcTemplate jdbcTemplate;
    private UserRepository userRepository;
    private EventRepository eventRepository;

    private TicketMapper ticketMapper = new TicketMapper();


    @Autowired
    public TicketRepositoryImpl(JdbcTemplate jdbcTemplate, UserRepository userRepository, EventRepository eventRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Ticket> getByUser(@Nonnull User user) {
        String sql = "SELECT * FROM tickets WHERE user_id = ?";
        List<Ticket> tickets = jdbcTemplate.query(sql, new Object[]{user.getId()}, ticketMapper);
        return tickets;
    }

    @Override
    public Ticket save(@Nonnull Ticket ticket) {
        if (ticket.getId() != null) {
            String sql = "UPDATE tickets SET user_id = ?, event_id = ?, date_time = ?, seat = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    ticket.getUser().getId(),
                    ticket.getEvent().getId(),
                    Timestamp.valueOf(ticket.getDateTime()),
                    ticket.getSeat(),
                    ticket.getId()
            );
        } else {
            String sql = "INSERT INTO tickets(user_id, event_id, date_time, seat) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(
                    sql,
                    ticket.getUser().getId(),
                    ticket.getEvent().getId(),
                    Timestamp.valueOf(ticket.getDateTime()),
                    ticket.getSeat());
        }
        return ticket;
    }

    @Override
    public void remove(@Nonnull Ticket ticket) {
        String sql = "DELETE FROM tickets WHERE id = ?";
        jdbcTemplate.update(sql, ticket.getId());
    }

    @Nullable
    @Override
    public Ticket getById(@Nonnull Long id) {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        Ticket ticket = jdbcTemplate.queryForObject(sql, new Object[]{id}, ticketMapper);
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        String sql = "SELECT * FROM tickets";
        List<Ticket> tickets = jdbcTemplate.query(sql, new TicketMapper());
        return tickets;
    }

    private final class TicketMapper implements RowMapper<Ticket> {
        @Override
        public Ticket mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Ticket ticket = new Ticket();
            ticket.setUser(userRepository.getById(resultSet.getLong("user_id")));
            ticket.setEvent(eventRepository.getById(resultSet.getLong("event_id")));
            ticket.setSeat(resultSet.getInt("seat"));
            ticket.setDateTime(resultSet.getDate("date_time").toLocalDate().atStartOfDay());
            return ticket;
        }
    }


}

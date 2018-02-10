CREATE TABLE users (
  id         INTEGER PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  birthday   TIMESTAMP
);

CREATE TABLE events (
  id         INTEGER PRIMARY KEY,
  name       VARCHAR(30),
  base_price DOUBLE,
  rating     INTEGER
);

CREATE TABLE tickets (
  id        INTEGER PRIMARY KEY,
  seat      INTEGER,
  date_time TIMESTAMP,
  user_id   INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id),
  event_id  INTEGER,
  FOREIGN KEY (event_id) REFERENCES events (id)
);

CREATE TABLE event_get_by_name_counter (
  id                 INTEGER PRIMARY KEY,
  event_id           INTEGER,
  FOREIGN KEY (event_id) REFERENCES events (id),
  count              BIGINT
);

CREATE TABLE event_get_price_counter (
  id                 INTEGER PRIMARY KEY,
  event_id           INTEGER,
  FOREIGN KEY (event_id) REFERENCES events (id),
  count              BIGINT
);

CREATE TABLE event_booked_tickets_counter (
  id                 INTEGER PRIMARY KEY,
  event_id           INTEGER,
  FOREIGN KEY (event_id) REFERENCES events (id),
  count              BIGINT
);

CREATE TABLE birthday_discount_counter (
  id                INTEGER PRIMARY KEY,
  count             BIGINT,
  user_id           INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id),
);

CREATE TABLE tenth_ticket_discount_counter (
  id                INTEGER PRIMARY KEY,
  count             BIGINT,
  user_id           INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id),
)



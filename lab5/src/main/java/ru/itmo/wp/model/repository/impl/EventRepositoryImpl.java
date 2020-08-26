package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.EventRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.List;

public class EventRepositoryImpl extends BasicRepositoryImpl implements EventRepository {
    @Override
    public void save(Event event) {
        save(event, "Event", "userId, type, creationTime", "?, ?, NOW()",
                List.of(event.getUserId(), event.getType().name()));
    }
}
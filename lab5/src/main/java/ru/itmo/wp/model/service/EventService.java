package ru.itmo.wp.model.service;


import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.EventRepository;
import ru.itmo.wp.model.repository.impl.EventRepositoryImpl;

public class EventService {
    EventRepository eventRepository = new EventRepositoryImpl();

    public void enterEvent(User user) {
        Event event = new Event();
        event.setUserId(user.getId());
        event.setType(Event.Type.ENTER);
        eventRepository.save(event);
    }

    public void logoutEvent(User user) {
        Event event = new Event();
        event.setUserId(user.getId());
        event.setType(Event.Type.LOGOUT);
        eventRepository.save(event);
    }
}

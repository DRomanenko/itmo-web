package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Talk;

import java.util.List;

public interface TalkRepository {
    List<Talk> findAll(long userId);
    void save(Talk talks);
}

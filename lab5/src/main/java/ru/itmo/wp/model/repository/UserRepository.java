package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface UserRepository {
    User find(long id);
    User findByLogin(String login);
    User findByEmail(String email);
    User findByLoginAndEmail(String login, String email);
    User findByLoginOrEmailAndPasswordSha(String loginOrEmail, String passwordSha);
    User findByLoginAndPasswordSha(String login, String passwordSha);
    List<User> findAll();
    long findCount();
    void save(User user, String passwordSha);
}

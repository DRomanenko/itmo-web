package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class TalkService {
    private TalkRepository talkRepository = new TalkRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();

    public List<Chat> findAllTalkViews(long userId) {
        List<Talk> talks = talkRepository.findAll(userId);

        List<Chat> result = new ArrayList<>();
        for (Talk talk : talks) {
            Chat chat = new Chat();
            chat.setId(talk.getId());
            chat.setSourceUser(userRepository.find(talk.getSourceUserId()));
            chat.setTargetUser(userRepository.find(talk.getTargetUserId()));
            chat.setText(talk.getText());
            chat.setCreationTime(talk.getCreationTime());
            result.add(chat);
        }

        return result;
    }

    public void validateTalk(String targetUserLogin, String text) throws ValidationException {
        if (targetUserLogin == null || targetUserLogin.isEmpty()) {
            throw new ValidationException("Login of recipient is required");
        }

        if (text == null || text.isEmpty()) {
            throw new ValidationException("Text of message is required");
        }

        if (userRepository.findByLogin(targetUserLogin) == null) {
            throw new ValidationException("Invalid login of recipient");
        }
    }

    public void send(Talk talk) {
        talkRepository.save(talk);
    }

    public static class Chat {
        private long id;
        private User sourceUser;
        private User targetUser;
        private String text;
        private Date creationTime;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public User getSourceUser() {
            return sourceUser;
        }

        public void setSourceUser(User sourceUser) {
            this.sourceUser = sourceUser;
        }

        public User getTargetUser() {
            return targetUser;
        }

        public void setTargetUser(User targetUser) {
            this.targetUser = targetUser;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Date getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(Date creationTime) {
            this.creationTime = creationTime;
        }
    }
}
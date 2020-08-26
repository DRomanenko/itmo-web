package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.Domain;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicRepositoryImpl {
    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();



    protected Date findCreationTime(long id, String obj) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT creationTime FROM " + obj + " WHERE id=?")) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getTimestamp(1);
                    }
                }
                throw new RepositoryException("Can't find " + obj + ".creationTime by id.");
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find " + obj + ".creationTime by id.", e);
        }
    }

    public <T> List<T> findAll(String statement, String objName, List<Object> elements) {
        List<T> objs = new ArrayList<T>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement currStatement = connection.prepareStatement(
                    statement)) {
                int cnt = 0;
                for (Object element : elements) {
                    if (element instanceof Long) {
                        currStatement.setLong(++cnt, (Long) element);
                    } else if (element instanceof String) {
                        currStatement.setString(++cnt, (String) element);
                    }
                }


                try (ResultSet resultSet = currStatement.executeQuery()) {
                    while (resultSet.next()) {
                        if ("Talk".equals(objName)) {
                            objs.add((T) toTalk(currStatement.getMetaData(), resultSet));
                        } else if (objName.equals("User")) {
                            objs.add((T) toUser(currStatement.getMetaData(), resultSet));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find all" + objName, e);
        }
        return objs;
    }

    public User toUser(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        User user = new User();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    user.setId(resultSet.getLong(i));
                    break;
                case "login":
                    user.setLogin(resultSet.getString(i));
                    break;
                case "email":
                    user.setEmail(resultSet.getString(i));
                    break;
                case "creationTime":
                    user.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return user;
    }

    private Talk toTalk(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        Talk talk = new Talk();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    talk.setId(resultSet.getLong(i));
                    break;
                case "sourceUserId":
                    talk.setSourceUserId(resultSet.getLong(i));
                    break;
                case "targetUserId":
                    talk.setTargetUserId(resultSet.getLong(i));
                    break;
                case "text":
                    talk.setText(resultSet.getString(i));
                    break;
                case "creationTime":
                    talk.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }
        return talk;
    }

    public void save(Domain obj, String objName, String fields, String values, List<Object> elements) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `" + objName + "` (" + fields + ") VALUES (" + values + ")", Statement.RETURN_GENERATED_KEYS)) {
                int cnt = 0;
                for (Object element : elements) {
                    if (element instanceof Long) {
                        statement.setLong(++cnt, (Long) element);
                    } else if (element instanceof String) {
                        statement.setString(++cnt, (String) element);
                    }
                }
                if (statement.executeUpdate() == 1) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        obj.setId(generatedKeys.getLong(1));
                        obj.setCreationTime(findCreationTime(obj.getId(), objName));
                    } else {
                        throw new RepositoryException("Can't save " + objName + " [no autogenerated fields].");
                    }
                } else {
                    throw new RepositoryException("Can't save " + objName + ".");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save " + objName + ".", e);
        }
    }
}

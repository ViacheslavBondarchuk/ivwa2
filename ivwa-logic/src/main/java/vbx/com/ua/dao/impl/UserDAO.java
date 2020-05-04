package vbx.com.ua.dao.impl;

import org.springframework.stereotype.Repository;
import vbx.com.ua.dao.DAO;
import vbx.com.ua.domain.Authority;
import vbx.com.ua.domain.User;
import vbx.com.ua.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserDAO implements DAO<User, Long> {
    private JDBCUtil jdbcUtil = JDBCUtil.getInstance();

    @Override
    public void create(User user) {
        try (Connection connection = jdbcUtil.getConnection()) {
            if (connection != null) {
                connection.setAutoCommit(false);
                String userId = jdbcUtil.getNextValue("ivwa.users_id_seq");
                Set<Authority> authorities = user.getAuthorities();
                String query = String.format("insert into ivwa.users(id,username,password,email) values (\'%s\',\'%s\',\'%s\',\'%s\')"
                        , userId
                        , user.getUsername()
                        , user.getPassword()
                        , user.getEmail());
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
                preparedStatement.close();
                String authorityQuery = "insert into ivwa.users(user_id,name) values(\'%s\',\'%s\')";
                authorities.forEach(authority -> {
                    try {
                        PreparedStatement authorityStatement = connection.prepareStatement(authorityQuery);
                        authorityStatement.execute();
                        authorityStatement.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                });
                connection.commit();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<User> read(Long aLong) {
        try (Connection connection = jdbcUtil.getConnection()) {
            if (connection != null) {
                String query = String.format("select usr.*, array_agg(name order by usr.id) as authority" +
                        " from ivwa.users as usr inner join ivwa.authorities as auth on usr.id = auth.user_id where usr.id = %d group by usr.id, auth.user_id", aLong);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet != null && resultSet.next()) {
                    String[] authorities = ((String[]) resultSet.getArray("authority").getArray());
                    Set<Authority> authoritySet = Arrays.stream(authorities)
                            .map(Authority::valueOf)
                            .collect(Collectors.toSet());
                    return Optional.of(new User(
                            resultSet.getLong("id")
                            , resultSet.getString("username")
                            , resultSet.getString("password")
                            , resultSet.getString("email")
                            , resultSet.getBoolean("is_enabled")
                            , resultSet.getBoolean("is_account_non_expired")
                            , resultSet.getBoolean("is_account_non_locked")
                            , resultSet.getBoolean("is_credentials_non_expired")
                            , authoritySet
                    ));

                }
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<User> readAll() {
        try (final Connection connection = jdbcUtil.getConnection()) {
            if (connection != null) {
                String query = "select usr.*, array_agg(name order by usr.id) as authority" +
                        " from ivwa.users as usr inner join ivwa.authorities as auth on usr.id = auth.user_id group by usr.id, auth.user_id";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet != null) {
                    List<User> users = new ArrayList<>();
                    while (resultSet.next()) {
                        String[] authorities = ((String[]) resultSet.getArray("authority").getArray());
                        Set<Authority> authoritySet = Arrays.stream(authorities)
                                .map(Authority::valueOf)
                                .collect(Collectors.toSet());
                        users.add(new User(
                                resultSet.getLong("id")
                                , resultSet.getString("username")
                                , resultSet.getString("password")
                                , resultSet.getString("email")
                                , resultSet.getBoolean("is_enabled")
                                , resultSet.getBoolean("is_account_non_expired")
                                , resultSet.getBoolean("is_account_non_locked")
                                , resultSet.getBoolean("is_credentials_non_expired")
                                , authoritySet
                        ));
                    }
                    return users;
                }
                resultSet.close();
                preparedStatement.close();
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Long aLong) {

    }

    public Optional<User> findByUsername(String username){
        try (Connection connection = jdbcUtil.getConnection()) {
            if (connection != null) {
                String query = String.format("select usr.*, array_agg(name order by usr.id) as authority" +
                        " from ivwa.users as usr inner join ivwa.authorities as auth on usr.id = auth.user_id where usr.username = \'%\' group by usr.id, auth.user_id", username);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet != null && resultSet.next()) {
                    String[] authorities = ((String[]) resultSet.getArray("authority").getArray());
                    Set<Authority> authoritySet = Arrays.stream(authorities)
                            .map(Authority::valueOf)
                            .collect(Collectors.toSet());
                    return Optional.of(new User(
                            resultSet.getLong("id")
                            , resultSet.getString("username")
                            , resultSet.getString("password")
                            , resultSet.getString("email")
                            , resultSet.getBoolean("is_enabled")
                            , resultSet.getBoolean("is_account_non_expired")
                            , resultSet.getBoolean("is_account_non_locked")
                            , resultSet.getBoolean("is_credentials_non_expired")
                            , authoritySet
                    ));

                }
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long aLong) {
        try (Connection connection = jdbcUtil.getConnection()) {
            if (connection != null) {
                String query = String.format("delete from ivwa.users where id = \'%\'", aLong);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
                preparedStatement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

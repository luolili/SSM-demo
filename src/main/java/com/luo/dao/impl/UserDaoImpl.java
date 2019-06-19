package com.luo.dao.impl;

import com.luo.dao.UserDao;
import com.luo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource(name = "myJdbc")//javax
    private JdbcTemplate myJdbc;

    @Override
    public List<User> getAllUsers() {
        String sql = "select * from user";
        List<User> result = query(sql);
        return result;
    }

    @Override
    public User getUser(String id) {
        String sql = "select * from user where id="+id;
        return queryById(sql);
    }

    @Override
    public boolean addUser(User userModel) {
        return false;
    }

    @Override
    public boolean updateUser(String id, String name) {
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    /**
     * query user list by sql
     * @param sql
     * @return
     */
    private List<User> query(String sql) {

        final List<User> userList = new ArrayList<>();

        myJdbc.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                //-1 get properties from resultSet
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                //-2 create a user
                User user = new User(id, name, age);
                //add user to list
                userList.add(user);

            }
        });
        return userList;
    }


    private User queryById(String sql) {
        final User[] u = new User[1];
        myJdbc.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                int id = resultSet.getInt(1);

                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                User user = new User(id, name, age);
                u[0] = user;
            }
        });

        return u[0];
    }
}

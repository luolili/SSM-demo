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

@Repository("userDaoImpl")
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
    public boolean addUser(User user) {

        String sql = "insert into user values(?,?,?)";

        int result = myJdbc.update(sql,user.getId(),user.getName(),user.getAge());

        return result != 0;
    }

    @Override
    public boolean updateUser(String id, String name) {
        String sql = "update user set name = ? where id = ?";
        int result = myJdbc.update(sql, name, id);

        return result != 0;
    }

    @Override
    public boolean deleteUser(String id) {

        String sql = "delete from user where id = ?";
        int result = myJdbc.update(sql, id);
        return result != 0;
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

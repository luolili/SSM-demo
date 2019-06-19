package com.luo.controller;

import com.luo.dao.UserDao;
import com.luo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserDao userDao;

    @Autowired
    UserController(UserDao userDao) {
        this.userDao = userDao;
    }
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers()
    {
        return userDao.getAllUsers();
    }

    @RequestMapping(value = "getOne", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(String id) {

        return userDao.getUser(id);
    }

    @RequestMapping(value = "saveOne", method = RequestMethod.POST)
    @ResponseBody
    public boolean addUser() {
        User u = new User(3, "huy", 6);
        return userDao.addUser(u);
    }

    @RequestMapping(value = "updateOne", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateUser() {
        User u = new User(3, "ty", 6);
        return userDao.updateUser(u.getId()+"", u.getName());
    }
}

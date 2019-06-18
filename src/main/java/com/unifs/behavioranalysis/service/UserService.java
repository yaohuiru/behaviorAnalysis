package com.unifs.behavioranalysis.service;

import com.unifs.behavioranalysis.bean.User;

import java.util.HashMap;
import java.util.List;

public interface UserService
{
    public void userinsert(User user);
    public void  userdelete(String id);
    public void userupdate(User user);
    public List<User> userselectall();
    public List<User> userselect(User user);
    public List<HashMap<String, Object>> parseList();
}

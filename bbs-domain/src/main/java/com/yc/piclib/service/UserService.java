package com.yc.piclib.service;

import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.UserDomain;
import com.yc.piclib.entity.User;

import java.util.List;

public interface UserService {

    public List<UserDomain> list();

    public PageDomain<UserDomain> listByPage(UserDomain userDomain);

    public UserDomain findOne(User u);
}

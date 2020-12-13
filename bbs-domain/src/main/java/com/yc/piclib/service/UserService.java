package com.yc.piclib.service;

import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.UserDomain;

import java.util.List;

public interface UserService {

    public List<UserDomain> list();

    public PageDomain<UserDomain> listByPage(UserDomain userDomain);
}

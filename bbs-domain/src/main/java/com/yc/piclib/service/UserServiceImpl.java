package com.yc.piclib.service;

import com.yc.piclib.dao.impl.UserMapper;
import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.UserDomain;
import com.yc.piclib.entity.User;
import com.yc.piclib.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper picMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserDomain> list() {
        List<User> list = picMapper.selectAll();
        List<UserDomain> r = new ArrayList<UserDomain>();
        for (User p : list) {
            UserDomain pd = new UserDomain(p.getUid(), p.getUname(), p.getUpass(),p.getHead(),p.getRegtime(),p.getGender());
            r.add(pd);
        }
        return r;
    }

    @Override
    public PageDomain<UserDomain> listByPage(UserDomain picDomain) {
        Example example = new Example(User.class);
        if (CommonUtils.isNotNull(picDomain.getHead())) {
            example.createCriteria()
                    .andLike("description", "%" + picDomain.getUname() + "%");
        }
        long total = picMapper.selectCountByExample(example);

        PageDomain<UserDomain> pageDomain = new PageDomain<UserDomain>();
        pageDomain.setTotal(total);
        pageDomain.setPage(picDomain.getPage());
        pageDomain.setPageSize(picDomain.getPageSize());


        List<User> list = picMapper.selectByExample(example);
        List<UserDomain> r = new ArrayList<UserDomain>();
        for (User p : list) {
            UserDomain pd = new UserDomain(p.getUid(), p.getUname(), p.getUpass(),p.getHead(),p.getRegtime(),p.getGender());
            r.add(pd);
        }
        pageDomain.setData(r);

        return pageDomain;
    }


    @Transactional(readOnly = true)
    @Override
    public UserDomain findOne(User u){
        User user = this.picMapper.selectOne(u);
        UserDomain picDomain = new UserDomain(user.getUid(), user.getUname(), user.getUpass(),user.getHead(),user.getRegtime(), user.getGender());
        return picDomain;
    }
}

package com.yc.piclib.service;

import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.ReplyDomain;

import java.util.List;

public interface ReplyService {

    public List<ReplyDomain> list();

    public PageDomain<ReplyDomain> listByPage(ReplyDomain replyDomain);

    public void reply(ReplyDomain replyDomain);

    public void delete(Integer id);

}

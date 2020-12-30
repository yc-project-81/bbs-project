package com.yc.piclib.service;

import com.yc.piclib.dao.impl.ReplyMapper;
import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.ReplyDomain;
import com.yc.piclib.entity.Reply;
import com.yc.piclib.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired(required = false)
    private ReplyMapper picMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ReplyDomain> list() {
        List<Reply> list = picMapper.selectAll();
        List<ReplyDomain> r = new ArrayList<ReplyDomain>();
        for (Reply p : list) {
            ReplyDomain pd = new ReplyDomain(p.getReplyid(),p.getTitle(),p.getContent(),p.getPublishtime(),p.getPublishtime(),p.getModifytime(),p.getUid(),p.getTopicid());
            r.add(pd);
        }
        return r;
    }

    @Override
    public PageDomain<ReplyDomain> listByPage(ReplyDomain picDomain) {
        Example example = new Example(Reply.class);
        if (CommonUtils.isNotNull(picDomain.getContent())) {
            example.createCriteria()
                    .andLike("description", "%" + picDomain.getContent() + "%");
        }
        long total = picMapper.selectCountByExample(example);

        PageDomain<ReplyDomain> pageDomain = new PageDomain<ReplyDomain>();
        pageDomain.setTotal(total);
        pageDomain.setPage(picDomain.getPage());
        pageDomain.setPageSize(picDomain.getPageSize());


        List<Reply> list = picMapper.selectByExample(example);
        List<ReplyDomain> r = new ArrayList<ReplyDomain>();
        for (Reply p : list) {
            ReplyDomain pd = new ReplyDomain(p.getReplyid(),p.getTitle(),p.getContent(),p.getPublishtime(),p.getPublishtime(),p.getModifytime(),p.getUid(),p.getTopicid());
            r.add(pd);
        }
        pageDomain.setData(r);

        return pageDomain;
    }

    @Override
    public void reply(ReplyDomain replyDomain){
    Reply reply=new Reply();
        reply.setTopicid(replyDomain.getTopicid());
        reply.setContent(replyDomain.getContent());
        reply.setModifytime(replyDomain.getModifytime());
        reply.setPublishtime(replyDomain.getPublishtime());
        reply.setReplyid(replyDomain.getReplyid());
        reply.setTitle(replyDomain.getTitle());
        reply.setUid(replyDomain.getUid());
        this.picMapper.insert(reply);

        replyDomain.setReplyid(reply.getReplyid());

    }

    @Override
    public void delete(Integer id){
        this.picMapper.deleteByPrimaryKey(id);

    }

}

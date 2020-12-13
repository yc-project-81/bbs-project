package com.yc.piclib.service;

import com.yc.piclib.dao.impl.TopicMapper;
import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.TopicDomain;
import com.yc.piclib.entity.Topic;
import com.yc.piclib.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired(required = false)
    private TopicMapper picMapper;

    @Transactional(readOnly = true)
    @Override
    public List<TopicDomain> list() {
        List<Topic> list = picMapper.selectAll();
        List<TopicDomain> r = new ArrayList<TopicDomain>();
        for (Topic p : list) {
            TopicDomain pd = new TopicDomain(p.getTopicid(),p.getTitle(),p.getContent(),p.getPublishtime(),p.getModifytime(),p.getUid(),p.getUid(),p.getBoardid());
            r.add(pd);
        }
        return r;
    }

    @Override
    public PageDomain<TopicDomain> listByPage(TopicDomain picDomain) {
        Example example = new Example(Topic.class);
        if (CommonUtils.isNotNull(picDomain.getContent())) {
            example.createCriteria()
                    .andLike("description", "%" + picDomain.getContent() + "%");
        }
        long total = picMapper.selectCountByExample(example);

        PageDomain<TopicDomain> pageDomain = new PageDomain<TopicDomain>();
        pageDomain.setTotal(total);
        pageDomain.setPage(picDomain.getPage());
        pageDomain.setPageSize(picDomain.getPageSize());


        List<Topic> list = picMapper.selectByExample(example);
        List<TopicDomain> r = new ArrayList<TopicDomain>();
        for (Topic p : list) {
            TopicDomain pd = new TopicDomain(p.getTopicid(),p.getTitle(),p.getContent(),p.getPublishtime(),p.getModifytime(),p.getUid(),p.getUid(),p.getBoardid());
            r.add(pd);
        }
        pageDomain.setData(r);

        return pageDomain;
    }


}

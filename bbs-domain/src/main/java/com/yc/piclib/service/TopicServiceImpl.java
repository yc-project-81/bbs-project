package com.yc.piclib.service;

import com.yc.piclib.dao.impl.TopicMapper;
import com.yc.piclib.domain.BoardDomain;
import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.TopicDomain;
import com.yc.piclib.entity.Board;
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
            TopicDomain pd = new TopicDomain(p.getTopicid(),p.getTitle(),p.getContent(),p.getPublishtime(),p.getModifytime(),p.getUid(),p.getBoardid());
            r.add(pd);
        }
        return r;
    }

    @Override
    public PageDomain<TopicDomain> listByPage(TopicDomain picDomain) {
        Example example = new Example(Topic.class);
        if (CommonUtils.isNotNull(picDomain.getContent())) {
            example.createCriteria()
                    .andLike("content", "%" + picDomain.getContent() + "%");
        }
        long total = picMapper.selectCountByExample(example);

        PageDomain<TopicDomain> pageDomain = new PageDomain<TopicDomain>();
        pageDomain.setTotal(total);
        pageDomain.setPage(picDomain.getPage());
        pageDomain.setPageSize(picDomain.getPageSize());


        List<Topic> list = picMapper.selectByExample(example);
        List<TopicDomain> r = new ArrayList<TopicDomain>();
        for (Topic p : list) {
            TopicDomain pd = new TopicDomain(p.getTopicid(),p.getTitle(),p.getContent(),p.getPublishtime(),p.getModifytime(),p.getUid(),p.getBoardid());
            r.add(pd);
        }
        pageDomain.setData(r);

        return pageDomain;
    }


    @Override
    public void save(TopicDomain topicDomain) {
        Topic topic = new Topic();
        topic.setTopicid(topicDomain.getTopicid());
        topic.setTitle(topicDomain.getTitle());
        topic.setContent(topicDomain.getContent());
        topic.setPublishtime(topicDomain.getPublishtime());
        topic.setUid(topicDomain.getUid());
        topic.setBoardid(topicDomain.getBoardid());
        this.picMapper.insert(topic);
        // 在这里  mybatis完成了两步操作: 1. insert   2. select 到最新的id后，存到pic中
        //pic中的id已经获取到
        //关键:
        topicDomain.setBoardid(topic.getBoardid());
    }


    @Override
    public void delete(Integer id) {
        this.picMapper.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TopicDomain> findOne(Integer boardid) {

        Example example = new Example(Topic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("boardid",boardid);
        List<Topic> list=  this.picMapper.selectByExample(example);
        List<TopicDomain> r = new ArrayList<TopicDomain>();
        for (Topic p : list) {
            TopicDomain pd = new TopicDomain(p.getTopicid(),p.getTitle(),p.getContent(),p.getPublishtime(),p.getModifytime(),p.getUid(),p.getBoardid());
            r.add(pd);
        }
        return r;
    }
    @Transactional(readOnly = true)
    @Override
    public TopicDomain find1(Integer id) {
        Topic p = this.picMapper.selectByPrimaryKey(id);
        TopicDomain topicDomain = new TopicDomain(p.getTopicid(),p.getTitle(),p.getContent(),p.getPublishtime(),p.getModifytime(),p.getUid(),p.getBoardid());
        return topicDomain;
    }

    @Transactional(readOnly = true)
    @Override
    public TopicDomain find(Integer id) {
        Topic p = this.picMapper.selectByPrimaryKey(id);
        TopicDomain topicDomain = new TopicDomain(p.getTopicid(),p.getTitle(),p.getContent(),p.getPublishtime(),p.getModifytime(),p.getUid(),p.getBoardid());
        return topicDomain;
    }

    @Override
    public void updata(TopicDomain topicDomain,Integer topicid) {
        Topic topic = new Topic();
        Example example = new Example(Topic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("topicid",topicid);
        topic.setTitle(topicDomain.getTitle());
        topic.setContent(topicDomain.getContent());
        topic.setUid(topicDomain.getUid());
        topic.setBoardid(topicDomain.getBoardid());
        this.picMapper.updateByExampleSelective(topic,example);
    }
}

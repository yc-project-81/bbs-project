package com.yc.piclib.service;

import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.TopicDomain;

import java.util.List;

public interface TopicService {

    public List<TopicDomain> list();

    public PageDomain<TopicDomain> listByPage(TopicDomain topicDomain);

    public void save(TopicDomain topicDomain);

    public void delete(Integer id);
}

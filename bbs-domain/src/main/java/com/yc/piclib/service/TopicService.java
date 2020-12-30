package com.yc.piclib.service;

import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.TopicDomain;

import java.util.List;

public interface TopicService {

    public List<TopicDomain> list();

    public PageDomain<TopicDomain> listByPage(TopicDomain topicDomain);

    public void save(TopicDomain topicDomain);

    public void delete(Integer id);

    public List<TopicDomain> findOne(Integer boardid);

    /**
     * 根据id查图片详情(    图片的metadata )
     *
     * @param id
     * @return
     */
    public TopicDomain find(Integer id);
}

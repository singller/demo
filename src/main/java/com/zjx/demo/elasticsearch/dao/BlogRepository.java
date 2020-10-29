package com.zjx.demo.elasticsearch.dao;

import com.zjx.demo.elasticsearch.dto.BlogModel;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.elasticsearch.dao
 * @date:2020/10/27
 **/
public interface BlogRepository extends ElasticsearchRepository<BlogModel, String> {

    @Query("{\"match_phrase\":{\"title\":\"?0\"}}")
    List<BlogModel> findByTitleLike(String keyword);

    @Query("{\"match_phrase\":{\"title\":\"?0\"}}")
    List<BlogModel> findByTitleCustom(String keyword);
}

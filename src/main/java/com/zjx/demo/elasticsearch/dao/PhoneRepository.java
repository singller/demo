package com.zjx.demo.elasticsearch.dao;

import com.zjx.demo.elasticsearch.dto.PhoneModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PhoneRepository extends ElasticsearchRepository<PhoneModel, String> {
}
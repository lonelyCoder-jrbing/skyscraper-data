package com.skyscraper.skyscraperdata.elasticsearch.helper;

import com.skyscraper.skyscraperdata.dto.PaperDTO;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * create by sumerian on 2020/7/3
 * <p>
 * desc:es增删改查
 **/
@Component
public class EsOperatorHepler {

    @Autowired
    RestHighLevelClient clients;

    public void intsert(PaperDTO paperDTO) throws IOException {
        Map<String, Object> paperProperty = new HashMap<>();
        paperProperty.put("paperId", paperDTO.getPaperId());
        paperProperty.put("major", paperDTO.getMajor());
        paperProperty.put("paperTitle",paperDTO.getTitle());
        paperProperty.put("school", paperDTO.getSchool());
        paperProperty.put("content", paperDTO.getContent());
        IndexRequest paper = new IndexRequest("paper").source(paperProperty);
        clients.index(paper, RequestOptions.DEFAULT);
    }


}

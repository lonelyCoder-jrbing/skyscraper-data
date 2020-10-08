package com.skyscraper.data.service.service.paper;
import com.alibaba.fastjson.JSON;
import com.skyscraper.data.api.dto.PaperDTO;
import com.skyscraper.data.api.paperapi.PaperCommentApi;
import com.skyscraper.data.service.elasticsearch.config.EsScrollUtil;
import com.skyscraper.data.service.elasticsearch.constant.PaperConstant;
import org.apache.dubbo.config.annotation.Service;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.apache.dubbo.rpc.cluster.Constants.DEFAULT_LOADBALANCE;

/**
 * create by sumerian on 2020/10/8
 * <p>
 * desc:
 **/
@Service(group = "PaperCommentService",
        retries = 2,
        timeout = 10000,
        loadbalance = DEFAULT_LOADBALANCE,   //默认使用的是random
        actives = 10,
        version = "1.1.0")
public class PaperCommentServiceImpl implements PaperCommentApi {
    @Autowired
    RestHighLevelClient clients;

    @Override
    public PaperDTO getPaperById(String id) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest request = new SearchRequest(PaperConstant.PAPER_INDEX);
        searchSourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery(PaperConstant.PAPER_ID, id));
        request.source(searchSourceBuilder);
        List<SearchHit> hits = EsScrollUtil.scrollSearchAll(clients, 300L, request);

        PaperDTO paperDTO = null;
        for (SearchHit hit : hits) {
            paperDTO = JSON.parseObject(hit.getSourceAsString(), PaperDTO.class);
        }
        return paperDTO;
    }

    @Override
    public List<PaperDTO> getPaperByMajor(String major) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest request = new SearchRequest(PaperConstant.PAPER_INDEX);
        searchSourceBuilder.query(QueryBuilders.fuzzyQuery(PaperConstant.MAJOR, major));
        request.source(searchSourceBuilder);
        List<SearchHit> hits = EsScrollUtil.scrollSearchAll(clients, 300L, request);
        List<PaperDTO> res = new ArrayList<>(hits.size());
        for (SearchHit hit : hits) {
            PaperDTO person = JSON.parseObject(hit.getSourceAsString(), PaperDTO.class);
            res.add(person);
        }
        return res;
    }

    @Override
    public List<PaperDTO> getPaperBySchool(String school) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest request = new SearchRequest(PaperConstant.PAPER_INDEX);
        searchSourceBuilder.query(QueryBuilders.fuzzyQuery(PaperConstant.SCHOOL, school));
        request.source(searchSourceBuilder);
        List<SearchHit> hits = EsScrollUtil.scrollSearchAll(clients, 300L, request);
        List<PaperDTO> res = new ArrayList<>(hits.size());
        for (SearchHit hit : hits) {
            PaperDTO paperDTO = JSON.parseObject(hit.getSourceAsString(), PaperDTO.class);
            res.add(paperDTO);
        }
        return res;
    }

    @Override
    public List<PaperDTO> getPaperByContent(String content) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest request = new SearchRequest(PaperConstant.PAPER_INDEX);
        searchSourceBuilder.query(QueryBuilders.fuzzyQuery(PaperConstant.CONTENT, content));
        request.source(searchSourceBuilder);
        List<SearchHit> hits = EsScrollUtil.scrollSearchAll(clients, 300L, request);
        List<PaperDTO> res = new ArrayList<>(hits.size());
        for (SearchHit hit : hits) {
            PaperDTO paperDTO = JSON.parseObject(hit.getSourceAsString(), PaperDTO.class);
            res.add(paperDTO);
        }
        return res;
    }

    @Override
    public List<PaperDTO> getPaperByTiltle(String title) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest request = new SearchRequest(PaperConstant.PAPER_INDEX);
        searchSourceBuilder.query(QueryBuilders.fuzzyQuery(PaperConstant.PAPER_TITLE, title));
        request.source(searchSourceBuilder);
        List<SearchHit> hits = EsScrollUtil.scrollSearchAll(clients, 300L, request);
        List<PaperDTO> res = new ArrayList<>(hits.size());
        for (SearchHit hit : hits) {
            PaperDTO paperDTO = JSON.parseObject(hit.getSourceAsString(), PaperDTO.class);
            res.add(paperDTO);
        }
        return res;
    }


    SearchRequest getSearchRequest() {
        return null;
    }

}

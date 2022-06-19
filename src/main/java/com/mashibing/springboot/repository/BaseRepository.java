package com.mashibing.springboot.repository;

import com.alibaba.fastjson.JSON;
import com.mashibing.springboot.entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ScriptType;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Order;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;

@Configuration
public class BaseRepository<T> {
    AtomicLong atomicLong = new AtomicLong(System.currentTimeMillis());
    @Autowired
    private ElasticsearchRestTemplate template;

    public BaseRepository() {
    }

    public void createIndex(String... indexName) {
        boolean create = this.template.indexOps(IndexCoordinates.of(indexName)).create();
        System.out.println("createIndex " + indexName + " -> " + create);
    }

    public void existsIndex(String... indexName) {
        boolean exists = this.template.indexOps(IndexCoordinates.of(indexName)).exists();
        System.out.println("existsIndex " + indexName + " -> " + exists);
    }

    public void deleteIndex(String... indexName) {
        boolean delete = this.template.indexOps(IndexCoordinates.of(indexName)).delete();
        System.out.println("delete " + indexName + " -> " + delete);
    }

    public void insert(User... users) {
        IndexCoordinates index = IndexCoordinates.of(new String[]{"user"});
        this.test(users);
        User save = (User)this.template.save(users[0]);
        this.test(users);
        User[] save1 = (User[])this.template.save(users, index);
        IndexQuery indexQuery = (new IndexQueryBuilder()).withId(String.valueOf(this.atomicLong.incrementAndGet())).withObject(users[0]).build();
        this.template.index(indexQuery, index);
        List<IndexQuery> listIndex = new ArrayList(users.length);
        this.test(users);
        User[] var8 = users;
        int var9 = users.length;

        for(int var10 = 0; var10 < var9; ++var10) {
            User u = var8[var10];
            IndexQuery batchInsert = new IndexQuery();
            batchInsert.setId(String.valueOf(System.currentTimeMillis()));
            batchInsert.setSource(JSON.toJSONString(u));
            listIndex.add(indexQuery);
        }

        this.template.bulkIndex(listIndex, index);
    }

    public void delete(User... users) {
        IndexCoordinates index = IndexCoordinates.of(new String[]{"user"});
        BoolQueryBuilder price = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("brand", "huawei"));
        NativeSearchQuery query = new NativeSearchQuery(price);
        this.template.delete(query, User.class);
    }

    public void update(User... users) {
        IndexCoordinates index = IndexCoordinates.of(new String[]{"user"});
        NativeSearchQueryBuilder search = new NativeSearchQueryBuilder();
        BoolQueryBuilder bool = QueryBuilders.boolQuery().must(QueryBuilders.termsQuery("brand", new String[]{"oppo", "huawei"}));
        NativeSearchQuery query = search.withSourceFilter(new FetchSourceFilter(new String[]{"name"}, (String[])null)).withQuery(bool).build();
        Map<String, Object> params = new HashMap(1);
        params.put("price", 2051);
        UpdateQuery scriptUpdate = UpdateQuery.builder(query).withScriptType(ScriptType.INLINE).withParams(params).withScript("if(ctx._source.containsKey('price')){ctx._source.price = params.price}").build();
        this.template.updateByQuery(scriptUpdate, index);
    }

    public void list(Integer currentPage, Integer limit) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        NativeSearchQuery query = builder.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("brand", "oppo"))).build();
        query.setPageable(PageRequest.of(0, 300)).addSort(Sort.by(new Sort.Order[]{new Order(Direction.DESC, "price")}));
        this.template.search(query, User.class);
    }

    public void test(User... users) {
        User[] var2 = users;
        int var3 = users.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            User user = var2[var4];
            user.setId(String.valueOf(this.atomicLong.incrementAndGet()));
        }

    }
}

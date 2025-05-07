package com.example.controller;
import com.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@RestController
public class SearchController {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @GetMapping("/search")
    public List<Product> search(@RequestParam String query) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder()
            .withQuery(multiMatchQuery(query, "title^2", "description"));
        SearchHits<Product> searchHits = elasticsearchOperations.search(queryBuilder.build(), Product.class);
        return searchHits.getSearchHits().stream()
            .map(hit -> hit.getContent())
            .collect(Collectors.toList());
    }
}

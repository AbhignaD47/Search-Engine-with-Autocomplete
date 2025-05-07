package com.example.service;
import com.example.model.Product;
import com.example.util.SpellChecker;
import com.example.util.Trie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private Trie trie = new Trie();
    private SpellChecker spellChecker;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public void indexProducts(List<Product> products) {
        List<String> dictionary = products.stream().map(Product::getTitle).collect(Collectors.toList());
        spellChecker = new SpellChecker(dictionary);
        for (Product product : products) {
            elasticsearchOperations.save(product);
            trie.insert(product.getTitle());
        }
    }

    public List<String> getSuggestions(String prefix) {
        return trie.autocomplete(prefix);
    }

    public String suggestCorrection(String query) {
        return spellChecker.suggestCorrection(query);
    }
}

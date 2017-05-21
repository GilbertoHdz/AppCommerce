package com.example.developer.appcommerce.data.products.datasource.memory;

import com.example.developer.appcommerce.products.domain.criteria.ProductCriteria;
import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.List;

/**
 * Created by developer on 5/21/17.
 */

public interface IMemoryProductsDataSource {

    List<Product> find(ProductCriteria criteria);

    void save(Product product);

    void deleteAll();

    boolean mapIsNull();

}

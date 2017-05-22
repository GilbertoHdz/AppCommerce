package com.example.developer.appcommerce.products.domain.criteria;

import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.List;

/**
 * Created by developer on 5/21/17.
 */

public interface ProductCriteria {

    List<Product> match(List<Product> products);

}

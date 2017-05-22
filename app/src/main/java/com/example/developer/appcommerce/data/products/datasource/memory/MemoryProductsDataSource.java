package com.example.developer.appcommerce.data.products.datasource.memory;

import com.google.common.collect.Lists;
import com.example.developer.appcommerce.products.domain.criteria.ProductCriteria;
import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by developer on 5/21/17.
 * Implementación concreta de la fuente de datos en memoria
 */

public class MemoryProductsDataSource implements IMemoryProductsDataSource {

    private static HashMap<String, Product> mCachedProducts;

    @Override
    public List<Product> find(ProductCriteria criteria) {
        //Criteria
        ArrayList<Product> products =
                Lists.newArrayList(mCachedProducts.values());
        return criteria.match(products);
    }

    @Override
    public void save(Product product) {
        if (mCachedProducts == null) {
            mCachedProducts = new LinkedHashMap<>();
        }

        mCachedProducts.put(product.getCode(), product);
    }

    @Override
    public void deleteAll() {
        if (mCachedProducts == null) {
            mCachedProducts = new LinkedHashMap<>();
        }

        mCachedProducts.clear();
    }

    @Override
    public boolean mapIsNull() {
        return mCachedProducts == null;
    }
}

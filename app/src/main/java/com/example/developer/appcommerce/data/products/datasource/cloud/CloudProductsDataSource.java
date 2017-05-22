package com.example.developer.appcommerce.data.products.datasource.cloud;

import android.os.Handler;

import com.example.developer.appcommerce.products.domain.criteria.ProductCriteria;
import com.example.developer.appcommerce.products.domain.model.Product;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by developer on 5/21/17.
 */

public class CloudProductsDataSource implements ICloudProductsDataSource {

    private static HashMap<String, Product> API_DATA;
    private static final long LATENCY = 2000;

    static {
        API_DATA = new LinkedHashMap<>();

        for (int i = 0; i < 100; i++) {
            addProduct(43, "Producto " + (i + 1), "file:///android_asset/mock-product.png");
        }
    }

    private static void addProduct(float price, String name, String imageUrl) {
        Product newProduct = new Product(name, price, imageUrl);
        API_DATA.put(newProduct.getCode(), newProduct);
    }

    @Override
    public void getProducts(final ProductServiceCallback callback, ProductCriteria criteria) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onLoaded( Lists.newArrayList(API_DATA.values()) );
            }
        }, LATENCY);
    }
}

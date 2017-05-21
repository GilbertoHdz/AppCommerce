package com.example.developer.appcommerce.data.products.datasource.cloud;

import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.List;

/**
 * Created by developer on 5/21/17.
 */

public interface ICloudProductsDataSource {

    interface ProductServiceCallback {

        void onLoaded(List<Product> products);

        void onError(String error);

    }

    void getProducts(ProductServiceCallback callback);

}

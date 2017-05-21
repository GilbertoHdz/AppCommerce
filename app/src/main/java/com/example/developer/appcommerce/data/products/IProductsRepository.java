package com.example.developer.appcommerce.data.products;

import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.List;

/**
 * Created by developer on 5/21/17.
 */

public interface IProductsRepository {

    /**
     * GetProductsCallback procesa el flujo exitoso (onProductsLoaded())
     * y fallido (onProductsLoadError() ) de la carga de productos.
     */
    interface GetProductsCallback {

        void onProductsLoaded(List<Product> products);

        void onDataNotAvaliable(String error);

        void getProducts(GetProductsCallback callback);

        void refreshProducts();

    }
}

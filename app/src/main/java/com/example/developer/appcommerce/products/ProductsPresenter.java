package com.example.developer.appcommerce.products;

import com.example.developer.appcommerce.data.products.ProductsRepository;

/**
 * Created by developer on 5/21/17.
 */

public class ProductsPresenter implements ProductsMvp.Presenter {

    private final ProductsRepository mProductsRepository;
    private final ProductsMvp.View mProductsView;

    public ProductsPresenter(ProductsRepository mProductsRepository, ProductsMvp.View mProductsView) {
        this.mProductsRepository = mProductsRepository;
        this.mProductsView = mProductsView;
    }

    @Override
    public void loadProducts(boolean reload) {

    }

}

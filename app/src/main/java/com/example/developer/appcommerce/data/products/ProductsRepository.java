package com.example.developer.appcommerce.data.products;

import android.content.Context;

import com.example.developer.appcommerce.data.products.datasource.cloud.ICloudProductsDataSource;
import com.example.developer.appcommerce.data.products.datasource.memory.IMemoryProductsDataSource;
import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.List;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

/**
 * Created by developer on 5/21/17.
 */

public class ProductsRepository implements IProductsRepository {

    private final IMemoryProductsDataSource mMemoryProductsDataSource;
    private final ICloudProductsDataSource mCloudProductsDataSource;
    private final Context mContext;

    private boolean mReload;

    public ProductsRepository(IMemoryProductsDataSource mMemoryProductsDataSource, ICloudProductsDataSource mCloudProductsDataSource, Context mContext) {
        this.mMemoryProductsDataSource = checkNotNull(mMemoryProductsDataSource);
        this.mCloudProductsDataSource = checkNotNull(mCloudProductsDataSource);
        this.mContext = checkNotNull(mContext);
    }
}

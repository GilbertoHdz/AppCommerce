package com.example.developer.appcommerce.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.developer.appcommerce.data.products.ProductsRepository;
import com.example.developer.appcommerce.data.products.datasource.cloud.CloudProductsDataSource;
import com.example.developer.appcommerce.data.products.datasource.memory.MemoryProductsDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Contenedor de dependencias
 */

public final class DependencyProvider {

    private static Context mContext;
    private static MemoryProductsDataSource memorySource = null;
    private static CloudProductsDataSource cloudSource = null;
    private static ProductsRepository mProductsRepository = null;

    private DependencyProvider() {
    }

    public static ProductsRepository provideProductsRepository(@NonNull Context context) {
        mContext = checkNotNull(context);
        if (mProductsRepository == null) {
            mProductsRepository = new ProductsRepository(getMemorySource(),
                    getCloudSource(), context);
        }
        return mProductsRepository;
    }

    public static MemoryProductsDataSource getMemorySource() {
        if (memorySource == null) {
            memorySource = new MemoryProductsDataSource();
        }
        return memorySource;
    }

    public static CloudProductsDataSource getCloudSource() {
        if (cloudSource == null) {
            cloudSource = new CloudProductsDataSource();
        }
        return cloudSource;
    }
}

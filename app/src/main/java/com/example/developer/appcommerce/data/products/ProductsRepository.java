package com.example.developer.appcommerce.data.products;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

    @Override
    public void getProducts(GetProductsCallback callback) {
        if (!mMemoryProductsDataSource.mapIsNull() && !mReload) {
            getProductsFromMemory(callback);
            return;
        }
        if (mReload) {
            getProductsFromServer(callback);
        } else {
            List<Product> products = mMemoryProductsDataSource.find(null);

            if (products.size() > 0) {
                callback.onProductsLoaded(products);
            } else {
                getProductsFromServer(callback);
            }
        }
    }

    @Override
    public void refreshProducts() {
        mReload = true;
    }

    private void getProductsFromMemory(GetProductsCallback callback) {
        callback.onProductsLoaded( mMemoryProductsDataSource.find(null) );
    }

    private void getProductsFromServer(final GetProductsCallback callback) {
        if (!isOnline()) {
            callback.onDataNotAvailable("No hay conexiòn de red.");
            return;
        }

        mCloudProductsDataSource.getProducts(
                new ICloudProductsDataSource.ProductServiceCallback() {

            @Override
            public void onLoaded(List<Product> products) {
                refreshMemoryDataSource(products);
                getProductsFromMemory(callback);
            }

            @Override
            public void onError(String error) {

                callback.onDataNotAvailable(error);
            }
        });
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    private void refreshMemoryDataSource(List<Product> products) {
        mMemoryProductsDataSource.deleteAll();
        for (Product product : products) {
            mMemoryProductsDataSource.save(product); }
        mReload = false;
    }


}

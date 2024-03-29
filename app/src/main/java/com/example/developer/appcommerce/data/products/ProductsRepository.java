package com.example.developer.appcommerce.data.products;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.developer.appcommerce.data.products.datasource.cloud.ICloudProductsDataSource;
import com.example.developer.appcommerce.data.products.datasource.memory.IMemoryProductsDataSource;
import com.example.developer.appcommerce.products.domain.criteria.ProductCriteria;
import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by developer on 5/21/17.
 */

public class ProductsRepository implements IProductsRepository {

    private final IMemoryProductsDataSource mMemoryProductsDataSource;
    private final ICloudProductsDataSource mCloudProductsDataSource;
    private final Context mContext;

    private boolean mReload;


    public ProductsRepository(IMemoryProductsDataSource memoryDataSource,
                              ICloudProductsDataSource cloudDataSource,
                              Context context) {
        mMemoryProductsDataSource = checkNotNull(memoryDataSource);
        mCloudProductsDataSource = checkNotNull(cloudDataSource);
        mContext = checkNotNull(context);
    }

    @Override
    public void getProducts(GetProductsCallback callback, final ProductCriteria criteria) {
        // Este código lo desactive para que vieras el efecto "Endless scroll" cada vez
        // que llegas al límite de la lista.
        // Quita los comentarios para activar de nuevo la fuente en memoria.
        // Esto aumenta la velocidad de consulta.
        /*if (!mMemoryProductsDataSource.mapIsNull() && !mReload) {
            getProductsFromMemory(callback, criteria);
            return;
        }*/

        // Pon de nuevo a {mReload} en el if para activar
        // la fuente de datos en memoria.
        if (true) {
            getProductsFromServer(callback, criteria);
        } else {
            List<Product> products = mMemoryProductsDataSource.find(null);

            if (products.size() > 0) {
                callback.onProductsLoaded(products);
            } else {
                getProductsFromServer(callback, criteria);
            }
        }

    }

    private void getProductsFromMemory(GetProductsCallback callback,
                                       ProductCriteria criteria) {

        callback.onProductsLoaded(mMemoryProductsDataSource.find(criteria));
    }

    private void getProductsFromServer(final GetProductsCallback callback,
                                       final ProductCriteria criteria) {

        if (!isOnline()) {
            callback.onDataNotAvailable("No hay conexión de red.");
            return;
        }

        mCloudProductsDataSource.getProducts(
                new ICloudProductsDataSource.ProductServiceCallback() {
                    @Override
                    public void onLoaded(List<Product> products) {
                        refreshMemoryDataSource(products);
                        getProductsFromMemory(callback, criteria);
                    }

                    @Override
                    public void onError(String error) {
                        callback.onDataNotAvailable(error);
                    }
                },
                null);
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
            mMemoryProductsDataSource.save(product);
        }
        mReload = false;
    }

    @Override
    public void refreshProducts() {
        mReload = true;
    }

}

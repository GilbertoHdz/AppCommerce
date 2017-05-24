package com.example.developer.appcommerce.data.api;

import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/** Created by developer on 5/24/17.
 * Endpoints para la API
 */

public interface RestService {

    @GET("products")
    Call<List<Product>> getProducts();

}

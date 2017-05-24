package com.example.developer.appcommerce.data.products.datasource.cloud;

import com.example.developer.appcommerce.data.api.ErrorResponse;
import com.example.developer.appcommerce.data.api.RestService;
import com.example.developer.appcommerce.products.domain.criteria.ProductCriteria;
import com.example.developer.appcommerce.products.domain.model.Product;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by developer on 5/21/17.
 */

public class CloudProductsDataSource implements ICloudProductsDataSource {

    private static HashMap<String, Product> API_DATA;
    private static final long LATENCY = 2000;

    public static final String BASE_URL_AVD = "http://10.0.2.2/api.appproducts.com/v1/";

    private final Retrofit mRetrofit;
    private final RestService mRestService;

    public CloudProductsDataSource() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_AVD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRestService = mRetrofit.create(RestService.class);
    }

    @Override
    public void getProducts(final ProductServiceCallback callback, ProductCriteria criteria) {
        /*
        Posteriormente, enviaremos la petición al servidor.
        La forma de hacerlo es con los métodos Call.execute() y Call.enqueue().
        El primero ejecuta la petición en el hilo principal.
        El segundo lo hace asíncronamente y notifica su resultado con la interfaz Callback<T>.

        Usaremos enqueue(): Evitará que el hilo de UI se entorpezca y Android nos arroje diálogos ANR.
         */

        //primero llamamos a RestService.getProducts() para apilar la petición en call.
        Call<List<Product>> call = mRestService.getProducts();

        //Luego llamamos a enqueue() y tomamos su resultado con una interfaz anónima.
        call.enqueue(new Callback<List<Product>>() {

            //retorna la respuesta en su parámetro . Si fue exitosa
            //(isSuccesfull()), entonces retornamos el contenido (response.body()).
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                //Para procesar la respuesta, podemos aislar el comportamiento en un método
                processGetProductsResponse(response, callback);
            }

            //De lo contrario en onFailure(), notificamos el error.
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    private void processGetProductsResponse(Response<List<Product>> response, ProductServiceCallback callback) {
        String error = "Ha ocurrido un error";
        ResponseBody errorBody = response.errorBody();

        // ¿Hubo un error?
        if (errorBody != null) {
            // ¿Fué de la API?
            if (errorBody.contentType().subtype().equals("json")) {
                try {
                    // Parseamos el objeto
                    ErrorResponse er = new Gson()
                            .fromJson(errorBody.string(), ErrorResponse.class);
                    error = er.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            callback.onError(error);
        }

        // ¿LLegaron los productos sanos y salvos?
        if (response.isSuccessful()) {
            callback.onLoaded(response.body());
        }
    }

}

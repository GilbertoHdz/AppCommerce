package com.example.developer.appcommerce.products;

import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.List;

/**
 * Created by developer on 5/21/17.
 */

public interface ProductsMvp {
    interface View {

        //Lista de productos
        void showProducts(List<Product> products);

        //Estado de carga
        void showLoadingState(boolean show);

        //Estado Vacio (No existen productos)
        void showEmptyState();

        //Errores (Error de conexiòn, etc.)
        void showProductsError(String msg);

        //La vista de un páginado se trata de agregar más elementos a la lista
        // cada vez que se llegue al límite de elementos.
        void showProductsPage(List<Product> products);

        //indicador circular de progreso al final de la lista
        void showLoadMoreIndicator(boolean show);

        //Accionador Indicador progreso al final de la lista (On/Off)
        void allowMoreData(boolean show);
    }

    interface Presenter {
        //El presentador solo debe recuperar los datos del repositorio y los pone en la vista.
        void loadProducts(boolean reload);

    }
}

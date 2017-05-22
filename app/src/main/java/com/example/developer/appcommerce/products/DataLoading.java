package com.example.developer.appcommerce.products;

/**
 * Permite saber si un elemento relacionado con datos está cargandolos o si no posee más
 */
interface DataLoading {

    boolean isLoadingData();

    boolean isThereMoreData();
}

package com.example.developer.appcommerce.products;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by developer on 5/22/17.
 * {@link RecyclerView.OnScrollListener} para escrolling infinito
 */

public abstract class InfinityScrollListener extends RecyclerView.OnScrollListener {

    private static final int VISIBLE_THRESHOLD = 5;
    private final LinearLayoutManager mLayoutManager;
    private final DataLoading mDataLoading;

    public InfinityScrollListener( DataLoading mDataLoading, LinearLayoutManager mLayoutManager) {
        this.mLayoutManager = checkNotNull(mLayoutManager);
        this.mDataLoading = checkNotNull(mDataLoading);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy < 0 || mDataLoading.isLoadingData() || !mDataLoading.isThereMoreData()) {
            return;
        }

        //Cantidad items visibles actualmente en la lista.
        final int visibleItemCount = recyclerView.getChildCount();

        //Cantidad total items en el Adaptador
        final int totalItemCount = mLayoutManager.getItemCount();

        //Posición de primer ítem visible en la lista.
        final int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

        if ((totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            onLoadMore();
        }

    }

    public abstract void onLoadMore();

}

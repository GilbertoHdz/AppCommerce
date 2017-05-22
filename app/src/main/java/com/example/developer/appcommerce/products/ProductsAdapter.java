package com.example.developer.appcommerce.products;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.developer.appcommerce.R;
import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.List;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

/**
 * Created by developer on 5/21/17.
 */

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DataLoading {

    private List<Product> mProducts;
    private ProductItemListener mItemListener;

    private boolean mLoading = false;
    private boolean mMoreData = false;

    public ProductsAdapter(List<Product> mProducts, ProductItemListener mItemListener) {
        this.mProducts = mProducts;
        this.mItemListener = mItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view;
        view = inflater.inflate(R.layout.item_product, parent, false);
        return new ProductsHolder(view, mItemListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ProductsHolder) {
            Product product = mProducts.get(position);
            ProductsHolder productsHolder = (ProductsHolder) viewHolder;
            productsHolder.price.setText(product.getFormatedPrice());
            productsHolder.name.setText(product.getName());
            productsHolder.unitsInStock.setText(product.getFormattedUnitsInStock());

            Glide.with(viewHolder.itemView.getContext())
                    .load(product.getImageUrl())
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                    //.centerCrop()
                    .into(productsHolder.featuredImage);
        }
    }

    public void replaceData(List<Product> notes) {
        setList(notes);
        notifyDataSetChanged();
    }

    private void setList(List<Product> notes) {
        mProducts = checkNotNull(notes);
    }
    public void addData(List<Product> products) {
        mProducts.addAll(products);
    }

    @Override
    public int getItemCount() {
        return getDataItemCount();
    }

    public Product getItem(int position) {
        return mProducts.get(position);
    }
    public int getDataItemCount() {
        return mProducts.size();
    }

    @Override
    public boolean isLoadingData() {
        return mLoading;
    }

    @Override
    public boolean isThereMoreData() {
        return mMoreData;
    }

    public class ProductsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public TextView price;
        public ImageView featuredImage;
        public TextView unitsInStock;

        private ProductItemListener mItemListener;

        public ProductsHolder(View itemView, ProductItemListener listener) {
            super(itemView);
            mItemListener = listener;
            name = (TextView) itemView.findViewById(R.id.product_name);
            price = (TextView) itemView.findViewById(R.id.product_price);
            unitsInStock = (TextView) itemView.findViewById(R.id.units_in_stock);
            featuredImage = (ImageView) itemView.findViewById(R.id.product_featured_image);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }

    public interface ProductItemListener {
        void onProductClick(Product clickedNote);
    }

}

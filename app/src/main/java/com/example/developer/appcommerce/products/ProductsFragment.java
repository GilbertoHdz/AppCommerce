package com.example.developer.appcommerce.products;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.developer.appcommerce.R;
import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragment extends Fragment {

    private RecyclerView mProductsList;
    private ProductsAdapter mProductsAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mEmptyView;

    private ProductsAdapter.ProductItemListener mItemListener = new ProductsAdapter.ProductItemListener() {
        @Override
        public void onProductClick(Product clickedNote) {
            //Aquì se lanzara la pantalla de detalle del productor
        }
    };

    public ProductsFragment() {
        //Required empty public constructor
    }


    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductsAdapter = new ProductsAdapter(new ArrayList<Product>(0), mItemListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_products, container, false);

        //Referencias UI
        mProductsList = (RecyclerView) root.findViewById(R.id.products_list);
        mEmptyView = root.findViewById(R.id.noProducts);
        mSwipeRefreshLayout = (SwipeRefreshLayout)
                root.findViewById(R.id.refresh_layout);
        //Setup
        setUpProductsList();
        setUptRefreshLayout();
        return root;
    }

    //Relacionar la lista con el Adapter
    private void setUpProductsList() {
        mProductsList.setAdapter(mProductsAdapter);
        mProductsList.setHasFixedSize(true);
    }

    //Swipe Rrefresh
    private void setUptRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }
}

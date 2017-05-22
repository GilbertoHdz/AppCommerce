package com.example.developer.appcommerce.products;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.developer.appcommerce.R;
import com.example.developer.appcommerce.di.DependencyProvider;
import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragment extends Fragment implements ProductsMvp.View {

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

    private ProductsPresenter mProductsPresenter;

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

        /*
        Para eliminar la responsabilidad del presentador de saber cómo crear el repositorio.
        De esta forma inyectamos la dependencia ensamblando los componentes.
        */
        mProductsPresenter = new ProductsPresenter(
                DependencyProvider.provideProductsRepository(getActivity()), this);

        setRetainInstance(true);
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            mProductsPresenter.loadProducts(false);
        }
    }

    //Relacionar la lista con el Adapter
    private void setUpProductsList() {
        mProductsList.setAdapter(mProductsAdapter);

        final LinearLayoutManager layoutManager =
                (LinearLayoutManager) mProductsList.getLayoutManager();

        // Se agrega escucha de scroll infinito.
        mProductsList.addOnScrollListener(
                new InfinityScrollListener(mProductsAdapter, layoutManager){
            @Override
            public void onLoadMore() {
                mProductsPresenter.loadProducts(false);
            }
        });

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

    @Override
    public void showProducts(List<Product> products) {

        //Reemplazar los datos del adapter
        mProductsAdapter.replaceData(products);

        //Mostrar la lista
        mProductsList.setVisibility(View.VISIBLE);

        //Ocular el estado Vacio
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingState(final boolean show) {
        //*Iniciar la animacion de la recarga*

        //Primero Comprobar que el fragmento esta disponible
        if (getView() == null) {
            return;
        }

        //Se utiliza POST(Ejecutar nueva tarea) para encolar las llamas de recarga y
        //evitar posibles errores en cuanto concurrencia.
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(show);
            }
        });
    }

    @Override
    public void showEmptyState() {
        //Solo mostramos el View Vacio y Ocultamos la Lista
        mProductsList.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProductsError(String msg) {
        //Los errores provenientes desde el Presentador se manejan con un Toast
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG);
    }

    @Override
    public void showProductsPage(List<Product> products) {
        //Se genera una pila los productos a la pagina
        mProductsAdapter.addData(products);
    }

    @Override
    public void showLoadMoreIndicator(boolean show) {
        //muestra el view de “cargar más” y
        if (!show) {
            mProductsAdapter.dataFinishedLoading();
        } else {
            mProductsAdapter.dataStartedLoading();
        }
    }

    @Override
    public void allowMoreData(boolean show) {
        //determina si aún hay datos.
        mProductsAdapter.setMoreData(show);
    }
}

package com.example.developer.appcommerce.products.domain.criteria;

import com.example.developer.appcommerce.products.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 5/22/17.
 * Criterio para determinar los productos que pertenecen a una página específica
 */

public class PagingProductCriteria implements ProductCriteria {

    private final int mPage;
    private final int mLimit;

    public PagingProductCriteria(int page, int limit) {
        this.mPage = page;
        this.mLimit = limit;
    }


    @Override
    public List<Product> match(List<Product> products) {
        List<Product> criteriaProducts = new ArrayList<>();

        //Sanidad
        if(mLimit <= 0 || mPage <=0){
            return criteriaProducts;
        }

        int size = products.size();
        int numPages = size / mLimit;
        int a,b;

        if (mPage > numPages) {
            return criteriaProducts;
        }

        a = (mPage - 1) * mLimit;

        if (a == size) {
            return criteriaProducts;
        }

        b = a + mLimit;

        criteriaProducts = products.subList(a, b);

        return criteriaProducts;
    }

}

package com.example.developer.appcommerce.login.presentation;

import com.example.developer.appcommerce.products.ProductsMvp;

/**
 * Created by developer on 5/23/17.
 */

public interface LoginMvp {

    interface View {

        void showEmailError();

        void showPasswordError();

        void login(String email, String password);

        void showLoginError(String error);

        void showLoadingIndicator(boolean show);

        void showProductsScreen();

        void setPresenter(ProductsMvp.Presenter presenter);

    }

    interface Presenter{

    }

}

package com.example.developer.appcommerce.login.presentation;

/** Created by developer on 5/23/17.
 * Implementar reacciones como métodos
 * ********* Reacciones ************
 * Mostrar error de correo invalido
 * Mostrar error de contraseña invalida
 * Loguear
 * Mostrar errores de login
 * Mostrar progreso
 * Ir a la screen de productos
 */

public interface LoginMvp {

    interface View {

        void showEmailError();

        void showPasswordError();

        void login(String email, String password);

        void showLoginError(String error);

        void showLoadingIndicator(boolean show);

        void showProductsScreen();

        void setPresenter(Presenter presenter);

    }

    interface Presenter{

    }

}

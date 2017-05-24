package com.example.developer.appcommerce.login.presentation;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.developer.appcommerce.R;
import com.example.developer.appcommerce.products.ProductsMvp;

public class LoginFragment extends Fragment implements LoginMvp.View {

    // Miembros UI
    private ImageView mImageLogo;
    private EditText mEmailField;
    private TextInputLayout mEmailFloatingLabel;
    private EditText mPasswordField;
    private TextInputLayout mPasswordFloatingLabel;
    private Button mButtonLogin;
    private ProgressBar mProgressLogin;

    // Relación de composición
    private LoginMvp.Presenter mLoginPresenter;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Instancias de los layout del fragment_login
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View uiRoot = inflater.inflate(R.layout.fragment_login, container, false);

        // UI
        mImageLogo =
                (ImageView) uiRoot.findViewById(R.id.image_logo);
        mEmailField =
                (EditText) uiRoot.findViewById(R.id.text_field_email);
        mEmailFloatingLabel =
                (TextInputLayout) uiRoot.findViewById(R.id.float_label_email);
        mPasswordField =
                (EditText) uiRoot.findViewById(R.id.text_field_password);
        mPasswordFloatingLabel =
                (TextInputLayout) uiRoot.findViewById(R.id.float_label_password);
        mButtonLogin =
                (Button) uiRoot.findViewById(R.id.button_login);
        mProgressLogin =
                (ProgressBar) uiRoot.findViewById(R.id.progress_login);

        // Setup UI
        Glide.with(this).load("file:///android_asset/logo.png").into(mImageLogo);

        mEmailField.addTextChangedListener(new TextWatcherLabel(mEmailFloatingLabel));
        mPasswordField.addTextChangedListener(new TextWatcherLabel(mPasswordFloatingLabel));

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return uiRoot;
    }

    @Override
    public void showEmailError() {

    }

    @Override
    public void showPasswordError() {

    }

    @Override
    public void login(String email, String password) {

    }

    @Override
    public void showLoginError(String error) {

    }

    @Override
    public void showLoadingIndicator(boolean show) {

    }

    @Override
    public void showProductsScreen() {

    }

    @Override
    public void setPresenter(ProductsMvp.Presenter presenter) {

    }


    //Clase personalizada para resetear el error según la etiqueta
    class TextWatcherLabel implements TextWatcher {

        private final TextInputLayout mFloatingLabel;

        public TextWatcherLabel(TextInputLayout floatingLabel) {
            mFloatingLabel = floatingLabel;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mFloatingLabel.setError(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

}

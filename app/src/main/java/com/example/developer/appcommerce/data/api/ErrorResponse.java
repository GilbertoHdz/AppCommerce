package com.example.developer.appcommerce.data.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by developer on 5/24/17.
 */

public class ErrorResponse {

    @SerializedName("message")
    String mMessage;

    public String getMessage() {
        return mMessage;
    }

}

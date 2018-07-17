package com.example.colors2web.user_management.Validation;

import android.widget.EditText;

public class Validation {

    public static boolean isValidData(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            editText.requestFocus();
            editText.setError("Field Required");
            return false;
        } else {
            return true;
        }

    }
}

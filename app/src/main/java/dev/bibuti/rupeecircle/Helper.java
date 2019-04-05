package dev.bibuti.rupeecircle;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Helper {

    static boolean validateForm(String fullName, String email, String password, String confirmPassword, String mobile) {

        if (fullName.isEmpty() ||
                email.isEmpty() ||
                password.isEmpty() ||
                confirmPassword.isEmpty() ||
                mobile.isEmpty()) {
            return false;
        } else if (!password.equals(confirmPassword)) {
            return false;
        } else return mobile.length() == 10;

    }

    static boolean validateForm(String email, String password) {
        return !email.isEmpty() && !password.isEmpty();
    }

    static void showToast(Context context, String message) {

        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView toastTV = view.findViewById(R.id.toastTV);
        toastTV.setText(message);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP | Gravity.END, 16, 16);
        toast.setView(view);
        toast.show();
    }

    public static void log(String msg) {
        Log.d("Helper", msg);
    }

}

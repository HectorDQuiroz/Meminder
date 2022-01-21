package com.example.meminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.Objects;

public class sesion_Iniciada extends AppCompatActivity {

    //Firebase
    private FirebaseAuth mAuth;
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button Cuenta;
    private EditText txt_user;
    private EditText txt_pass;
    private EditText txt_passC;
    private EditText txt_mail;
    private String user_Name, user_Pass, user_PassC, user_Mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Firebase
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sesion__iniciada);
        getSupportActionBar().hide();
        String titulo = getString(R.string.titulo1);
        Objects.requireNonNull(getSupportActionBar()).setTitle(titulo);
        //Asignacion de botones
        txt_user = (EditText) findViewById(R.id.txt_user);
        txt_pass = (EditText) findViewById(R.id.txt_pass);
        txt_passC = (EditText) findViewById(R.id.txt_confirmpass);
        txt_mail = (EditText) findViewById(R.id.txt_mail);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void onCLick(View view) {
        if (view.getId() == R.id.btn_inicio) {
            back_Inicio();
        } else if (view.getId() == R.id.btn_Registrar) {
            validar();
        }
    }

    public void validar() {
        user_Name = txt_user.getText().toString().trim();
        user_Pass = txt_pass.getText().toString().trim();
        user_PassC = txt_passC.getText().toString().trim();
        user_Mail = txt_mail.getText().toString().trim();

        if (TextUtils.isEmpty(user_Name)) {
            txt_user.setError(getString(R.string.verficicacion));
            txt_user.requestFocus();
        } else if (TextUtils.isEmpty(user_Pass)) {
            txt_pass.setError(getString(R.string.verficicacion));
            txt_pass.requestFocus();
        } else if (TextUtils.isEmpty(user_PassC)) {
            txt_passC.setError(getString(R.string.verficicacion));
            txt_passC.requestFocus();
        } else if (TextUtils.isEmpty(user_Mail)) {
            txt_mail.setError(getString(R.string.verficicacion));
            txt_mail.requestFocus();
        } else {
            registrar_User();
        }
    }

    public void back_Inicio() {
        Intent Inicio = new Intent(this, MainActivity.class);
        startActivity(Inicio);
    }

    //Temporal antes de base datos
    public void registrar_User() {
        Intent registro = new Intent(this, MainActivity.class);

        String usuario = txt_mail.getText().toString();
        String pass = txt_pass.getText().toString();
//        SharedPreferences preferences = getSharedPreferences(("credenciales"), Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("user", usuario);
//        editor.putString("pass", pass);
//
//        editor.apply();

        mAuth.createUserWithEmailAndPassword(usuario, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(registro);
                        } else {
                            // If sign in fails, display a message to the user.
                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                            dameToastdeerror(errorCode);
                        }

                        // ...
                    }
                });

    }

    private void dameToastdeerror(String error) {

        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(sesion_Iniciada.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(sesion_Iniciada.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(sesion_Iniciada.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                Toast.makeText(sesion_Iniciada.this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_LONG).show();
                txt_mail.setError("La dirección de correo electrónico está mal formateada.");
                txt_mail.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(sesion_Iniciada.this, "La contraseña no es válida o el usuario no tiene contraseña.", Toast.LENGTH_LONG).show();
                txt_pass.setError("la contraseña es incorrecta ");
                txt_pass.requestFocus();
                txt_pass.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(sesion_Iniciada.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(sesion_Iniciada.this, "Esta operación es sensible y requiere autenticación reciente. Inicie sesión nuevamente antes de volver a intentar esta solicitud.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(sesion_Iniciada.this, "Ya existe una cuenta con la misma dirección de correo electrónico pero diferentes credenciales de inicio de sesión. Inicie sesión con un proveedor asociado a esta dirección de correo electrónico.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(sesion_Iniciada.this, "La dirección de correo electrónico ya está siendo utilizada por otra cuenta..   ", Toast.LENGTH_LONG).show();
                txt_mail.setError("La dirección de correo electrónico ya está siendo utilizada por otra cuenta.");
                txt_mail.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(sesion_Iniciada.this, "Esta credencial ya está asociada con una cuenta de usuario diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(sesion_Iniciada.this, "La cuenta de usuario ha sido inhabilitada por un administrador..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(sesion_Iniciada.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(sesion_Iniciada.this, "No hay ningún registro de usuario que corresponda a este identificador. Es posible que se haya eliminado al usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(sesion_Iniciada.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(sesion_Iniciada.this, "Esta operación no está permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(sesion_Iniciada.this, "La contraseña proporcionada no es válida..", Toast.LENGTH_LONG).show();
                txt_pass.setError("La contraseña no es válida, debe tener al menos 6 caracteres");
                txt_pass.requestFocus();
                break;

        }

    }


}
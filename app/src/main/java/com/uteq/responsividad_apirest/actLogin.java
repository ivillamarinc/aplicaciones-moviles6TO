package com.uteq.responsividad_apirest;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.uteq.responsividad_apirest.WebServices.Asynchtask;
import com.uteq.responsividad_apirest.WebServices.WebService;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class actLogin extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_act_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btLogin(View v) {
        TextInputLayout ilUsuario = findViewById(R.id.textInputLayout);
        TextInputEditText edtUsr = findViewById(R.id.tilUsuario);

        if (edtUsr.getText().toString().isEmpty()) {
            ilUsuario.setError("Nombre requerido");
        } else {
            ilUsuario.setErrorEnabled(false);
        }



        //LLAMAR API RESTFUL


        String url = "https://revistas.uteq.edu.ec/ws/login.php?usr=" +
                edtUsr.getText().toString()
                + "&pass=" + edtUsr.getText().toString();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService(url,
                datos, actLogin.this, actLogin.this);

        ws.execute("GET");

    }
// PRIMER PASOOO
    @Override
    public void processFinish(String result) throws JSONException
    {
      Toast.makeText(this,result,
              Toast.LENGTH_LONG).show();
    }
}

package com.example.appquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UsuarioActivity1 extends AppCompatActivity {
    private EditText edtNomeUsuario;
    private Button buttonOk;
    private DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario1);

        doInitialize();
    }

    public void doInitialize() {
        edtNomeUsuario = findViewById(R.id.edtNomeUsuario);
        buttonOk = findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario.getINSTANCE().setNome(edtNomeUsuario.getText().toString());
                db.addUsuario();

                onBackPressed();
            }
        });

    }
}

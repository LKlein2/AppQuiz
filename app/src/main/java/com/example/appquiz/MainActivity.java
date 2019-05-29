package com.example.appquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageLogo;
    private TextView txtNomeUsuario;
    private Button buttonRanking, buttonContinuar;

    private DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doInitialize();
        db.retUsuario();

        if (Usuario.getINSTANCE().getNome() == "") {
            openUsuario();
        }

    }

    public void doInitialize() {
        imageLogo = findViewById(R.id.imageLogo);
        txtNomeUsuario = findViewById(R.id.txtNomeUsuario);
        buttonRanking  = findViewById(R.id.buttonRanking);
        buttonContinuar = findViewById(R.id.buttonContinuar);

        imageLogo.setImageResource(R.drawable.quiz);
    }

    public void openUsuario() {
        Intent activityUsuario = new Intent(MainActivity.this, UsuarioActivity1.class);
        startActivity(activityUsuario);
    }
}
package com.example.appquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;

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
            buttonContinuar.setText(R.string.novo_jogo);
        }

        db.testaPerguntas(this);
    }

    public void doInitialize() {
        imageLogo       = findViewById(R.id.imageLogo);
        txtNomeUsuario  = findViewById(R.id.txtNomeUsuario);
        buttonRanking   = findViewById(R.id.buttonRanking);
        buttonContinuar = findViewById(R.id.buttonContinuar);

        imageLogo.setImageResource(R.drawable.quiz);

        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPerg();
            }
        });

        buttonRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sua pontuação é: " + Usuario.u_pontuacao,Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void openUsuario() {
        Intent activityUsuario = new Intent(MainActivity.this, UsuarioActivity1.class);
        startActivity(activityUsuario);
    }

    public void openPerg() {
        Intent pergunta = new Intent(MainActivity.this, activity_perg.class);
        startActivity(pergunta);
    }




}
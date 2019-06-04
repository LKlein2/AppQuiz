package com.example.appquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_perg extends AppCompatActivity {


    private TextView textPerg;
    private Button btnR1, btnR2, btnR3, btnSair, btnPular;


    private DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perg);

        doInitialize();
    }

    public void proxFase(int atual) {



    }


    public String[][] respostas;
    public void doInitialize() {
        textPerg = findViewById(R.id.textPerg);
        btnR1 = findViewById(R.id.btnR1);
        btnR2 = findViewById(R.id.btnR2);
        btnR3 = findViewById(R.id.btnR3);
        btnSair = findViewById(R.id.btnSair);
        btnPular = findViewById(R.id.btnPular);

        textPerg.setText(db.retPergunta(Usuario.u_fase + 1));
        respostas = db.retResposta(Usuario.u_fase + 1);
        btnR1.setText(respostas[0][1]);
        btnR2.setText(respostas[1][1]);
        btnR3.setText(respostas[2][1]);

        btnR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resp = 0;

                if (respostas[0][1] == "1") {
                    proxFase(Usuario.u_fase);
                } else {
                    proxFase(Usuario.u_fase);
                }
            }
        });

        btnR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resp = 0;

                if (respostas[1][1] == "1") {
                    proxFase(Usuario.u_fase);
                } else {
                    proxFase(Usuario.u_fase);
                }
            }
        });

        btnR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resp = 0;

                if (respostas[2][1] == "1") {
                    proxFase(Usuario.u_fase);
                } else {
                    proxFase(Usuario.u_fase);
                }
            }

        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        btnPular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

}

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

        proxFase();
    }

    public void proxFase() {
        textPerg.setText(db.retPergunta(Usuario.u_fase + 1));

        respostas = db.retResposta(Usuario.u_fase + 1);
        btnR1.setText(respostas[0][0]);
        btnR2.setText(respostas[1][0]);
        btnR3.setText(respostas[2][0]);
    }

    public String[][] respostas;
    public void doInitialize() {
        textPerg = findViewById(R.id.textPerg);
        btnR1 = findViewById(R.id.btnR1);
        btnR2 = findViewById(R.id.btnR2);
        btnR3 = findViewById(R.id.btnR3);

        btnR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (respostas[0][1].equals("1")) {
                    Usuario.u_fase++;
                    proxFase();
                } else {
                    proxFase();
                }
            }
        });

        btnR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (respostas[1][1].equals("1")) {
                    Usuario.u_fase++;
                    proxFase();
                } else {
                    proxFase();
                }
            }
        });

        btnR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (respostas[2][1].equals("1")) {
                    Usuario.u_fase++;
                    proxFase();
                } else {
                    proxFase();
                }
            }
        });
    }

}

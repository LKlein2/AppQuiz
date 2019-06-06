package com.example.appquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

public class activity_perg extends AppCompatActivity {

    private TextView textPerg;
    private Button btnR1, btnR2, btnR3, btnSair, btnPular;
    private Handler handler;

    private DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perg);
        handler = new Handler();
        doInitialize();

        proxFase();
    }

    public void proxFase() {
        textPerg.setText(db.retPergunta(Usuario.u_fase + 1));

        respostas = db.retResposta(Usuario.u_fase + 1);
        btnR1.setText(respostas[0][0]);
        btnR2.setText(respostas[1][0]);
        btnR3.setText(respostas[2][0]);

        btnR1.setBackgroundResource(R.color.colorAccent);
        btnR2.setBackgroundResource(R.color.colorPrimaryDark);
        btnR3.setBackgroundResource(R.color.colorAccent);
    }

    public void verificaResposta(final int i) {
        final Button Aux;
        if (i == 0) Aux = btnR1;
        else if (i == 1) Aux = btnR2;
        else Aux = btnR3;

        Usuario.u_fase++;
        if (respostas[i][1].equals("1")) {
            Aux.setBackgroundColor(Color.GREEN);
            db.aumentaPonto();
            db.incrementaFase();

        } else {
            Aux.setBackgroundColor(Color.RED);
            db.incrementaFase();
        }

        new Thread() {
            public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                public void run() {
                    proxFase();
                }
            });

            }
        }.start();
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
                verificaResposta(0);
            }
        });

        btnR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificaResposta(1);
            }
        });

        btnR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificaResposta(2);
            }
        });
    }

}

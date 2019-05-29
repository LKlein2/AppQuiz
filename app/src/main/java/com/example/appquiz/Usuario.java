package com.example.appquiz;

public final class Usuario {
    private static Usuario INSTANCE = null;
    private static String u_nome;
    public static int u_fase, u_pontuacao;

    private Usuario () {
        u_nome = "";
    }

    public static Usuario getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Usuario();
        }
        return INSTANCE;
    }

    public void setNome(String nome) {
        if (u_nome == "") {
            u_nome = nome;
        }
    }

    public String getNome () {
        return u_nome;
    }


}

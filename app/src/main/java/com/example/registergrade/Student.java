package com.example.registergrade;

public class Student {
    private int id;
    private String nome;
    private float nota1;
    private float nota2;
    private float media;

    //recebe todos os valores como parâmetros
    public Student(int id, String nome, float nota1, float nota2, float media) {
        this.id = id;
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.media = media;
    }



//acessar o valor de cada atributo
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getNota1() {
        return nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public float getMedia() {
        return media;
    }
}

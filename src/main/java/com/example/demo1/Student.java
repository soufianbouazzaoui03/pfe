package com.example.demo1;

public class Student {
    private int num;
    private String CNE;
    private String nomEtPrenom;

    public int getNum() {
        return num;
    }

    public String getCNE() {
        return CNE;
    }

    public String getNomEtPrenom() {
        return nomEtPrenom;
    }

    public Student(int num, String CNE, String nomEtPrenom) {
        this.num = num;
        this.CNE = CNE;
        this.nomEtPrenom = nomEtPrenom;
    }

}

package com.example.mytasks;

public class MyTasks {

    String titledoes, datadoes, descdoes;

    public MyTasks() {
    }

    public MyTasks(String titledoes, String datadoes, String descdoes) {
        this.titledoes = titledoes;
        this.datadoes = datadoes;
        this.descdoes = descdoes;
    }

    public String getTitledoes() {
        return titledoes;
    }

    public void setTitledoes(String titledoes) {
        this.titledoes = titledoes;
    }

    public String getDatadoes() {
        return datadoes;
    }

    public void setDatadoes(String datadoes) {
        this.datadoes = datadoes;
    }

    public String getDescdoes() {
        return descdoes;
    }

    public void setDescdoes(String descdoes) {
        this.descdoes = descdoes;
    }
}

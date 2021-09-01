package com.gsiza.crudex;

public class Veio {
    int id;
    public int idade;
    public String telefone;
    public String nome;
    public String sobrenome;

    public Veio(){

    }

    public Veio(int id, int idade, String telefone, String nome, String sobrenome){
        this.id = id;
        this.idade = idade;
        this.telefone = telefone;
        this.nome = nome;
        this.sobrenome= sobrenome;
    }

    public Veio(int idade, String telefone, String nome, String sobrenome){
        this.idade = idade;
        this.telefone = telefone;
        this.nome = nome;
        this.sobrenome= sobrenome;
    }

    //-------------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}

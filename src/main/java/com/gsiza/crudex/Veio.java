package com.gsiza.crudex;

public class Veio {
    int id;
    public int idade;
    public int telefone;
    public String nome;
    public String sobrenome;

    public Veio(){

    }

    public Veio(int id, int idade, int telefone, String nome, String sobrenome){
        this.id = id;
        this.idade = idade;
        this.telefone = telefone;
        this.nome = nome;
        this.sobrenome= sobrenome;
    }

    public Veio(int idade, int telefone, String nome, String sobrenome){
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

    public void setIdade(String idade) {
        this.idade = Integer.parseInt(String.valueOf(idade));
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
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

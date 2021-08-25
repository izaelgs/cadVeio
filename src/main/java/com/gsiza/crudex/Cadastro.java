package com.gsiza.crudex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {

    private EditText nome;
    private EditText sobrenome;
    private EditText idade;

    DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        nome = findViewById(R.id.edtNome);
        sobrenome = findViewById(R.id.edtSNome);
        idade = findViewById(R.id.edtIdade);

        /* TESTE DO CRUD*/

        db.cadVeio(new Veio(67, 279976546, "JORTINELSON","STORAXOTA"));
        db.cadVeio(new Veio(27, 279961546, "TESTE","ADA"));
        db.cadVeio(new Veio(51, 219976546, "VBUSETA","FSAFW"));
/*
        Veio veio = new Veio();
        veio.setId(1);
        db.selectVeio(veio);

        Log.d("Cliente Selecionado", "Id" + veio.getId() + "Nome: " + veio.getNome() +
                "Sobrenome: " +veio.getSobrenome() + "Idade: " + veio.getIdade() + "Telefone: " + veio.getTelefone());*/
    }

    public void cadVeio(View view){
        Veio v = new Veio();
        v.setNome(nome.getText().toString());
        v.setSobrenome(sobrenome.getText().toString());
        v.setIdade(idade.getText().toString());
    }
}
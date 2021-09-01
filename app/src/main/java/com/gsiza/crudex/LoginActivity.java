package com.gsiza.crudex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText senha;
    private Button entrar;

    DataBase db = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intentIntentLogin = new Intent(this, MeuDia.class);

        user = findViewById(R.id.edtLogin);
        senha = findViewById(R.id.edtSenha);

        entrar = findViewById(R.id.btnTry);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String users = user.getText().toString();
                String senhas = senha.getText().toString();

                Veio veio = new Veio();
                veio.setNome(users);
                Log.d("string", "onClick: " + veio.nome);
                veio.setSobrenome(senhas);
                Log.d("string", "onClick: " + veio.sobrenome);

                Veio v = (Veio) db.verificaVeio(veio);

                if (v != null){
                    if (v.getNome().equals(veio.getNome())){
                    Toast.makeText(LoginActivity.this, "Login sucedido mano", Toast.LENGTH_SHORT).show();
                        startActivity(intentIntentLogin);
                    }else{}
                }else if (users.length() > 0 && senhas.length() > 0){
                    Toast.makeText(LoginActivity.this, "Nome de usuário ou Senha tá errado mano da uma olhada ae fml",
                            Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(LoginActivity.this, "escreve alguma coisa ae fml", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
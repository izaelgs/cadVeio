package com.gsiza.crudex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

    }

    public void IntentCadastro(View view){
        Intent intentCad = new Intent(this, CadastroActivity.class);
        startActivity(intentCad);
    }

    public void intentLogin(View view){
        Intent intentIntentLogin = new Intent(this, LoginActivity.class);
        startActivity(intentIntentLogin);
    }
}
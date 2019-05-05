package br.com.digitalhouse.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.digitalhouse.R;

public class IntroducaoActivity extends AppCompatActivity {
    private Button btnComecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducao);

        //VAMOS COLOCAR A AÇÃO DO BOTÃO

        //btnComecar = findViewById(R.id.btnComecar);

//        btnComecar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(IntroducaoActivity.this, HomeActivity.class));
//            }
//        });
    }
}

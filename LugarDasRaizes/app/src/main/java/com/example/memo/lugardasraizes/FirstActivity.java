package com.example.memo.lugardasraizes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstActivity extends Activity implements View.OnClickListener {
    private Button btnOk;
    private EditText numerador;
    private EditText denominador;
    private final String TAG = "FuncaoTransferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        btnOk = findViewById(R.id.ok);
        btnOk.setOnClickListener(this);

        numerador = findViewById(R.id.numerador);
        denominador = findViewById(R.id.denominador);

    }

    @Override
    public void onClick(View view) {


        Toast.makeText(getApplicationContext(),"Clicou", Toast.LENGTH_SHORT).show();

        Log.i(TAG, numerador.getText().toString());
        Log.i(TAG, denominador.getText().toString());

        //Intent i= new Intent(this,MainActivity.class);
        // Bundle b = new Bundle();
        // b.putString("nomeJogador", ft.getText().toString());
        // i.putExtras(b);
        //startActivity(i);
    }
}

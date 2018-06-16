package com.example.memo.lugardasraizes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends Activity implements View.OnClickListener {
    private Button btnOk;
    private EditText ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);



        btnOk = (Button) findViewById(R.id.ok);
        btnOk.setOnClickListener(this);

        ft = (EditText) findViewById(R.id.ft);
    }

    @Override
    public void onClick(View view) {
        Intent i= new Intent(this,MainActivity.class);
        Bundle b = new Bundle();
        b.putString("nomeJogador", ft.getText().toString());
        i.putExtras(b);
        startActivity(i);
    }
}

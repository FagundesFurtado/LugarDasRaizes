package com.example.memo.lugardasraizes.Main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.BottomNavigationView;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.SuperscriptSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.memo.lugardasraizes.Adapter.MyTabsListener;
import com.example.memo.lugardasraizes.Adapter.RespostaGrafico;
import com.example.memo.lugardasraizes.Adapter.TabsAdapter;
import com.example.memo.lugardasraizes.Dados.Grafico;
import com.example.memo.lugardasraizes.Dados.ResolveRaizes;
import com.example.memo.lugardasraizes.Fragment.GraficoFragment;
import com.example.memo.lugardasraizes.Model.Configuracao;
import com.example.memo.lugardasraizes.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    private TextView numeradorExemplo;
    private TextView denominadorExemplo;
    private EditText numerador;
    private EditText denominador;
    private Button ok;
    private Button limpar;
    private EditText intervalo;
    private EditText passo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numeradorExemplo = findViewById(R.id.txtNumerador);
        denominadorExemplo = findViewById(R.id.txtDenominador);

        numerador = findViewById(R.id.numerador);
        denominador = findViewById(R.id.denominador);
        intervalo = findViewById(R.id.intervalo);
        passo = findViewById(R.id.passo);
        intervalo.setText("15");
        passo.setText("0.1");
        ok = findViewById(R.id.btnOk);
        limpar = findViewById(R.id.btnLimpar);

        ok.setOnClickListener(this);
        limpar.setOnClickListener(this);

        numerador.setOnKeyListener(this);
        denominador.setOnKeyListener(this);


    }


    @Override
    public void onClick(View v) {

        if (v == ok) {
            Intent i = new Intent(this, GraficoActivity.class);
            i.putExtra("Numerador", numerador.getText().toString());
            i.putExtra("Denominador", denominador.getText().toString());
            Configuracao.getInstance().setIntervalo(Double.parseDouble(intervalo.getText().toString()));
            Configuracao.getInstance().setPasso(Double.parseDouble(passo.getText().toString()));

            startActivity(i);

        }
        if (v == limpar) {
            numerador.setText("");
            denominador.setText("");
            intervalo.setText("15");
            passo.setText("0.1");
            numeradorExemplo.setText("");
            denominadorExemplo.setText("");
        }


    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {


        String n = numerador.getText().toString();

        String d = denominador.getText().toString();
        if (!n.equals("")) {
            numeradorExemplo.setText(trataValores(n));
        }

        if (!d.equals("")) {
            denominadorExemplo.setText(trataValores(d));
        }


        return false;
    }

    private String trataValores(String equacao) {
        String split[] = equacao.split(" ");

        String saida = "";

        for (int i = 0; i < split.length; i++) {
            int valor = Integer.parseInt(split[i]);
            int intGrau = split.length - 1 - i;
            if (i != 0 && valor > 0) {
                saida += " + ";
            }
            if (valor < 0) {
                saida += " - ";

            }

            if (valor != 0) {
                if (valor != 1 && valor != -1 || intGrau == 0) {
                    saida += split[i];
                }

                if (intGrau != 0) {
                    saida += "S";
                    if (intGrau > 1)
                        saida += superscript(intGrau) + " ";
                }
            }
        }

        return saida;
    }

    private static String superscript(int valor) {

        switch (valor) {
            case 1:
                return "¹";
            case 2:
                return "²";
            case 3:
                return "³";
            case 4:
                return "⁴";
            case 5:
                return "⁵";
            case 6:
                return "⁶";
            case 7:
                return "⁷";
            case 8:
                return "⁸";
            case 9:
                return "⁹";
        }
        return "";
    }


}

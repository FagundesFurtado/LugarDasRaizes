package com.example.memo.lugardasraizes.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.memo.lugardasraizes.Dados.Grafico;
import com.example.memo.lugardasraizes.Dados.ResolveRaizes;
import com.example.memo.lugardasraizes.Dados.Termo;
import com.example.memo.lugardasraizes.Main.MainActivity;
import com.example.memo.lugardasraizes.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;


public class Inicio extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private final String TAG = "Grafico";
    private String mParam1;
    private String mParam2;
    private Button btnOk;
    private EditText numerador;
    private EditText denominador;


    private Grafico grafico = Grafico.getInstance();

    private OnFragmentInteractionListener mListener;

    public Inicio() {

    }


    public static Inicio newInstance(String param1, String param2) {
        Inicio fragment = new Inicio();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_inicio, container, false);

        numerador = v.findViewById(R.id.numerador);
        denominador = v.findViewById(R.id.denominador);

        btnOk = v.findViewById(R.id.btnOk);

        btnOk.setOnClickListener(this);


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if (v == btnOk) {
            if ((!numerador.equals("")) && (!denominador.equals(""))) {
                String[] f = new String[2];
                f[0] = numerador.getText().toString();
                f[1] = denominador.getText().toString();
                this.trataString(f[1]);


                grafico.setEquacao(numerador.toString(), denominador.toString());
                ((MainActivity) getActivity()).graficoFragment();
                Log.i(TAG, "Clicou no botao");


            }

        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void trataString(String funcao) {
        //3s^3+2s^2+s^1+4
        String poli = "1x^3+3x^2+2x^1";

        Pattern.compile("(?=[+-])").split(poli);

        Pattern pegapoli = Pattern.compile("(?=[+-])");
        String[] m = pegapoli.split(poli);
        ArrayList<Termo> termos = new ArrayList<>();
        for (String i : m) {
            Log.i("Poli", i);
            addTermo(termos, i);
        }
        Collections.sort(
                termos, new Comparator<Termo>() {
                    @Override
                    public int compare(Termo o1, Termo o2) {
                        return o2.getGrau().compareTo(o1.getGrau());
                    }
                }
        );


        for(Termo q : termos)
            Log.i("Vetor termos ", "Termo "+q.toString());
        Integer[] termosParaFuncao = new Integer[termos.get(0).getGrau()+1];
        for(int j =0; j<termosParaFuncao.length;j++)
            termosParaFuncao[j] = 0;
        for(int i =0;i<termos.size();i++)
        {
            Log.i("vetorFinal", "Posicao "+termos.get(i).getGrau()+"\t"+termos.get(i).getCoeficiente() );
          termosParaFuncao[termos.get(i).getGrau()] =  termos.get(i).getCoeficiente();
        }
        for(int p = 0;p<termosParaFuncao.length;p++)
        {
            Log.i("peteca", "Valor "+termosParaFuncao[p]+"\t posicao "+p);
        }

    }

    public void addTermo(ArrayList<Termo> lista, String termo) {
        termo = termo.replace("^", "");
        String[] funcao = termo.split("x");

        for (String l : funcao)
            Log.i("Funcao ", l);
        if (funcao.length == 2) {

            lista.add(new Termo(Integer.parseInt(funcao[0]), Integer.parseInt(funcao[1])));

        }
        if(funcao.length == 1)
        {
            try {
                lista.add(new Termo(Integer.parseInt(funcao[0]), 0));
            }
            catch (Exception e )
            {
                e.printStackTrace();
            }

        }
    }

}

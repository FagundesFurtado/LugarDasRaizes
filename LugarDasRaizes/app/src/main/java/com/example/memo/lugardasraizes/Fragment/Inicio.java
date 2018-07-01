package com.example.memo.lugardasraizes.Fragment;

import android.content.Context;
import android.content.Intent;
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
    private Button btnLimpar;
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
        btnLimpar = v.findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(this);

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
                grafico.setEquacao(f[0].toString(), f[1].toString());
                //((MainActivity) getActivity()).graficoFragment();
                Log.i(TAG, "Clicou no botao");
            }
        }
        if(v == btnLimpar){
            numerador.setText("");
            denominador.setText("");
        }

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }



}
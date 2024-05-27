package br.edu.fateczl.competicaonatacao;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.fateczl.competicaonatacao.model.AtletaSenior;

public class AtletaSeniorFragment extends Fragment {

    private View view;
    private EditText etNomeSenior;
    private EditText etDataNascSenior;
    private EditText etBairroSenior;
    private CheckBox cbProblemasCardiacosSenior;
    private Button btnCadastrarSenior;

    public AtletaSeniorFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_atleta_senior, container, false);
        etNomeSenior = view.findViewById(R.id.etNomeSenior);
        etDataNascSenior = view.findViewById(R.id.etDataNascSenior);
        etBairroSenior = view.findViewById(R.id.etBairroSenior);
        cbProblemasCardiacosSenior = view.findViewById(R.id.cbProblemasCardiacosSenior);
        cbProblemasCardiacosSenior.setChecked(false);
        btnCadastrarSenior = view.findViewById(R.id.btnCadastrarSenior);

        btnCadastrarSenior.setOnClickListener(op -> cadastro());
        return view;
    }

    private void cadastro() {
        AtletaSenior senior = new AtletaSenior();
        senior.setNome(etNomeSenior.getText().toString());
        senior.setDataNasc(etDataNascSenior.getText().toString());
        senior.setBairro(etBairroSenior.getText().toString());
        if(cbProblemasCardiacosSenior.isChecked()){
            senior.setProblemasCardiacos(true);
        } else {
            senior.setProblemasCardiacos(false);
        }

        Toast.makeText(view.getContext(), senior.toString(), Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void limpaCampos() {
        etNomeSenior.setText("");
        etDataNascSenior.setText("");
        etBairroSenior.setText("");
        cbProblemasCardiacosSenior.setChecked(false);
    }
}
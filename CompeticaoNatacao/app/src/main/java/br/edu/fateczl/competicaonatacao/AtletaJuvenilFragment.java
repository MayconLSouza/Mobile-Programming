package br.edu.fateczl.competicaonatacao;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.edu.fateczl.competicaonatacao.model.AtletaJuvenil;

public class AtletaJuvenilFragment extends Fragment {

    private View view;
    private EditText etNomeJuvenil;
    private EditText etDataNascJuvenil;
    private EditText etBairroJuvenil;
    private EditText etAnosPraticaJuvenil;
    private Button btnCadastrarJuvenil;


    public AtletaJuvenilFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_atleta_juvenil, container, false);
        etNomeJuvenil = view.findViewById(R.id.etNomeJuvenil);
        etDataNascJuvenil = view.findViewById(R.id.etDataNascJuvenil);
        etBairroJuvenil = view.findViewById(R.id.etBairroJuvenil);
        etAnosPraticaJuvenil = view.findViewById(R.id.etAnosPraticaJuvenil);
        btnCadastrarJuvenil = view.findViewById(R.id.btnCadastrarJuvenil);

        btnCadastrarJuvenil.setOnClickListener(op -> cadastro());
        return view;
    }

    private void cadastro() {
        AtletaJuvenil juvenil = new AtletaJuvenil();
        juvenil.setNome(etNomeJuvenil.getText().toString());
        juvenil.setDataNasc(etDataNascJuvenil.getText().toString());
        juvenil.setBairro(etBairroJuvenil.getText().toString());
        juvenil.setAnosPratica(Integer.parseInt(etAnosPraticaJuvenil.getText().toString()));

        Toast.makeText(view.getContext(), juvenil.toString(), Toast.LENGTH_LONG).show();
        limpaCampos();

    }

    private void limpaCampos() {
        etNomeJuvenil.setText("");
        etDataNascJuvenil.setText("");
        etBairroJuvenil.setText("");
        etAnosPraticaJuvenil.setText("");
    }
}
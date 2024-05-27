package br.edu.fateczl.competicaonatacao;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.edu.fateczl.competicaonatacao.model.AtletaPleno;

public class AtletaPlenoFragment extends Fragment {

    private View view;
    private EditText etNomePleno;
    private EditText etDataNascPleno;
    private EditText etBairroPleno;
    private EditText etAcademiaPleno;
    private EditText etRecordePleno;
    private Button btnCadastrarPleno;

    public AtletaPlenoFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_atleta_pleno, container, false);
        etNomePleno = view.findViewById(R.id.etNomePleno);
        etDataNascPleno = view.findViewById(R.id.etDataNascPleno);
        etBairroPleno = view.findViewById(R.id.etBairroPleno);
        etAcademiaPleno = view.findViewById(R.id.etAcademiaPleno);
        etRecordePleno = view.findViewById(R.id.etRecordePleno);
        btnCadastrarPleno = view.findViewById(R.id.btnCadastrarPleno);

        btnCadastrarPleno.setOnClickListener(op -> cadastro());
        return view;
    }

    private void cadastro() {
        AtletaPleno pleno = new AtletaPleno();
        pleno.setNome(etNomePleno.getText().toString());
        pleno.setDataNasc(etDataNascPleno.getText().toString());
        pleno.setBairro(etBairroPleno.getText().toString());
        pleno.setAcademia(etAcademiaPleno.getText().toString());
        pleno.setRecorde(Integer.parseInt(etRecordePleno.getText().toString()));

        Toast.makeText(view.getContext(), pleno.toString(), Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void limpaCampos() {
        etNomePleno.setText("");
        etDataNascPleno.setText("");
        etBairroPleno.setText("");
        etAcademiaPleno.setText("");
        etRecordePleno.setText("");
    }
}
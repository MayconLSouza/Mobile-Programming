package br.edu.fateczl.exemplofragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.edu.fateczl.exemplofragments.controller.IOperacao;
import br.edu.fateczl.exemplofragments.controller.OperacaoPF;
import br.edu.fateczl.exemplofragments.controller.OperacaoPJ;
import br.edu.fateczl.exemplofragments.model.PessoaFisica;
import br.edu.fateczl.exemplofragments.model.PessoaJuridica;

public class PessoaJuridicaFragment extends Fragment {

    private View view;
    private EditText etCnpjPJ;
    private EditText etNomePJ;
    private EditText etEmailPJ;
    private Button btnCadastrarPJ;
    private TextView tvListaPJ;

    public PessoaJuridicaFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_pessoa_juridica, container, false);
        etCnpjPJ = view.findViewById(R.id.etCnpjPJ);
        etNomePJ = view.findViewById(R.id.etNomePJ);
        etEmailPJ = view.findViewById(R.id.etEmailPJ);
        btnCadastrarPJ = view.findViewById(R.id.btnCadastrarPJ);
        tvListaPJ =view.findViewById(R.id.tvListaPJ);
        tvListaPJ.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnCadastrarPJ.setOnClickListener(op -> cadastro());

        return view;
    }

    private void cadastro() {
        PessoaJuridica pj = new PessoaJuridica();
        pj.setCnpj(etCnpjPJ.getText().toString());
        pj.setNome(etNomePJ.getText().toString());
        pj.setEmail(etEmailPJ.getText().toString());

        IOperacao<PessoaJuridica> op = new OperacaoPJ();
        op.cadastrar(pj);
        List<PessoaJuridica> lista = op.listar();

        StringBuffer buffer = new StringBuffer();
        for(PessoaJuridica p : lista){
            buffer.append(p.toString() + "\n");
        }
        tvListaPJ.setText(buffer.toString());
        limpaCampos();
    }

    private void limpaCampos() {
        etCnpjPJ.setText("");
        etNomePJ.setText("");
        etEmailPJ.setText("");
    }
}
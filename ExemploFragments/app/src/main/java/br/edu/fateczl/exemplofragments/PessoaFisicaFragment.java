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
import br.edu.fateczl.exemplofragments.model.PessoaFisica;

public class PessoaFisicaFragment extends Fragment {

    private View view;
    private EditText etCpfPF;
    private EditText etNomePF;
    private EditText etTelefonePF;
    private Button btnCadastrarPF;
    private TextView tvListaPF;

    public PessoaFisicaFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_pessoa_fisica, container, false);
        etCpfPF = view.findViewById(R.id.etCpfPF);
        etNomePF = view.findViewById(R.id.etNomePF);
        etTelefonePF = view.findViewById(R.id.etTelefonePF);
        btnCadastrarPF = view.findViewById(R.id.btnCadastrarPF);
        tvListaPF =view.findViewById(R.id.tvListaPF);
        tvListaPF.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnCadastrarPF.setOnClickListener(op -> cadastro());

        return view;
    }

    private void cadastro() {
        PessoaFisica pf = new PessoaFisica();
        pf.setCpf(etCpfPF.getText().toString());
        pf.setNome(etNomePF.getText().toString());
        pf.setTelefone(etTelefonePF.getText().toString());

        IOperacao<PessoaFisica> op = new OperacaoPF();
        op.cadastrar(pf);
        List<PessoaFisica> lista = op.listar();

        StringBuffer buffer = new StringBuffer();
        for(PessoaFisica p : lista){
            buffer.append(p.toString() + "\n");
        }
        tvListaPF.setText(buffer.toString());
        limpaCampos();
    }

    private void limpaCampos() {
        etCpfPF.setText("");
        etNomePF.setText("");
        etTelefonePF.setText("");
    }
}
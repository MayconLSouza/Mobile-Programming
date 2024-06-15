package br.edu.fateczl.locadoravideogame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.locadoravideogame.controller.JogoController;
import br.edu.fateczl.locadoravideogame.model.Jogo;
import br.edu.fateczl.locadoravideogame.persistence.JogoDao;

public class JogoFragment extends Fragment {

    private View view;
    private EditText etCodigoJogo, etNomeJogo, etPrecoJogo, etDesenvolvedoraJogo, etIdadeJogo;
    private Button btnBuscarJogo, btnInserirJogo, btnModificarJogo, btnExcluirJogo, btnListarJogo;
    private TextView tvListarJogo;
    private JogoController jCont;


    public JogoFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jogo, container, false);
        etCodigoJogo = view.findViewById(R.id.etCodigoJogo);
        etNomeJogo = view.findViewById(R.id.etNomeJogo);
        etPrecoJogo = view.findViewById(R.id.etPrecoJogo);
        etDesenvolvedoraJogo = view.findViewById(R.id.etDesenvolvedoraJogo);
        etIdadeJogo = view.findViewById(R.id.etIdadeJogo);
        btnBuscarJogo = view.findViewById(R.id.btnBuscarJogo);
        btnInserirJogo = view.findViewById(R.id.btnInserirJogo);
        btnModificarJogo = view.findViewById(R.id.btnModificarJogo);
        btnExcluirJogo = view.findViewById(R.id.btnExcluirJogo);
        btnListarJogo = view.findViewById(R.id.btnListarJogo);
        tvListarJogo = view.findViewById(R.id.tvListarJogo);

        jCont = new JogoController(new JogoDao(view.getContext()));

        btnInserirJogo.setOnClickListener(op -> acaoInserir());
        btnModificarJogo.setOnClickListener(op -> acaoModificar());
        btnExcluirJogo.setOnClickListener(op -> acaoExcluir());
        btnBuscarJogo.setOnClickListener(op -> acaoBuscar());
        btnListarJogo.setOnClickListener(op -> acaoListar());

        return view;
    }

    private void acaoInserir() {
        Jogo jogo = montaJogo();
        try {
            jCont.inserir(jogo);
            Toast.makeText(view.getContext(), "Jogo inserido com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoModificar() {
        Jogo jogo = montaJogo();
        try {
            jCont.modificar(jogo);
            Toast.makeText(view.getContext(), "Jogo atualizado com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoExcluir() {
        Jogo jogo = montaJogo();
        try {
            jCont.deletar(jogo);
            Toast.makeText(view.getContext(), "Jogo excluído com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoBuscar() {
        Jogo jogo = montaJogo();
        try {
            jogo = jCont.buscar(jogo);
            if(jogo.getNome() != null){
                preencheCampos(jogo);
            } else {
                Toast.makeText(view.getContext(), "Jogo não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void acaoListar() {
        try {
            List<Jogo> jogos = jCont.listar();
            StringBuffer buffer = new StringBuffer();
            for (Jogo j : jogos){
                buffer.append(j.toString() + "\n");
            }
            tvListarJogo.setText(buffer.toString());
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Jogo montaJogo(){
        Jogo jogo = new Jogo();
        jogo.setCodigo(Integer.parseInt(etCodigoJogo.getText().toString()));
        jogo.setNome(etNomeJogo.getText().toString());
        jogo.setDesenvolvedora(etDesenvolvedoraJogo.getText().toString());
        if(!etIdadeJogo.getText().toString().isEmpty()){
            jogo.setIdadeRecomendada(Integer.parseInt(etIdadeJogo.getText().toString()));
        }
        if(!etPrecoJogo.getText().toString().isEmpty()){
            jogo.setPreco(Float.parseFloat(etPrecoJogo.getText().toString()));
        }
        return jogo;
    }

    private void preencheCampos(Jogo j){
        etCodigoJogo.setText(String.valueOf(j.getCodigo()));
        etNomeJogo.setText(String.valueOf(j.getNome()));
        etPrecoJogo.setText(String.valueOf(j.getPreco()));
        etDesenvolvedoraJogo.setText(String.valueOf(j.getDesenvolvedora()));
        etIdadeJogo.setText(String.valueOf(j.getIdadeRecomendada()));
    }

    private void limpaCampos(){
        etCodigoJogo.setText("");
        etNomeJogo.setText("");
        etPrecoJogo.setText("");
        etDesenvolvedoraJogo.setText("");
        etIdadeJogo.setText("");
    }

}
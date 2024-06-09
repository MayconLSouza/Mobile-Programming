package br.edu.fateczl.biblioteca;

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

import br.edu.fateczl.biblioteca.controller.RevistaController;
import br.edu.fateczl.biblioteca.model.Revista;
import br.edu.fateczl.biblioteca.persistence.RevistaDao;

public class RevistaFragment extends Fragment {

    private View view;
    private EditText etCodigoRevista, etNomeRevista, etPaginasRevista, etISSNRevista;
    private Button btnBuscarRevista, btnInserirRevista, btnModificarRevista, btnExcluirRevista, btnListarRevista;
    private TextView tvListarRevista;
    private RevistaController rCont;

    public RevistaFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_revista, container, false);
        etCodigoRevista = view.findViewById(R.id.etCodigoRevista);
        etNomeRevista = view.findViewById(R.id.etNomeRevista);
        etPaginasRevista = view.findViewById(R.id.etPaginasRevista);
        etISSNRevista = view.findViewById(R.id.etISSNRevista);
        btnBuscarRevista = view.findViewById(R.id.btnBuscarRevista);
        btnListarRevista = view.findViewById(R.id.btnListarRevista);
        btnInserirRevista = view.findViewById(R.id.btnInserirRevista);
        btnModificarRevista = view.findViewById(R.id.btnModificarRevista);
        btnExcluirRevista = view.findViewById(R.id.btnExcluirRevista);
        tvListarRevista = view.findViewById(R.id.tvListarRevista);
        tvListarRevista.setMovementMethod(new ScrollingMovementMethod());

        rCont = new RevistaController(new RevistaDao(view.getContext()));

        btnInserirRevista.setOnClickListener(op -> acaoInserir());
        btnModificarRevista.setOnClickListener(op -> acaoModificar());
        btnExcluirRevista.setOnClickListener(op -> acaoExcluir());
        btnBuscarRevista.setOnClickListener(op -> acaoBuscar());
        btnListarRevista.setOnClickListener(op -> acaoListar());

        return view;
    }

    private void acaoInserir() {
        Revista revista = montaRevista();
        try {
            rCont.inserir(revista);
            Toast.makeText(view.getContext(), "Revista inserida com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoModificar() {
        Revista revista = montaRevista();
        try {
            rCont.modificar(revista);
            Toast.makeText(view.getContext(), "Revista atualizada com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoExcluir() {
        Revista revista = montaRevista();
        try {
            rCont.deletar(revista);
            Toast.makeText(view.getContext(), "Revista excluída com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoBuscar() {
        Revista revista = montaRevista();
        try{
            revista = rCont.buscar(revista);
            if(revista.getNome() != null){
                preencheCampos(revista);
            } else {
                Toast.makeText(view.getContext(), "Revista excluída com sucesso", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoListar() {
        try{
            List<Revista> revistas = rCont.listar();
            StringBuffer buffer = new StringBuffer();
            for(Revista r : revistas){
                buffer.append(r.toString() + "\n");
            }
            tvListarRevista.setText(buffer.toString());
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Revista montaRevista() {
        Revista r = new Revista();
        r.setCodigo(Integer.parseInt(etCodigoRevista.getText().toString()));
        r.setNome(etNomeRevista.getText().toString());
        r.setQtdPaginas(Integer.parseInt(etPaginasRevista.getText().toString()));
        r.setIssn(etISSNRevista.getText().toString());

        return r;
    }

    private void preencheCampos(Revista revista) {
        etCodigoRevista.setText(String.valueOf(revista.getCodigo()));
        etNomeRevista.setText(revista.getNome());
        etPaginasRevista.setText(String.valueOf(revista.getQtdPaginas()));
        etISSNRevista.setText(revista.getIssn());
    }

    private void limpaCampos() {
        etCodigoRevista.setText("");
        etNomeRevista.setText("");
        etPaginasRevista.setText("");
        etISSNRevista.setText("");
    }

}
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

import br.edu.fateczl.biblioteca.controller.LivroController;
import br.edu.fateczl.biblioteca.model.Aluno;
import br.edu.fateczl.biblioteca.model.Livro;
import br.edu.fateczl.biblioteca.persistence.LivroDao;

public class LivroFragment extends Fragment {

    private View view;
    private EditText etCodigoLivro, etNomeLivro, etPaginasLivro, etISBNLivro, etEdicaoLivro;
    private Button btnBuscarLivro, btnInserirLivro, btnModificarLivro, btnExcluirLivro, btnListarLivro;
    private TextView tvListarLivro;
    private LivroController lCont;

    public LivroFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_livro, container, false);
        etCodigoLivro = view.findViewById(R.id.etCodigoLivro);
        etNomeLivro = view.findViewById(R.id.etNomeLivro);
        etPaginasLivro = view.findViewById(R.id.etPaginasLivro);
        etISBNLivro = view.findViewById(R.id.etISBNLivro);
        etEdicaoLivro = view.findViewById(R.id.etEdicaoLivro);
        btnBuscarLivro = view.findViewById(R.id.btnBuscarLivro);
        btnInserirLivro = view.findViewById(R.id.btnInserirLivro);
        btnModificarLivro = view.findViewById(R.id.btnModificarLivro);
        btnExcluirLivro = view.findViewById(R.id.btnExcluirLivro);
        btnListarLivro = view.findViewById(R.id.btnListarLivro);
        tvListarLivro = view.findViewById(R.id.tvListarLivro);
        tvListarLivro.setMovementMethod(new ScrollingMovementMethod());

        lCont = new LivroController(new LivroDao(view.getContext()));

        btnInserirLivro.setOnClickListener(op -> acaoInserir());
        btnModificarLivro.setOnClickListener(op -> acaoModificar());
        btnExcluirLivro.setOnClickListener(op -> acaoExcluir());
        btnBuscarLivro.setOnClickListener(op -> acaoBuscar());
        btnListarLivro.setOnClickListener(op -> acaoListar());

        return view;
    }

    private void acaoInserir() {
        Livro livro = montaLivro();
        try{
            lCont.inserir(livro);
            Toast.makeText(view.getContext(),"Livro inserido com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoModificar() {
        Livro livro = montaLivro();
        try{
            lCont.modificar(livro);
            Toast.makeText(view.getContext(),"Livro atualizado com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoExcluir() {
        Livro livro = montaLivro();
        try{
            lCont.deletar(livro);
            Toast.makeText(view.getContext(),"Livro excluído com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoBuscar() {
        Livro livro = montaLivro();
        try{
            livro = lCont.buscar(livro);
            if(livro.getNome() != null){
                preencheCampos(livro);
            } else {
                Toast.makeText(view.getContext(),"Livro não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoListar() {
        try{
            List<Livro> livros = lCont.listar();
            StringBuffer buffer = new StringBuffer();
            for(Livro l : livros){
                buffer.append(l.toString() + "\n");
            }
            tvListarLivro.setText(buffer.toString());
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Livro montaLivro() {
        Livro livro = new Livro();
        livro.setCodigo(Integer.parseInt(etCodigoLivro.getText().toString()));
        livro.setNome(etNomeLivro.getText().toString());
        livro.setQtdPaginas(Integer.parseInt(etPaginasLivro.getText().toString()));
        livro.setIsbn(etISBNLivro.getText().toString());
        livro.setEdicao(Integer.parseInt(etEdicaoLivro.getText().toString()));

        return livro;
    }

    private void preencheCampos(Livro livro) {
        etCodigoLivro.setText(String.valueOf(livro.getCodigo()));
        etNomeLivro.setText(livro.getNome());
        etPaginasLivro.setText(String.valueOf(livro.getQtdPaginas()));
        etISBNLivro.setText(livro.getIsbn());
        etEdicaoLivro.setText(String.valueOf(livro.getEdicao()));
    }

    private void limpaCampos() {
        etCodigoLivro.setText("");
        etNomeLivro.setText("");
        etPaginasLivro.setText("");
        etISBNLivro.setText("");
        etEdicaoLivro.setText("");
    }
}
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

import br.edu.fateczl.biblioteca.controller.AlunoController;
import br.edu.fateczl.biblioteca.model.Aluno;
import br.edu.fateczl.biblioteca.persistence.AlunoDao;

public class AlunoFragment extends Fragment {

    private View view;
    private EditText etRAAluno, etNomeAluno, etEmailAluno;
    private Button btnBuscarAluno, btnInserirAluno, btnModificarAluno, btnExcluirAluno, btnListarAluno;
    private TextView tvListarAluno;
    private AlunoController aCont;

    public AlunoFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_aluno, container, false);
        etRAAluno = view.findViewById(R.id.etRAAluno);
        etNomeAluno = view.findViewById(R.id.etNomeAluno);
        etEmailAluno = view.findViewById(R.id.etEmailAluno);
        btnBuscarAluno = view.findViewById(R.id.btnBuscarAluno);
        btnInserirAluno = view.findViewById(R.id.btnInserirAluno);
        btnModificarAluno = view.findViewById(R.id.btnModificarAluno);
        btnExcluirAluno = view.findViewById(R.id.btnExcluirAluno);
        btnListarAluno = view.findViewById(R.id.btnListarAluno);
        tvListarAluno = view.findViewById(R.id.tvListarAluno);
        tvListarAluno.setMovementMethod(new ScrollingMovementMethod());

        aCont = new AlunoController(new AlunoDao(view.getContext()));

        btnInserirAluno.setOnClickListener(op -> acaoInserir());
        btnModificarAluno.setOnClickListener(op -> acaoModificar());
        btnExcluirAluno.setOnClickListener(op -> acaoExcluir());
        btnBuscarAluno.setOnClickListener(op -> acaoBuscar());
        btnListarAluno.setOnClickListener(op -> acaoListar());

        return view;
    }

    private void acaoInserir() {
        Aluno aluno = montaAluno();
        try{
            aCont.inserir(aluno);
            Toast.makeText(view.getContext(),"Aluno inserido com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoModificar() {
        Aluno aluno = montaAluno();
        try{
            aCont.modificar(aluno);
            Toast.makeText(view.getContext(),"Aluno atualizado com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoExcluir() {
        Aluno aluno = montaAluno();
        try{
            aCont.deletar(aluno);
            Toast.makeText(view.getContext(),"Aluno excluído com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoBuscar() {
        Aluno aluno = montaAluno();
        try{
            aluno = aCont.buscar(aluno);
            if(aluno.getNome() != null){
                preencheCampos(aluno);
            } else {
                Toast.makeText(view.getContext(),"Aluno não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoListar() {
        try{
            List<Aluno> alunos = aCont.listar();
            StringBuffer buffer = new StringBuffer();
            for(Aluno a : alunos){
                buffer.append(a.toString() + "\n");
            }
            tvListarAluno.setText(buffer.toString());
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Aluno montaAluno() {
        Aluno aluno = new Aluno();
        aluno.setRa(Integer.parseInt(etRAAluno.getText().toString()));
        aluno.setNome(etNomeAluno.getText().toString());
        aluno.setEmail(etEmailAluno.getText().toString());

        return aluno;
    }

    private void preencheCampos(Aluno aluno) {
        etRAAluno.setText(String.valueOf(aluno.getRa()));
        etNomeAluno.setText(aluno.getNome());
        etEmailAluno.setText(aluno.getEmail());
    }

    private void limpaCampos() {
        etRAAluno.setText("");
        etNomeAluno.setText("");
        etEmailAluno.setText("");
    }

}
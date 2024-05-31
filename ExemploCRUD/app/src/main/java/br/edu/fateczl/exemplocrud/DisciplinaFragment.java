package br.edu.fateczl.exemplocrud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.exemplocrud.controller.DisciplinaController;
import br.edu.fateczl.exemplocrud.controller.ProfessorController;
import br.edu.fateczl.exemplocrud.model.Disciplina;
import br.edu.fateczl.exemplocrud.model.Professor;
import br.edu.fateczl.exemplocrud.persistence.DisciplinaDao;
import br.edu.fateczl.exemplocrud.persistence.ProfessorDao;

public class DisciplinaFragment extends Fragment {

    private View view;
    private EditText etCodigoDisc, etNomeDisc;
    private Spinner spProfDisc;
    private Button btnInserirDisc, btnModificarDisc, btnExcluirDisc, btnListarDisc, btnBuscarDisc;
    private TextView tvListarDisc;
    private DisciplinaController dCont;
    private ProfessorController pCont;
    private List<Professor> professores;

    public DisciplinaFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_disciplina, container, false);
        etCodigoDisc = view.findViewById(R.id.etCodigoDisc);
        etNomeDisc = view.findViewById(R.id.etNomeDisc);
        spProfDisc = view.findViewById(R.id.spProfDisc);
        btnInserirDisc = view.findViewById(R.id.btnInserirDisc);
        btnModificarDisc = view.findViewById(R.id.btnModificarDisc);
        btnExcluirDisc = view.findViewById(R.id.btnExcluirDisc);
        btnListarDisc = view.findViewById(R.id.btnListarDisc);
        btnBuscarDisc = view.findViewById(R.id.btnBuscarDisc);
        tvListarDisc = view.findViewById(R.id.tvListarDisc);
        tvListarDisc.setMovementMethod(new ScrollingMovementMethod());

        dCont = new DisciplinaController(new DisciplinaDao(view.getContext()));
        pCont = new ProfessorController(new ProfessorDao(view.getContext()));

        preencheSpinner();

        btnInserirDisc.setOnClickListener(op -> acaoInserir());
        btnModificarDisc.setOnClickListener(op -> acaoModificar());
        btnExcluirDisc.setOnClickListener(op -> acaoExcluir());
        btnBuscarDisc.setOnClickListener(op -> acaoBuscar());
        btnListarDisc.setOnClickListener(op -> acaoListar());
        return view;
    }

    private void acaoInserir() {
        int spPos = spProfDisc.getSelectedItemPosition();
        if(spPos > 0){
            Disciplina disciplina = montaDisciplina();
            try {
                dCont.inserir(disciplina);
                Toast.makeText(view.getContext(), "Disciplina inserida com sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione um professor", Toast.LENGTH_LONG).show();
        }
    }

    private void acaoModificar() {
        int spPos = spProfDisc.getSelectedItemPosition();
        if(spPos > 0){
            Disciplina disciplina = montaDisciplina();
            try {
                dCont.modificar(disciplina);
                Toast.makeText(view.getContext(), "Disciplina atualizada com sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione um professor", Toast.LENGTH_LONG).show();
        }
    }

    private void acaoExcluir() {
        Disciplina disciplina = montaDisciplina();
        try {
            dCont.deletar(disciplina);
            Toast.makeText(view.getContext(), "Disciplina excluída com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoBuscar() {
        Disciplina disciplina = montaDisciplina();
        try {
            professores = pCont.listar();
            disciplina = dCont.buscar(disciplina);
            if(disciplina.getNome() != null){
                preencheCampos(disciplina);
            } else {
                Toast.makeText(view.getContext(), "Disciplina não encontrada", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoListar() {
        try {
            List<Disciplina> disciplinas = dCont.listar();
            StringBuffer buffer = new StringBuffer();
            for(Disciplina d : disciplinas){
                buffer.append(d.toString() + "\n");
            }
            tvListarDisc.setText(buffer.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void preencheSpinner() {
        Professor p0 = new Professor();
        p0.setCodigo(0);
        p0.setNome("Selecione um professor");
        p0.setTitulacao("");

        try {
            professores = pCont.listar();
            professores.add(0, p0);

            ArrayAdapter ad = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, professores);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spProfDisc.setAdapter(ad);

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Disciplina montaDisciplina(){
        Disciplina d = new Disciplina();
        d.setCodigo(Integer.parseInt(etCodigoDisc.getText().toString()));
        d.setNome(etNomeDisc.getText().toString());
        d.setProfessor((Professor) spProfDisc.getSelectedItem());

        return d;
    }

    private void limpaCampos(){
        etCodigoDisc.setText("");
        etNomeDisc.setText("");
        spProfDisc.setSelection(0);
    }

    public void preencheCampos(Disciplina d){
        etCodigoDisc.setText(String.valueOf(d.getCodigo()));
        etNomeDisc.setText(d.getNome());

        int cont = 1;
        for(Professor p : professores){
            if(p.getCodigo() == d.getProfessor().getCodigo()){
                spProfDisc.setSelection(cont);
            } else {
                cont++;
            }
        }
        if(cont > professores.size()){
            spProfDisc.setSelection(0);
        }
    }
}
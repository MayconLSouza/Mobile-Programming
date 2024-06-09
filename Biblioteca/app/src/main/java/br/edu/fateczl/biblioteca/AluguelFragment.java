package br.edu.fateczl.biblioteca;

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
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.biblioteca.controller.AluguelController;
import br.edu.fateczl.biblioteca.controller.AlunoController;
import br.edu.fateczl.biblioteca.controller.LivroController;
import br.edu.fateczl.biblioteca.controller.RevistaController;
import br.edu.fateczl.biblioteca.model.Aluguel;
import br.edu.fateczl.biblioteca.model.Aluno;
import br.edu.fateczl.biblioteca.model.Exemplar;
import br.edu.fateczl.biblioteca.model.Livro;
import br.edu.fateczl.biblioteca.persistence.AluguelDao;
import br.edu.fateczl.biblioteca.persistence.AlunoDao;
import br.edu.fateczl.biblioteca.persistence.LivroDao;
import br.edu.fateczl.biblioteca.persistence.RevistaDao;

public class AluguelFragment extends Fragment {

    private Spinner spAlunoAluguel;
    private Spinner spExemplarAluguel;
    private EditText etDtRetiradaAluguel;
    private EditText etDtDevolucaoAluguel;
    private Button btnBuscarAluguel;
    private Button btnInserirAluguel;
    private Button btnModificarAluguel;
    private Button btnExcluirAluguel;
    private Button btnListarAluguel;
    private TextView tvListarAluguel;
    private View view;
    private AluguelController aluguelCont;
    private AlunoController alunoCont;
    private LivroController lCont;
    private RevistaController rCont;
    private List<Aluno> alunos;
    private List<Exemplar> exemplares;

    public AluguelFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_aluguel, container, false);
        spAlunoAluguel = view.findViewById(R.id.spAlunoAluguel);
        spExemplarAluguel = view.findViewById(R.id.spExemplarAluguel);
        etDtRetiradaAluguel = view.findViewById(R.id.etDtRetiradaAluguel);
        etDtDevolucaoAluguel = view.findViewById(R.id.etDtDevolucaoAluguel);
        btnBuscarAluguel = view.findViewById(R.id.btnBuscarAluguel);
        btnInserirAluguel = view.findViewById(R.id.btnInserirALuguel);
        btnModificarAluguel = view.findViewById(R.id.btnModificarAluguel);
        btnExcluirAluguel = view.findViewById(R.id.btnExcluirAluguel);
        btnListarAluguel = view.findViewById(R.id.btnListarAluguel);
        tvListarAluguel = view.findViewById(R.id.tvListarAluguel);
        tvListarAluguel.setMovementMethod(new ScrollingMovementMethod());

        aluguelCont = new AluguelController(new AluguelDao(view.getContext()));
        alunoCont = new AlunoController(new AlunoDao(view.getContext()));
        lCont = new LivroController(new LivroDao(view.getContext()));
        rCont = new RevistaController(new RevistaDao(view.getContext()));

        preencheSpinnerAluno();
        preencheSpinnerExemplar();

        btnInserirAluguel.setOnClickListener(op -> acaoInserir());
        btnModificarAluguel.setOnClickListener(op -> acaoModificar());
        btnExcluirAluguel.setOnClickListener(op -> acaoExcluir());
        btnBuscarAluguel.setOnClickListener(op -> acaoBuscar());
        btnListarAluguel.setOnClickListener(op -> acaoListar());

        return view;
    }

    private void acaoInserir() {
        int spAluno = spAlunoAluguel.getSelectedItemPosition();
        int spExemplar = spExemplarAluguel.getSelectedItemPosition();
        if (spAluno > 0 && spExemplar > 0){
            Aluguel aluguel = montaAluguel();
            try{
                aluguelCont.inserir(aluguel);
                Toast.makeText(view.getContext(), "Aluguel inserido com sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException e){
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione uma opção", Toast.LENGTH_LONG).show();
        }
    }

    private void acaoModificar() {
        int spAluno = spAlunoAluguel.getSelectedItemPosition();
        int spExemplar = spExemplarAluguel.getSelectedItemPosition();
        if (spAluno > 0 && spExemplar > 0){
            Aluguel aluguel = montaAluguel();
            try{
                aluguelCont.inserir(aluguel);
                Toast.makeText(view.getContext(), "Aluguel atualizado com sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException e){
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione uma opção", Toast.LENGTH_LONG).show();
        }
    }

    private void acaoExcluir() {
        Aluguel aluguel = montaAluguel();
        try{
            aluguelCont.deletar(aluguel);
            Toast.makeText(view.getContext(), "Aluguel excluído com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoBuscar() {
        Aluguel aluguel = montaAluguel();
        try{
            aluguel = aluguelCont.buscar(aluguel);
            if(aluguel.getDataRetirada() != null){
                preencheCampos(aluguel);
            } else {
                Toast.makeText(view.getContext(), "Aluguel não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoListar() {
        try{
            List<Aluguel> alugueis = aluguelCont.listar();
            StringBuffer buffer = new StringBuffer();
            for(Aluguel a : alugueis){
                buffer.append(a.toString() + "\n");
            }
            tvListarAluguel.setText(buffer.toString());
        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Aluguel montaAluguel() {
        Aluguel a = new Aluguel();
        a.setAluno((Aluno) spAlunoAluguel.getSelectedItem());
        a.setExemplar((Exemplar) spExemplarAluguel.getSelectedItem());
        a.setDataRetirada(etDtRetiradaAluguel.getText().toString());
        a.setDataDevolucao(etDtDevolucaoAluguel.getText().toString());

        return a;
    }

    private void preencheCampos(Aluguel aluguel) {
        etDtRetiradaAluguel.setText(aluguel.getDataRetirada());
        etDtDevolucaoAluguel.setText(aluguel.getDataDevolucao());
        int cont = 1;
        for(Aluno a : alunos){
            if(a.getRa() == aluguel.getAluno().getRa()){
                spAlunoAluguel.setSelection(cont);
            } else {
                cont++;
            }
        }
        if(cont > alunos.size()){
            spAlunoAluguel.setSelection(0);
        }
        cont = 1;
        for(Exemplar e : exemplares){
            if(e.getCodigo() == aluguel.getExemplar().getCodigo()){
                spExemplarAluguel.setSelection(cont);
            } else {
                cont++;
            }
        }
        if(cont > exemplares.size()){
            spExemplarAluguel.setSelection(0);
        }
    }

    private void limpaCampos() {
        spAlunoAluguel.setSelection(0);
        spExemplarAluguel.setSelection(0);
        etDtDevolucaoAluguel.setText("");
        etDtRetiradaAluguel.setText("");
    }

    private void preencheSpinnerAluno() {
        Aluno aluno0 = new Aluno();
        aluno0.setRa(0);
        aluno0.setNome("Selecione um aluno");
        aluno0.setEmail("");

        try{
            List<Aluno> alunos = alunoCont.listar();
            alunos.add(0, aluno0);

            ArrayAdapter ad = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, alunos);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spAlunoAluguel.setAdapter(ad);
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void preencheSpinnerExemplar() {
        Livro livro0 = new Livro();
        livro0.setCodigo(0);
        livro0.setNome("Selecione Exemplar");

        try{
            exemplares = new ArrayList<>();
            exemplares.addAll(lCont.listar());
            exemplares.addAll(rCont.listar());

            List<Exemplar> exemplarSpinner = new ArrayList<>();
            exemplarSpinner.add(0, livro0);
            exemplarSpinner.addAll(exemplares);

            ArrayAdapter ad = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, exemplarSpinner);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spExemplarAluguel.setAdapter(ad);
        }catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
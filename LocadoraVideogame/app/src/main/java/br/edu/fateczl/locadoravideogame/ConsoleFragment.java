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

import br.edu.fateczl.locadoravideogame.controller.ConsoleController;
import br.edu.fateczl.locadoravideogame.model.Console;
import br.edu.fateczl.locadoravideogame.persistence.ConsoleDao;

public class ConsoleFragment extends Fragment {

    private View view;
    private EditText etCodigoConsole, etNomeConsole, etPrecoConsole, etFabricanteConsole;
    private Button btnBuscarConsole, btnInserirConsole, btnModificarConsole, btnExcluirConsole, btnListarConsole;
    private TextView tvListarConsole;
    private ConsoleController cCont;

    public ConsoleFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_console, container, false);
        etCodigoConsole = view.findViewById(R.id.etCodigoConsole);
        etNomeConsole = view.findViewById(R.id.etNomeConsole);
        etPrecoConsole = view.findViewById(R.id.etPrecoConsole);
        etFabricanteConsole = view.findViewById(R.id.etFabricanteConsole);
        btnBuscarConsole = view.findViewById(R.id.btnBuscarConsole);
        btnInserirConsole = view.findViewById(R.id.btnInserirConsole);
        btnModificarConsole = view.findViewById(R.id.btnModificarConsole);
        btnExcluirConsole = view.findViewById(R.id.btnExcluirConsole);
        btnListarConsole = view.findViewById(R.id.btnListarConsole);
        tvListarConsole = view.findViewById(R.id.tvListarConsole);

        cCont = new ConsoleController(new ConsoleDao(view.getContext()));

        btnInserirConsole.setOnClickListener(op -> acaoInserir());
        btnModificarConsole.setOnClickListener(op -> acaoModificar());
        btnExcluirConsole.setOnClickListener(op -> acaoExcluir());
        btnBuscarConsole.setOnClickListener(op -> acaoBuscar());
        btnListarConsole.setOnClickListener(op -> acaoListar());

        return view;
    }

    private void acaoInserir() {
        Console console = montaConsole();
        try {
            cCont.inserir(console);
            Toast.makeText(view.getContext(), "Console inserido com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoModificar() {
        Console console = montaConsole();
        try {
            cCont.modificar(console);
            Toast.makeText(view.getContext(), "Console atualizado com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoExcluir() {
        Console console = montaConsole();
        try {
            cCont.deletar(console);
            Toast.makeText(view.getContext(), "Console excluído com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoBuscar() {
        Console console = montaConsole();
        try {
            console = cCont.buscar(console);
            if(console.getNome() != null){
                preencheCampos(console);
            } else{
                Toast.makeText(view.getContext(), "Console não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoListar() {
        try{
            List<Console> consoles = cCont.listar();
            StringBuffer buffer = new StringBuffer();
            for(Console c : consoles){
                buffer.append(c.toString() + "\n");
            }
            tvListarConsole.setText(buffer.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Console montaConsole(){
        Console c = new Console();
        c.setCodigo(Integer.parseInt(etCodigoConsole.getText().toString()));
        c.setNome(etNomeConsole.getText().toString());
        c.setFabricante(etFabricanteConsole.getText().toString());
        if(!etPrecoConsole.getText().toString().isEmpty()){
            c.setPreco(Float.parseFloat(etPrecoConsole.getText().toString()));
        }
        return c;
    }

    private void preencheCampos(Console c){
        etCodigoConsole.setText(String.valueOf(c.getCodigo()));
        etNomeConsole.setText(String.valueOf(c.getNome()));
        etPrecoConsole.setText(String.valueOf(c.getPreco()));
        etFabricanteConsole.setText(String.valueOf(c.getFabricante()));
    }

    private void limpaCampos(){
        etCodigoConsole.setText("");
        etNomeConsole.setText("");
        etPrecoConsole.setText("");
        etFabricanteConsole.setText("");
    }
}
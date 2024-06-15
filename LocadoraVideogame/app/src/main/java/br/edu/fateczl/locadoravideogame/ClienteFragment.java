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

import br.edu.fateczl.locadoravideogame.controller.ClienteController;
import br.edu.fateczl.locadoravideogame.model.Cliente;
import br.edu.fateczl.locadoravideogame.persistence.ClienteDao;

public class ClienteFragment extends Fragment {

    private View view;
    private EditText etCPFCLiente, etNomeCliente, etEmailCliente;
    private Button btnBuscarCliente, btnInserirCliente, btnModificarCliente, btnExcluirCliente, btnListarCliente;
    private TextView tvListarCliente;
    private ClienteController cCont;

    public ClienteFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cliente, container, false);
        etCPFCLiente = view.findViewById(R.id.etCPFCliente);
        etNomeCliente = view.findViewById(R.id.etNomeCliente);
        etEmailCliente = view.findViewById(R.id.etEmailCliente);
        btnBuscarCliente = view.findViewById(R.id.btnBuscarCliente);
        btnInserirCliente = view.findViewById(R.id.btnInserirCliente);
        btnModificarCliente = view.findViewById(R.id.btnModificarCliente);
        btnExcluirCliente = view.findViewById(R.id.btnExcluirCliente);
        btnListarCliente = view.findViewById(R.id.btnListarCliente);
        tvListarCliente = view.findViewById(R.id.tvListarCliente);

        cCont = new ClienteController(new ClienteDao(view.getContext()));

        btnInserirCliente.setOnClickListener(op -> acaoInserir());
        btnModificarCliente.setOnClickListener(op -> acaoModificar());
        btnExcluirCliente.setOnClickListener(op -> acaoExcluir());
        btnBuscarCliente.setOnClickListener(op -> acaoBuscar());
        btnListarCliente.setOnClickListener(op -> acaoListar());

        return view;
    }

    private void acaoInserir() {
        Cliente cliente = montaCliente();
        try {
            cCont.inserir(cliente);
            Toast.makeText(view.getContext(), "Cliente inserido com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoModificar() {
        Cliente cliente = montaCliente();
        try {
            cCont.modificar(cliente);
            Toast.makeText(view.getContext(), "Cliente atualizado com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoExcluir() {
        Cliente cliente = montaCliente();
        try {
            cCont.deletar(cliente);
            Toast.makeText(view.getContext(), "Cliente excluído com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoBuscar() {
        Cliente cliente = montaCliente();
        try {
            cliente = cCont.buscar(cliente);
            if(cliente.getNome() != null){
                preencheCampos(cliente);
            } else {
                Toast.makeText(view.getContext(), "Cliente não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void acaoListar() {
        try {
            List<Cliente> clientes = cCont.listar();
            StringBuffer buffer = new StringBuffer();
            for(Cliente c : clientes){
                buffer.append(c.toString() + "\n");
            }
            tvListarCliente.setText(buffer.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Cliente montaCliente(){
        Cliente c = new Cliente();
        c.setCpf(Integer.parseInt(etCPFCLiente.getText().toString()));
        c.setNome(etNomeCliente.getText().toString());
        c.setEmail(etEmailCliente.getText().toString());
        return c;
    }

    private void preencheCampos(Cliente c){
        etCPFCLiente.setText(String.valueOf(c.getCpf()));
        etNomeCliente.setText(String.valueOf(c.getNome()));
        etEmailCliente.setText(String.valueOf(c.getEmail()));
    }

    private void limpaCampos(){
        etCPFCLiente.setText("");
        etNomeCliente.setText("");
        etEmailCliente.setText("");
    }
}
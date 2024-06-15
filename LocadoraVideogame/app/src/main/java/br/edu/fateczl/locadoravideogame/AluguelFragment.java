package br.edu.fateczl.locadoravideogame;

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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.locadoravideogame.controller.AluguelController;
import br.edu.fateczl.locadoravideogame.controller.ClienteController;
import br.edu.fateczl.locadoravideogame.controller.ConsoleController;
import br.edu.fateczl.locadoravideogame.controller.JogoController;
import br.edu.fateczl.locadoravideogame.model.Aluguel;
import br.edu.fateczl.locadoravideogame.model.Cliente;
import br.edu.fateczl.locadoravideogame.model.Console;
import br.edu.fateczl.locadoravideogame.model.Produto;
import br.edu.fateczl.locadoravideogame.persistence.AluguelDao;
import br.edu.fateczl.locadoravideogame.persistence.ClienteDao;
import br.edu.fateczl.locadoravideogame.persistence.ConsoleDao;
import br.edu.fateczl.locadoravideogame.persistence.JogoDao;

public class AluguelFragment extends Fragment {

    private View view;
    private EditText etDtRetiradaAluguel, etDtDevolucaoAluguel;
    private Button btnBuscarAluguel, btnInserirAluguel, btnModificarAluguel, btnExcluirAluguel, btnListarAluguel;
    private TextView tvListarAluguel;
    private Spinner spClienteAluguel, spProdutoAluguel;
    private ClienteController clienteController;
    private AluguelController aluguelController;
    private JogoController jogoController;
    private ConsoleController consoleController;
    private List<Cliente> clientes;
    private List<Produto> produtos;

    public AluguelFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_aluguel, container, false);
        etDtRetiradaAluguel = view.findViewById(R.id.etDtRetiradaAluguel);
        etDtDevolucaoAluguel = view.findViewById(R.id.etDtDevolucaoAluguel);
        btnBuscarAluguel = view.findViewById(R.id.btnBuscarAluguel);
        btnInserirAluguel = view.findViewById(R.id.btnInserirAluguel);
        btnModificarAluguel = view.findViewById(R.id.btnModificarAluguel);
        btnExcluirAluguel = view.findViewById(R.id.btnExcluirAluguel);
        btnListarAluguel = view.findViewById(R.id.btnListarAluguel);
        tvListarAluguel = view.findViewById(R.id.tvListarAluguel);
        spClienteAluguel = view.findViewById(R.id.spClienteAluguel);
        spProdutoAluguel = view.findViewById(R.id.spProdutoAluguel);

        aluguelController = new AluguelController(new AluguelDao(view.getContext()));
        clienteController = new ClienteController(new ClienteDao(view.getContext()));
        jogoController = new JogoController(new JogoDao(view.getContext()));
        consoleController = new ConsoleController(new ConsoleDao(view.getContext()));

        preencheSpinnerCliente();
        preencheSpinnerProduto();

        btnInserirAluguel.setOnClickListener(op -> acaoInserir());
        btnModificarAluguel.setOnClickListener(op -> acaoModificar());
        btnExcluirAluguel.setOnClickListener(op -> acaoExcluir());
        btnBuscarAluguel.setOnClickListener(op -> acaoBuscar());
        btnListarAluguel.setOnClickListener(op -> acaoListar());

        return view;
    }

    private void acaoInserir() {
        int positionCliente = spClienteAluguel.getSelectedItemPosition();
        int positionProduto = spProdutoAluguel.getSelectedItemPosition();
        if(positionCliente > 0 && positionProduto > 0) {
            Aluguel a = montaAluguel();
            try {
                aluguelController.inserir(a);
                Toast.makeText(view.getContext(), "Aluguel inserido com sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione uma opção", Toast.LENGTH_LONG).show();
        }
    }

    private void acaoModificar() {
        int positionCliente = spClienteAluguel.getSelectedItemPosition();
        int positionProduto = spProdutoAluguel.getSelectedItemPosition();
        if(positionCliente > 0 && positionProduto > 0) {
            Aluguel a = montaAluguel();
            try {
                aluguelController.modificar(a);
                Toast.makeText(view.getContext(), "Aluguel atualizado com sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione uma opção", Toast.LENGTH_LONG).show();
        }
    }

    private void acaoExcluir() {
        Aluguel a = montaAluguel();
        try {
            aluguelController.deletar(a);
            Toast.makeText(view.getContext(), "Aluguel excluído com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoBuscar() {
        Aluguel a = montaAluguel();
        try {
            a = aluguelController.buscar(a);
            if(a.getCliente().getNome() != null){
                preencheCampos(a);
            } else {
                Toast.makeText(view.getContext(), "Aluguel não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoListar() {
        try {
            List<Aluguel> alugueis = aluguelController.listar();
            StringBuffer buffer = new StringBuffer();
            for(Aluguel a : alugueis){
                buffer.append(a.toString() + "\n");
            }
            tvListarAluguel.setText(buffer.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Aluguel montaAluguel(){
        Aluguel a = new Aluguel();
        a.setDataRetirada(LocalDate.parse(etDtRetiradaAluguel.getText().toString()));
        if(!etDtDevolucaoAluguel.getText().toString().isEmpty()){
            a.setDataDevolucao(LocalDate.parse(etDtDevolucaoAluguel.getText().toString()));
        }
        a.setCliente((Cliente) spClienteAluguel.getSelectedItem());
        a.setProduto((Produto) spProdutoAluguel.getSelectedItem());
        return a;
    }

    private void limpaCampos(){
        etDtRetiradaAluguel.setText("");
        etDtDevolucaoAluguel.setText("");
        spClienteAluguel.setSelection(0);
        spProdutoAluguel.setSelection(0);
    }

    public void preencheCampos(Aluguel a){
        etDtRetiradaAluguel.setText(String.valueOf(a.getDataRetirada()));
        etDtDevolucaoAluguel.setText(String.valueOf(a.getDataDevolucao()));

        int cont = 0;
        for(Cliente c : clientes){
            if(c.getCpf() == a.getCliente().getCpf()){
                spClienteAluguel.setSelection(cont);
            } else {
                cont++;
            }
            System.out.println(c.toString());
        }
        if(cont > clientes.size()){
            spClienteAluguel.setSelection(0);
        }

        cont = 1;
        for(Produto p : produtos){
            if(p.getCodigo() == a.getProduto().getCodigo()){
                spProdutoAluguel.setSelection(cont);
            } else {
                cont++;
            }
        }
        if(cont > produtos.size()){
            spProdutoAluguel.setSelection(0);
        }
    }

    private void preencheSpinnerCliente() {
        Cliente c0 = new Cliente();
        c0.setCpf(0);
        c0.setNome("Selecione um cliente");
        c0.setEmail("");

        try {
            clientes = clienteController.listar();
            clientes.add(0, c0);

            ArrayAdapter ad = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, clientes);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spClienteAluguel.setAdapter(ad);
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void preencheSpinnerProduto() {
        Console c0 = new Console();
        c0.setCodigo(0);
        c0.setNome("Selecione um produto");
        c0.setPreco(0.0f);
        c0.setFabricante("");
        produtos = new ArrayList<>();
        List<Produto> produtosSpinner = new ArrayList<>();

        try{
            produtos.addAll(jogoController.listar());
            produtos.addAll(consoleController.listar());

            produtosSpinner.add(0, c0);
            produtosSpinner.addAll(produtos);
            ArrayAdapter ad = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, produtosSpinner);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spProdutoAluguel.setAdapter(ad);
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
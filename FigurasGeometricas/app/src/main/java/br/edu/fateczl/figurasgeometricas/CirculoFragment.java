package br.edu.fateczl.figurasgeometricas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.fateczl.figurasgeometricas.controller.CirculoController;
import br.edu.fateczl.figurasgeometricas.controller.IGeometriaController;
import br.edu.fateczl.figurasgeometricas.model.Circulo;

public class CirculoFragment extends Fragment {

    private View view;
    private EditText etRaio;
    private Button btnAreaCirculo;
    private Button btnPerimetroCirculo;
    private TextView tvResultadoCirculo;

    public CirculoFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_circulo, container, false);
        etRaio = view.findViewById(R.id.etRaio);
        btnAreaCirculo = view.findViewById(R.id.btnAreaCirculo);
        btnPerimetroCirculo = view.findViewById(R.id.btnPerimetroCirculo);
        tvResultadoCirculo = view.findViewById(R.id.tvResultadoCirculo);

        btnAreaCirculo.setOnClickListener(op -> areaCirculo());
        btnPerimetroCirculo.setOnClickListener(op -> perimetroCirculo());
        return view;
    }

    private void areaCirculo() {
        Circulo c = new Circulo();
        c.setRaio(Float.parseFloat(etRaio.getText().toString()));

        IGeometriaController<Circulo> op = new CirculoController();
        float area = op.calcularArea(c);
        String res = "Área = " + area;
        tvResultadoCirculo.setText(res);
        limpaCampos();
    }

    private void perimetroCirculo() {
        Circulo c = new Circulo();
        c.setRaio(Float.parseFloat(etRaio.getText().toString()));

        IGeometriaController<Circulo> op = new CirculoController();
        float perimetro = op.calcularPerimetro(c);
        String res = "Perímetro = " + perimetro;
        tvResultadoCirculo.setText(res);
        limpaCampos();
    }

    private void limpaCampos() {
        etRaio.setText("");
    }
}
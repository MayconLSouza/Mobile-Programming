package br.edu.fateczl.figurasgeometricas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.fateczl.figurasgeometricas.controller.IGeometriaController;
import br.edu.fateczl.figurasgeometricas.controller.RetanguloController;
import br.edu.fateczl.figurasgeometricas.model.Retangulo;

public class RetanguloFragment extends Fragment {

    private View view;
    private EditText etComprimento;
    private EditText etLargura;
    private Button btnAreaRetangulo;
    private Button btnPerimetroRetangulo;
    private TextView tvResultadoRetangulo;

    public RetanguloFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_retangulo, container, false);
        etComprimento = view.findViewById(R.id.etComprimento);
        etLargura = view.findViewById(R.id.etLargura);
        btnAreaRetangulo = view.findViewById(R.id.btnAreaRetangulo);
        btnPerimetroRetangulo = view.findViewById(R.id.btnPerimetroRetangulo);
        tvResultadoRetangulo = view.findViewById(R.id.tvResultadoRetangulo);
        tvResultadoRetangulo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnAreaRetangulo.setOnClickListener(op -> areaRetangulo());
        btnPerimetroRetangulo.setOnClickListener(op -> perimetroRetangulo());
        return view;
    }

    private void areaRetangulo() {
        Retangulo r = new Retangulo();
        r.setComprimento(Float.parseFloat(etComprimento.getText().toString()));
        r.setLargura(Float.parseFloat(etLargura.getText().toString()));

        IGeometriaController<Retangulo> op = new RetanguloController();
        float area = op.calcularArea(r);
        String res = "Área = " + area;
        tvResultadoRetangulo.setText(res);
        limpaCampos();
    }

    private void perimetroRetangulo() {
        Retangulo r = new Retangulo();
        r.setComprimento(Float.parseFloat(etComprimento.getText().toString()));
        r.setLargura(Float.parseFloat(etLargura.getText().toString()));

        IGeometriaController<Retangulo> op = new RetanguloController();
        float perimetro = op.calcularPerimetro(r);
        String res = "Perímetro = " + perimetro;
        tvResultadoRetangulo.setText(res);
        limpaCampos();
    }

    private void limpaCampos() {
        etComprimento.setText("");
        etLargura.setText("");
    }
}
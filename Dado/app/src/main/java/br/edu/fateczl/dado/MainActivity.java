package br.edu.fateczl.dado;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbUmDado;
    private RadioButton rbDoisDados;
    private RadioButton rbTresDados;
    private Spinner spDado;
    private Button btnLancar;
    private TextView tvSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rbUmDado = findViewById(R.id.rbUmDado);
        rbUmDado.setChecked(true);
        rbDoisDados = findViewById(R.id.rbDoisDados);
        rbTresDados = findViewById(R.id.rbTresDados);
        spDado = findViewById(R.id.spDado);
        btnLancar = findViewById(R.id.btnLancar);
        tvSaida = findViewById(R.id.tvSaida);

        preencheSpinner();
        btnLancar.setOnClickListener(op -> lancarDados());
    }

    private void lancarDados() {
        StringBuffer buffer = new StringBuffer();
        Integer qtd = (Integer) spDado.getSelectedItem();

        if(rbUmDado.isChecked()){
            buffer.append(faceDado(qtd));
        } else if (rbDoisDados.isChecked()){
            for(int i = 0; i <= 1; i++){
                buffer.append(faceDado(qtd));
                if(i == 0){
                    buffer.append(", ");
                }
            }
        } else if (rbTresDados.isChecked()){
            for(int i = 0; i <= 2; i++){
                buffer.append(faceDado(qtd));
                if(i != 2){
                    buffer.append(", ");
                }
            }
        }
        tvSaida.setText(buffer.toString());
    }

    private int faceDado(Integer qtd) {
        Random random = new Random();
        int numeroFace = random.nextInt(qtd) + 1;
        return numeroFace;
    }

    private void preencheSpinner() {
        List<Integer> lista = new ArrayList<>();
        lista.add(4);
        lista.add(6);
        lista.add(8);
        lista.add(10);
        lista.add(12);
        lista.add(20);
        lista.add(100);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDado.setAdapter(adapter);
    }
}
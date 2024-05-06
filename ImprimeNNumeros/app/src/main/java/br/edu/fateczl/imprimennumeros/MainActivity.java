package br.edu.fateczl.imprimennumeros;

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

public class MainActivity extends AppCompatActivity {

    private RadioButton rbImpar;
    private RadioButton rbPar;
    private Spinner spQtdNum;
    private Button btnGerar;
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

        rbPar = findViewById(R.id.rbPar);
        rbPar.setChecked(true);
        rbImpar = findViewById(R.id.rbImpar);
        spQtdNum = findViewById(R.id.spQtdNum);
        btnGerar = findViewById(R.id.btnGerar);
        tvSaida = findViewById(R.id.tvSaida);

        preencheSpinner();
        btnGerar.setOnClickListener(op -> gerar());
    }

    private void gerar() {
        StringBuffer buffer = new StringBuffer();
        Integer qtd = (Integer) spQtdNum.getSelectedItem();
        int cont = 0;
        int i = 1;
        if(rbPar.isChecked()){
            while (cont < qtd){
                if (i % 2 == 0){
                    buffer.append(i);
                    if (cont < qtd -1){
                        buffer.append(", ");
                    }
                    cont++;
                }
                i++;
            }
        }
        if(rbImpar.isChecked()){
            while (cont < qtd){
                if (i % 2 == 1){
                    buffer.append(i);
                    if (cont < qtd -1){
                        buffer.append(", ");
                    }
                    cont++;
                }
                i++;
            }
        }
        tvSaida.setText(buffer.toString());
    }

    private void preencheSpinner() {
        List<Integer> lista = new ArrayList<>();
        lista.add(2);
        lista.add(4);
        lista.add(8);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQtdNum.setAdapter(adapter);
    }
}
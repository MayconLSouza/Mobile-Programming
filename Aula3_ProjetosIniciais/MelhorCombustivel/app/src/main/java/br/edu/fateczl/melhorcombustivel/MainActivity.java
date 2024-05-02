package br.edu.fateczl.melhorcombustivel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etGasolina;
    private EditText etEtanol;
    private TextView tvResultado;
    private Button btnCalcular;

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

        etGasolina = findViewById(R.id.etGasolina);
        etGasolina.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etEtanol = findViewById(R.id.etEtanol);
        etEtanol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvResultado = findViewById(R.id.tvResultado);
        tvResultado.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(op -> Calculo());
    }

    private void Calculo() {
        float gasolina = Float.parseFloat(etGasolina.getText().toString());
        float etanol = Float.parseFloat(etEtanol.getText().toString());
        String resultado;
        float vantagem = (etanol / gasolina);

        if (vantagem <= 0.7) {
            resultado = getString(R.string.resultado) + " Etanol";
        } else {
            resultado = getString(R.string.resultado) + " Gasolina";
        }

        tvResultado.setText(resultado);

    }

}
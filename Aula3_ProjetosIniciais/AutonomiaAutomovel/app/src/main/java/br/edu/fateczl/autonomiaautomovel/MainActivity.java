package br.edu.fateczl.autonomiaautomovel;

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

    private EditText etConsumoMedio;
    private EditText etQuantidadeCombustivel;
    private Button btnCalcular;
    private TextView tvAutonomia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvAutonomia), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etConsumoMedio = findViewById(R.id.etConsumoMedio);
        etConsumoMedio.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etQuantidadeCombustivel = findViewById(R.id.etQuantidadeCombustivel);
        etQuantidadeCombustivel.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvAutonomia = findViewById(R.id.tvAutonomia);
        tvAutonomia.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(op -> Calculo());
    }

    private void Calculo(){
        double consumoMedio = Double.parseDouble(etConsumoMedio.getText().toString());
        double quantidadeCombustivel = Double.parseDouble(etQuantidadeCombustivel.getText().toString());
        double consumoMedioPorMetros = consumoMedio * 1000;
        double autonomia = quantidadeCombustivel * consumoMedioPorMetros;

        String resultado = getString(R.string.autonomia) + " " + autonomia + " m";
        tvAutonomia.setText(resultado);
    }
}
package br.edu.fateczl.eqsegundagrau;

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

    private EditText etValorA;
    private EditText etValorB;
    private EditText etValorC;
    private TextView tvDelta;
    private TextView tvRes1;
    private TextView tvRes2;


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

        etValorA =findViewById(R.id.etValorA);
        etValorA.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etValorB = findViewById(R.id.etValorB);
        etValorB.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etValorC = findViewById(R.id.etValorC);
        etValorC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvDelta = findViewById(R.id.tvDelta);
        tvDelta.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvRes1 = findViewById(R.id.tvRes1);
        tvRes1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvRes2 = findViewById(R.id.tvRes2);
        tvRes2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button btnCalc =findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(op -> calc());
    }

    private void calc(){

        float a = Float.parseFloat(etValorA.getText().toString());
        float b = Float.parseFloat(etValorB.getText().toString());
        float c = Float.parseFloat(etValorC.getText().toString());

        float delta = (b * b) - (4 * a * c);

        if (delta < 0) {
            String resDelta = "A equação não possui raízes reais";
            tvDelta.setText(resDelta);
            tvRes1.setText("");
            tvRes2.setText("");
        } else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);

            String resDelta = getString(R.string.delta) + " " + delta;
            String res1 = getString(R.string.resultado_x1) + " " + x1;
            String res2 = getString(R.string.resultado_x2) + " " + x2;
            tvDelta.setText(resDelta);
            tvRes1.setText(res1);
            tvRes2.setText(res2);
        }
    }
}
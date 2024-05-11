package br.edu.fateczl.professorsalario;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.edu.fateczl.professorsalario.model.Professor;
import br.edu.fateczl.professorsalario.model.ProfessorHorista;
import br.edu.fateczl.professorsalario.model.ProfessorTitular;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbTitular;
    private RadioButton rbHorista;
    private EditText etAnos;
    private EditText etHoras;
    private Button btnCalcular;
    private TextView tvSalario;


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

        etAnos = findViewById(R.id.etAnos);
        etHoras = findViewById(R.id.etHoras);
        btnCalcular = findViewById(R.id.btnCalcular);
        tvSalario = findViewById((R.id.tvSalario));
        rbTitular = findViewById(R.id.rbTitular);
        rbTitular.setChecked(true);
        rbHorista = findViewById(R.id.rbHorista);
        
        btnCalcular.setOnClickListener(op -> calcular());
    }

    private void calcular() {
        double sal = 0;
        if(rbTitular.isChecked()){
            if (!etAnos.getText().toString().isEmpty()) {
                ProfessorTitular prof = new ProfessorTitular();
                int anosInstituicao = Integer.parseInt(etAnos.getText().toString());
                sal = prof.calcularSalario(anosInstituicao);
            }
        }
        if(rbHorista.isChecked()){
            if (!etHoras.getText().toString().isEmpty()) {
                ProfessorHorista prof = new ProfessorHorista();
                int horas = Integer.parseInt(etHoras.getText().toString());
                sal = prof.calcularSalario(horas);
            }
        }
        BigDecimal salario = BigDecimal.valueOf(sal).setScale(2, RoundingMode.HALF_UP);
        tvSalario.setText(getString(R.string.salario) + salario);
    }

}
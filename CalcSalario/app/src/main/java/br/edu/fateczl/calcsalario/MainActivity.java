package br.edu.fateczl.calcsalario;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.edu.fateczl.calcsalario.model.Funcionario;
import br.edu.fateczl.calcsalario.model.Supervisor;
import br.edu.fateczl.calcsalario.model.Venderdor;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbVend;
    private RadioButton rbSup;
    private EditText etHoras;
    private CheckBox cbBonus;
    private Button btnCalc;
    private TextView tvSalario;
    private Funcionario func;

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

        etHoras = findViewById(R.id.etHoras);
        cbBonus = findViewById(R.id.cbBonus);
        cbBonus.setChecked(false);
        btnCalc = findViewById(R.id.btnCalc);
        tvSalario = findViewById(R.id.tvSalario);
        rbVend = findViewById(R.id.rbVend);
        rbVend.setChecked(true);
        rbSup = findViewById(R.id.rbSup);

        btnCalc.setOnClickListener(op -> calc());
    }

    private void calc() {
        int horas = Integer.parseInt(etHoras.getText().toString());
        if(rbVend.isChecked()){
            func = new Venderdor();
        }
        if(rbSup.isChecked()){
            func = new Supervisor();
        }
        double sal = func.calcSalario(horas);
        if(cbBonus.isChecked()){
            double bonus = func.calcBonus();
            sal += bonus;
        }
        BigDecimal salario = BigDecimal.valueOf(sal).setScale(2, RoundingMode.HALF_UP);
        tvSalario.setText(getString(R.string.salario) + salario);
    }
}
package br.edu.fateczl.calcsalario2;

import static br.edu.fateczl.calcsalario2.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import br.edu.fateczl.calcsalario2.model.Funcionario;
import br.edu.fateczl.calcsalario2.model.Supervisor;
import br.edu.fateczl.calcsalario2.model.Vendedor;

public class MainActivity extends AppCompatActivity {
    private RadioButton rbVend;
    private RadioButton rbSup;
    private EditText etHoras;
    private EditText etCpf;
    private EditText etNome;
    private CheckBox cbBonus;
    private Button btnCalc;
    private Funcionario func;

    @SuppressLint("MissingInflatedId")
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
        etCpf = findViewById(R.id.etCpf);
        etNome = findViewById(R.id.etNome);
        cbBonus = findViewById(R.id.cbBonus);
        cbBonus.setChecked(false);
        btnCalc = findViewById(R.id.btnCalc);
        rbVend = findViewById(R.id.rbVend);
        rbVend.setChecked(true);
        rbSup = findViewById(R.id.rbSup);

        btnCalc.setOnClickListener(op -> calc());
    }

    private void calc() {
        int horas = Integer.parseInt(etHoras.getText().toString());
        String tipo = "";
        if(rbVend.isChecked()){
            func = new Vendedor();
            tipo = "vendedor";
        }
        if(rbSup.isChecked()){
            func = new Supervisor();
            tipo = "vendedor";
        }
        String cpf = etCpf.getText().toString();
        String nome = etNome.getText().toString();
        double sal = func.calcSalario(horas);
        if(cbBonus.isChecked()){
            double bonus = func.calcBonus();
            sal += bonus;
        }

        Log.i("CPF", cpf);
        Log.i("Nome", nome);
        Log.i("Sal", String.valueOf(sal));

        Bundle bundle = new Bundle();
        bundle.putString("cpf", cpf);
        bundle.putString("nome", nome);
        bundle.putDouble("sal", sal);
        bundle.putString("tipo", tipo);

        troca(bundle);
    }

    private void troca(Bundle bundle) {
        Intent i = new Intent(this, SaidaActivity.class);
        i.putExtras(bundle);
        this.startActivity(i);
        this.finish();
    }
}
package br.edu.fateczl.idade;

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

import java.time.LocalDate;
import java.time.Period;

public class MainActivity extends AppCompatActivity {

    private  EditText etDia;
    private EditText etMes;
    private EditText etAno;
    private TextView tvIdade;
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

        etDia = findViewById(R.id.etDia);
        etDia.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etMes = findViewById(R.id.etMes);
        etMes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etAno = findViewById(R.id.etAno);
        etAno.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvIdade = findViewById(R.id.tvIdade);
        tvIdade.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(op -> Calcular());
    }

    private void Calcular(){
        int dia = Integer.parseInt(etDia.getText().toString());
        int mes = Integer.parseInt(etMes.getText().toString());
        int ano = Integer.parseInt(etAno.getText().toString());
        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);
        LocalDate dataAtual = LocalDate.now();
        Period idade = Period.between(dataNascimento, dataAtual);

        String resultado = idade.getYears() + " ano(s), " + idade.getMonths() +
                " mes(es) e " + idade.getDays() + " dia(s)";
        tvIdade.setText(resultado);
    }
}
package br.edu.fateczl.calcsalario2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class SaidaActivity extends AppCompatActivity {

    private Funcionario func;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_saida);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvSaida = findViewById(R.id.tvSaida);
        tvSaida.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String tipo = bundle.getString("tipo");
        if(tipo.equals("vendedor")){
            func = new Vendedor();
        } else{
            func = new Supervisor();
        }
        func.setCpf(bundle.getString("cpf"));
        func.setNome(bundle.getString("nome"));
        double sal = bundle.getDouble("sal");
        BigDecimal salario = BigDecimal.valueOf(sal).setScale(2, RoundingMode.HALF_UP);
        tvSaida.setText(tipo.toUpperCase()+"\n"+func.getCpf()+"\n"+func.getNome()+"\n"+salario);
        btnVoltar.setOnClickListener(op -> voltar());
    }

    private void voltar() {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
        this.finish();
    }
}
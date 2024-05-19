package br.edu.fateczl.vendaingresso;

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

import br.edu.fateczl.vendaingresso.model.Ingresso;
import br.edu.fateczl.vendaingresso.model.IngressoVIP;

public class SaidaActivity extends AppCompatActivity {

    private Ingresso ingresso;

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
        String funcao = "";
        if(tipo.equals("VIP")){
            ingresso = new IngressoVIP();
            funcao = bundle.getString("funcao");
        } else{
            ingresso = new Ingresso();
        }
        ingresso.setId(bundle.getString("id"));
        ingresso.setValor(bundle.getFloat("valor"));
        float valorFinal = ingresso.calcularValorFinal(bundle.getDouble("taxa"));
        BigDecimal preco = BigDecimal.valueOf(valorFinal).setScale(2, RoundingMode.HALF_UP);
        tvSaida.setText(tipo.toUpperCase()+"\n"+ingresso.getId()+"\n$ "+preco+"\n"+funcao);
        btnVoltar.setOnClickListener(op -> voltar());
    }

    private void voltar() {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
        this.finish();
    }
}
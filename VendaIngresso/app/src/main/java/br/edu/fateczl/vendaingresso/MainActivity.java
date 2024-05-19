package br.edu.fateczl.vendaingresso;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.vendaingresso.model.Ingresso;
import br.edu.fateczl.vendaingresso.model.IngressoVIP;

public class MainActivity extends AppCompatActivity {

    private EditText etID;
    private EditText etValor;
    private EditText etTaxa;
    private EditText etFuncao;
    private CheckBox cbVIP;
    private Button btnCalc;
    private Ingresso ingresso;

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

        etID = findViewById(R.id.etID);
        etValor = findViewById(R.id.etValor);
        etFuncao = findViewById(R.id.etFuncao);
        etTaxa = findViewById(R.id.etTaxa);
        cbVIP = findViewById(R.id.cbVIP);
        cbVIP.setChecked(false);
        btnCalc = findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(op -> calcular());
    }

    private void calcular() {
        String id = etID.getText().toString();
        float valor = Float.parseFloat(etValor.getText().toString());
        double taxa = Double.parseDouble(etTaxa.getText().toString());
        String tipo;
        Bundle bundle = new Bundle();
        String funcao = "";

        if (cbVIP.isChecked()){
            ingresso = new IngressoVIP();
            funcao = etFuncao.getText().toString();
            tipo = "VIP";
        } else {
            ingresso = new Ingresso();
            tipo = "Standard";
        }

        bundle.putString("funcao", funcao);
        bundle.putString("id", id);
        bundle.putFloat("valor", valor);
        bundle.putDouble("taxa", taxa);
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
package br.edu.fateczl.contabancaria;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.contabancaria.model.ContaEspecial;
import br.edu.fateczl.contabancaria.model.ContaPoupanca;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbPoupanca;
    private RadioButton rbEspecial;
    private EditText etNumConta;
    private Spinner spOperacao;
    private EditText etValor;
    private TextView tvRes;
    private Button btnConfirmar;
    private ContaPoupanca contaPoupanca;
    private ContaEspecial contaEspecial;

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

        rbPoupanca = findViewById(R.id.rbPoupanca);
        rbPoupanca.setChecked(true);
        rbEspecial = findViewById(R.id.rbEspecial);
        etNumConta = findViewById(R.id.etNumConta);
        spOperacao = findViewById(R.id.spOperacao);
        etValor = findViewById(R.id.etValor);
        tvRes = findViewById(R.id.tvRes);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        setConta();
        preencheSpinner();
        btnConfirmar.setOnClickListener(op -> executarOperacao());
    }

    private void setConta() {
        contaPoupanca = new ContaPoupanca("Fulano", 1, 500, 30);
        contaEspecial = new ContaEspecial("Beltrano", 2, 1000, 200);
    }

    private void executarOperacao() {
        String resposta;
        String numContaStr = etNumConta.getText().toString();
        if(rbPoupanca.isChecked()){
           if(numContaStr.equals("1")){
                String operacao = (String) spOperacao.getSelectedItem();
                switch (operacao){
                    case "Saque":
                        float valorSaque = Float.parseFloat(etValor.getText().toString());
                        resposta = contaPoupanca.sacar(valorSaque);
                        tvRes.setText(resposta);
                        break;
                    case "Depósito":
                        float valorDeposito = Float.parseFloat(etValor.getText().toString());
                        resposta = contaPoupanca.depositar(valorDeposito);
                        tvRes.setText(resposta);
                        break;
                    case "Exibir saldo com rendimento":
                        double taxaRendimento = 0.05d;
                        resposta = contaPoupanca.calcularNovoSaldo(taxaRendimento);
                        tvRes.setText(resposta);
                        break;
                    case "Exibir dados":
                        resposta = contaPoupanca.toString();
                        tvRes.setText(resposta);
                        break;
                }
           } else {
               String erro = "Conta Inválida";
               tvRes.setText(erro);
           }
        } else if (rbEspecial.isChecked()){
            if(numContaStr.equals("2")){
                String operacao = (String) spOperacao.getSelectedItem();
                switch (operacao){
                    case "Saque":
                        float valorSaque = Float.parseFloat(etValor.getText().toString());
                        resposta = contaEspecial.sacar(valorSaque);
                        tvRes.setText(resposta);
                        break;
                    case "Depósito":
                        float valorDeposito = Float.parseFloat(etValor.getText().toString());
                        resposta = contaEspecial.depositar(valorDeposito);
                        tvRes.setText(resposta);
                        break;
                    case "Exibir saldo com rendimento":
                        resposta = "Operação Inválida para conta especial";
                        tvRes.setText(resposta);
                        break;
                    case "Exibir dados":
                        resposta = contaEspecial.toString();
                        tvRes.setText(resposta);
                        break;
                }
            } else {
                String erro = "Conta Inválida";
                tvRes.setText(erro);
            }
        }

    }

    private void preencheSpinner() {
        List<String> operacao = new ArrayList<>();
        operacao.add("Saque");
        operacao.add("Depósito");
        operacao.add("Exibir saldo com rendimento");
        operacao.add("Exibir dados");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, operacao);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOperacao.setAdapter(adapter);
    }
}
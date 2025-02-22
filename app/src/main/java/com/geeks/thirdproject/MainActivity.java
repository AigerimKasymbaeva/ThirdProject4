package com.geeks.thirdproject;

import android.content.Intent;
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

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private int firstOperand, secondOperand;
    private boolean isOperationClick;
    private String currentOperation;
    private MaterialButton btnShare;

    // 12 + 21 = 33



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); */

        textView = findViewById(R.id.text_view);
        btnShare = findViewById(R.id.btn_share);

        btnShare.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("result", textView.getText().toString());
            startActivity(intent);
        });

    }

    public void onNumberClick(View view) {
        String textButton = ((MaterialButton)view).getText().toString();

        if (textButton.equals("AC")) {
            textView.setText("0");
            firstOperand = 0;
            btnShare.setVisibility(View.GONE); // Скрываем кнопку
        } else if (textView.getText().toString().equals("0") || isOperationClick) {
            textView.setText(textButton);
        } else {
            textView.append(textButton);
        }
        isOperationClick = false;
        btnShare.setVisibility(View.GONE); // Скрываем кнопку при нажатии на числа
    }

    public void onOperationClick(View view) {
        if (view.getId() == R.id.btn_plus) {
            firstOperand = Integer.parseInt(textView.getText().toString());
            currentOperation = "+";
        } else if (view.getId() == R.id.btn_minus) {
            firstOperand = Integer.parseInt(textView.getText().toString());
            currentOperation = "-";
        } else if (view.getId() == R.id.btn_multiplication) {
            firstOperand = Integer.parseInt(textView.getText().toString());
            currentOperation = "*";
        } else if (view.getId() == R.id.btn_division) {
            firstOperand = Integer.parseInt(textView.getText().toString());
            currentOperation = "/";
        } else if (view.getId() == R.id.btn_equal) {
            secondOperand = Integer.parseInt(textView.getText().toString());

            int result = 0;

            if (currentOperation.equals("+")) {
                result = firstOperand + secondOperand;
            } else if (currentOperation.equals("-")) {
                result = firstOperand - secondOperand;
            } else if (currentOperation.equals("*")) {
                result = firstOperand * secondOperand;
            } else if (currentOperation.equals("/")) {
                result = firstOperand / secondOperand;
            }

            textView.setText(String.valueOf(result));
            btnShare.setVisibility(View.VISIBLE); // Показываем кнопку
        }
        isOperationClick = true;
    }

}
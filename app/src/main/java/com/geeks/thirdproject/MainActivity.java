package com.geeks.thirdproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private int firstOperand;
    private int secondOperand;
    private boolean isOperationClick;
    private Button btnEqual;
    private String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.text_view);
        btnEqual = findViewById(R.id.btn_equal);
    }

    public void onNumberClick(View view) {
        String textButton = ((MaterialButton) view).getText().toString();

        if (textButton.equals("AC")) {
            textView.setText("0");
            firstOperand = 0;
            secondOperand = 0;
            operation = "";
            btnEqual.setEnabled(false);
        } else if (textView.getText().toString().equals("0") || isOperationClick) {
            // После нажатия операции вводим новое число, заменяя старое
            textView.setText(textButton);
        } else {
            // Если операция не была нажата, продолжаем дописывать число
            textView.append(textButton);
        }
        isOperationClick = false; // Теперь это обычный ввод числа
        btnEqual.setEnabled(true);
    }

    public void onOperationClick(View view) {
        if (!operation.isEmpty()) {
            // Если операция уже была выбрана, выполняем её перед выбором новой
            onEqualClick(view);
        }

        firstOperand = Integer.parseInt(textView.getText().toString());

        if (view.getId() == R.id.btn_plus) {
            operation = "+";
        } else if (view.getId() == R.id.btn_multiplication) {
            operation = "x";
        } else if (view.getId() == R.id.btn_division) {
            operation = "/";
        } else if (view.getId() == R.id.btn_minus) {
            operation = "-";
        }

        isOperationClick = true; // Флаг показывает, что следующее число будет новым
    }

    public void onEqualClick(View view) {
        if (operation.isEmpty()) return; // Если операция не выбрана — ничего не делаем

        secondOperand = Integer.parseInt(textView.getText().toString());

        int result = 0;
        switch (operation) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "x":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand == 0) {
                    textView.setText("Error");
                    return;
                }
                result = firstOperand / secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
        }

        textView.setText(String.valueOf(result));
        btnEqual.setEnabled(false);
        isOperationClick = true; // После "=" можно ввести новое число
        operation = ""; // Сбрасываем операцию после выполнения
    }
}
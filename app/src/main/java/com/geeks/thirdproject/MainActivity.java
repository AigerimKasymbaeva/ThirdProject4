package com.geeks.thirdproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private int firstOperand, secondOperand;
    private boolean isOperationClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
    }

    public void onNumberClick(View view) {
        String textButton = ((MaterialButton) view).getText().toString();

        if (textButton.equals("AC")) {
            result.setText("0");
        } else if (result.getText().toString().equals("0") || isOperationClick) {
            result.setText(textButton);
        } else {
            result.append(textButton);
        }
        isOperationClick = false;
    }

    public void onOperationClick(View view) {
        if (view.getId() == R.id.btn_minus) {
            firstOperand = Integer.parseInt(result.getText().toString());

        } else if (view.getId() == R.id.btn_plus) {
            firstOperand = Integer.parseInt(result.getText().toString());
        }else if (view.getId() == R.id.btn_division) {
            firstOperand = Integer.parseInt(result.getText().toString());
        } else if (view.getId() == R.id.btn_multiplication) {
            firstOperand = Integer.parseInt(result.getText().toString());
        } else if (view.getId() == R.id.btn_equal) {
            secondOperand = Integer.parseInt(result.getText().toString());

        int minus = firstOperand - secondOperand;
        int plus = firstOperand + secondOperand;
        int multiplication = firstOperand * secondOperand;
        int division = firstOperand / secondOperand;

        result.setText(String.valueOf(minus));
        result.setText(String.valueOf(plus));
        result.setText(String.valueOf(multiplication));
        result.setText(String.valueOf(division));
    }
    isOperationClick = true;
}
}
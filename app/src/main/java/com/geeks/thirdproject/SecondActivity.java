package com.geeks.thirdproject;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.RangeSlider;

import java.sql.Types;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private TextView resultTextView;
    private MaterialButton btnNext;
    private ImageButton heartButton;
    private boolean isLiked = false;
    private TextView Category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        heartButton = findViewById(R.id.heart_button);
        Category = findViewById(R.id.category);

        // Обработка нажатия на сердечко
        heartButton.setOnClickListener(v -> {
            if (isLiked) {
                heartButton.setBackgroundResource(R.drawable.heart_blue); // Синее сердечко
                Category.setBackgroundResource(R.drawable.corners2);
            } else {
                heartButton.setBackgroundResource(R.drawable.heart_pink); // Розовое сердечко
                Category.setBackgroundResource(R.drawable.corners);
            }
            isLiked = !isLiked; // Меняем состояние
        });

        resultTextView = findViewById(R.id.result_text_view);
        btnNext = findViewById(R.id.btn_next);

        // Получаем результат из Intent
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");

        // Устанавливаем результат в TextView
        if (result != null) {
            resultTextView.setText(result);
        }

        btnNext.setOnClickListener(v -> {
            // Закрываем все активити
            finishAffinity();
        });

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        String[] types = getResources().getStringArray(R.array.Types);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, types);
        autoCompleteTextView.setAdapter(arrayAdapter);

        RangeSlider rangeSlider = findViewById(R.id.rangeSlider);

        rangeSlider.addOnChangeListener((slider, value, fromUser) -> {
            List<Float> values = slider.getValues();
            int minValue = Math.round(values.get(0));
            int maxValue = Math.round(values.get(1));
        });

    }
}
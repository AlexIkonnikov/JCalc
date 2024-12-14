package com.example.calculator;

import android.os.Bundle;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.display);
        editText.requestFocus();
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        setButtonListener(editText);
        setOnResetListener(editText);
        setOperatorOnClickListener(editText);
        setOnResultListener(editText);
    }

    private void setButtonListener(EditText editText) {
        findViewById(R.id.zero).setOnClickListener(v -> editText.append("0"));
        findViewById(R.id.b1).setOnClickListener(v -> editText.append("1"));
        findViewById(R.id.b2).setOnClickListener(v -> editText.append("2"));
        findViewById(R.id.b3).setOnClickListener(v -> editText.append("3"));
        findViewById(R.id.b4).setOnClickListener(v -> editText.append("4"));
        findViewById(R.id.b5).setOnClickListener(v -> editText.append("5"));
        findViewById(R.id.b6).setOnClickListener(v -> editText.append("6"));
        findViewById(R.id.b7).setOnClickListener(v -> editText.append("7"));
        findViewById(R.id.b8).setOnClickListener(v -> editText.append("8"));
        findViewById(R.id.b9).setOnClickListener(v -> editText.append("9"));
    }

    private void setOnResetListener(EditText editText) {

        findViewById(R.id.reset).setOnLongClickListener(v -> {
            Editable text = editText.getText();
            if (text.length() > 0 ) {
                text.delete(0, text.length());;
                editText.setSelection(0);
            }
            return false;
        });

        findViewById(R.id.reset).setOnClickListener(v -> {
            Editable text = editText.getText();
            if (text.length() > 0 ) {
                text.delete(text.length() - 1, text.length());;
                editText.setSelection(0);
            }
        });
    }

    private void setOperatorOnClickListener(EditText editText) {
        findViewById(R.id.plus).setOnClickListener(v -> editText.append("+"));
        findViewById(R.id.minus).setOnClickListener(v -> editText.append("-"));
        findViewById(R.id.division).setOnClickListener(v -> editText.append("/"));
        findViewById(R.id.multiply).setOnClickListener(v -> editText.append("*"));
    }

    private void setOnResultListener(EditText editText) {
        findViewById(R.id.equal).setOnClickListener(v -> {
            Editable text = editText.getText();
            String expression = text.toString();
            Expression exp = new Expression(expression);
            String result = Integer.toString((int) exp.calculate());
            editText.setText(result);
            editText.setSelection(result.length());
        });
    }
}
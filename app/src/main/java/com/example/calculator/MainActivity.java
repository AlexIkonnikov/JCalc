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
        editText.setOnTouchListener(new EditText.OnTouchListener() {
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
        findViewById(R.id.comma).setOnClickListener(v -> handleCommaClick(editText));
    }

    private void handleCommaClick(EditText editText) {
        //TODO: implement
//        Editable editable = editText.getText();
//        editText.append(".");
    }

    private void setOnResetListener(EditText editText) {
        findViewById(R.id.reset).setOnLongClickListener(v -> {
            Editable text = editText.getText();
            if (text.length() > 0 ) {
                text.delete(0, text.length());;
            }
            return false;
        });

        findViewById(R.id.reset).setOnClickListener(v -> {
            Editable text = editText.getText();
            if (text.length() > 0 ) {
                text.delete(text.length() - 1, text.length());;
            }
        });
    }

    private void setOperatorOnClickListener(EditText editText) {
        findViewById(R.id.plus).setOnClickListener(v -> handleOperatorClick(editText,"+"));
        findViewById(R.id.minus).setOnClickListener(v -> handleOperatorClick(editText,"-"));
        findViewById(R.id.division).setOnClickListener(v -> handleOperatorClick(editText,"/"));
        findViewById(R.id.multiply).setOnClickListener(v -> handleOperatorClick(editText,"*"));
    }

    private void handleOperatorClick(EditText editText, String operator) {
        String operators = "+-/*";
        Editable text = editText.getText();
        if (text.length() <= 0) return;
        String toString = text.toString();
        String lastChar = toString.substring(toString.length() - 1);

        if (!operators.contains(lastChar)) {
            editText.append(operator);
            return;
        }

        text.replace(toString.length() - 1,toString.length() , operator);
    }

    private void setOnResultListener(EditText editText) {
        findViewById(R.id.equal).setOnClickListener(v -> {
            Editable text = editText.getText();
            String expression = text.toString();
            Expression exp = new Expression(expression);
            Double res = exp.calculate();

            String result = "";

            if (res - res.intValue() == 0) {
                result = Integer.toString(res.intValue());
            } else {
                Integer count = Math.min(res.toString().length(), 7);
                result = res.toString().substring(0, count);
            }

            editText.setText(result);
            editText.setSelection(result.length());
        });
    }
}
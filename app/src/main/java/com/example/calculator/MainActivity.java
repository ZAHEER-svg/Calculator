package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.mariuszgromada.math.mxparser.Expression;
import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private EditText display_expression;
    private TextView history;


    public void addListenerToButtons(){
        Button one;
        Button two;
        Button three;
        Button four;
        Button five;
        Button six;
        Button seven;
        Button eight;
        Button nine;
        Button zero;
        Button plus;
        Button minus;
        Button divide;
        Button multiply;
        Button dot;
        Button equal;
        Button reset;


        zero = findViewById(R.id.zero_btn);
        zero.setOnClickListener(new DisplayValue());

        one = findViewById(R.id.one_btn);
        one.setOnClickListener(new DisplayValue());

        two = findViewById(R.id.two_btn);
        two.setOnClickListener(new DisplayValue());

        three = findViewById(R.id.three_btn);
        three.setOnClickListener(new DisplayValue());

        four = findViewById(R.id.four_btn);
        four.setOnClickListener(new DisplayValue());

        five = findViewById(R.id.five_btn);
        five.setOnClickListener(new DisplayValue());

        six = findViewById(R.id.six_btn);
        six.setOnClickListener(new DisplayValue());

        seven = findViewById(R.id.seven_btn);
        seven.setOnClickListener(new DisplayValue());

        eight = findViewById(R.id.eight_btn);
        eight.setOnClickListener(new DisplayValue());

        nine = findViewById(R.id.nine_btn);
        nine.setOnClickListener(new DisplayValue());

        reset = findViewById(R.id.reset);
        reset.setOnClickListener(v -> backSpace());
        reset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clearText();
                clearHistory();
                return true;
            }
        });

        plus = findViewById(R.id.plus);
        plus.setOnClickListener(new AppendHistory());

        minus = findViewById(R.id.minus);
        minus.setOnClickListener(new AppendHistory());

        divide = findViewById(R.id.divide_btn);
        divide.setOnClickListener(new AppendHistory());

        multiply = findViewById(R.id.multiply);
        multiply.setOnClickListener(new AppendHistory());

        dot = findViewById(R.id.dot_btn);
        dot.setOnClickListener(new DisplayValue());

        equal = findViewById(R.id.equals);
        equal.setOnClickListener(v -> calculateExpression());
    }

    public void disableKeyBoardSystem (){
        display_expression.setShowSoftInputOnFocus(false);
    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        addListenerToButtons();

        history = (TextView) findViewById(R.id.history);
        display_expression = (EditText) findViewById(R.id.display_expressions);
        disableKeyBoardSystem();
    }


    public void updateText(String value){
        Editable oldStr = display_expression.getText();
        int cursorPos = display_expression.getSelectionStart();
        oldStr.insert(cursorPos, value);

        display_expression.setText(oldStr);
        display_expression.setSelection(cursorPos + 1);
    }

    public void updateHistory(String operator){
        String history_text = (String) history.getText();
        Editable display_text = display_expression.getText();

        String newStr = String.format("%s%s%s", history_text, display_text,operator);

        history.setText(newStr);
        clearText();
    }

    public void clearText(){
        display_expression.setText("");
    }

    public void clearHistory(){
        history.setText("");
    }

    public void backSpace(){
        Editable str = display_expression.getText();
        int cursorPos = display_expression.getSelectionStart();
        if(cursorPos != 0) str.delete(cursorPos -1, cursorPos);
    }

    public String backSpaceHistoryText(){
        String history_expression = (String) history.getText();
        history_expression = history_expression.substring(0, history_expression.length() - 1);

        return  history_expression;
    }

    public String returnFinalExpression(){
        String history_expression = (String) history.getText();
        String display_expression_text = display_expression.getText().toString();

        if(display_expression_text.matches("") && history_expression.matches("")){
            return "0";
        }
        else if(display_expression_text.matches("")){
            return backSpaceHistoryText();
        }
        else{
            return history_expression + display_expression_text;
        }

    }


    public void calculateExpression(){
        String expression_string = returnFinalExpression();

        Expression expression = new Expression(expression_string);
        String result = String.valueOf(expression.calculate());

        clearHistory();

        display_expression.setText(result);
        display_expression.setSelection(display_expression.getText().length());
    }

    class DisplayValue implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Button button_view = (Button) v;
            updateText((String) button_view.getText());
        }
    }

    class AppendHistory implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Button button_view = (Button) v;
            updateHistory((String) button_view.getText());
        }
    }
}


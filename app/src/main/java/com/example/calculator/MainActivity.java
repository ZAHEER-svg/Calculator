package com.example.calculator;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity
        implements  BasicCalculatorButtonsFragment.Listener, ScientificCalculatorFragment.Listener {

    private EditText display_expression;
    private TextView history;
    private final String BAD_EXPRESSION_MSG = "Bad Expression";


    public void disableKeyBoardSystem (){
        display_expression.setShowSoftInputOnFocus(false);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        history = findViewById(R.id.history);
        display_expression = findViewById(R.id.display_expressions);
        disableKeyBoardSystem();
    }


    public void calculatePercentage(){
        String display_text =  display_expression.getText().toString();
        Expression expression = new Expression(display_text + "%");
        String result = String.valueOf(expression.calculate());
        display_expression.setText(result);
    }


    public void updateText(String value){
        Editable oldStr = display_expression.getText();

        if(oldStr.toString().contains(BAD_EXPRESSION_MSG)){
            oldStr.clear();
        };

        int cursorPos = display_expression.getSelectionStart();
        oldStr.insert(cursorPos, value);

        display_expression.setText(oldStr);
        display_expression.setSelection(cursorPos + value.length());
    }


    public void updateHistory(String operator){
        String history_text = (String) history.getText();
        Editable display_text = display_expression.getText();

        String newStr = String.format("%s%s%s", history_text, display_text,operator);

        history.setText(newStr);
        clearText();
    }


    public void calculatePower(String power, String base){
        String expression_string = String.format("%s^%s", base, power);
        calculateToHistory(expression_string);
    }


    public String getDisplayExpression() {
        return display_expression.getText().toString();
    }


    public void updateHistoryWithFunction(String type){
        Editable display_text = display_expression.getText();
        history.setText(String.format("%s(%s)", type, display_text));
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


    public void calculateInverse(String denominator){
        String expression_string = String.format("1 / %s", denominator);
        calculateToHistory(expression_string);
    }


    public void calculatePermutation(String value){
        String expression_string = String.format("%s!", value);
        calculateToHistory(expression_string);
    }


    public void getRandomNumber() {
        clearText();
        clearHistory();

        double randNum =  Math.random();

        display_expression.setText(String.valueOf(randNum));
    }


    public String returnFinalExpression(){
        String history_expression = ((String) history.getText())
                .replace("log", "lg")
                .replace(getString(R.string.inverse_cos_btn_text), "arccos")
                .replace(getString(R.string.inverse_tan_btn_text), "arctan")
                .replace(getString(R.string.inverse_sin_btn_text), "arcsin");


        String display_expression_text = display_expression.getText().toString();


        if(display_expression_text.matches("") && history_expression.matches("")){
            return "0";
        }
        else{
            return history_expression.toString() + display_expression_text.toString();
        }

    }

    public void calculateRoot(String nthRoot, String value){
        String expression_string = String.format("%s√%s", nthRoot, value);

        calculateToHistory(expression_string);
    }


    public String calculateExpression(String expression_string){
        Expression expression = new Expression(expression_string);
        String result = String.valueOf(expression.calculate());

        Log.i("final_expression", expression_string);

        if(result.equals("NaN")) result = BAD_EXPRESSION_MSG;

        return result;
    }


    public void calculateToDisplay(String expression_string){
        String result = calculateExpression(expression_string);

        clearHistory();
        display_expression.setText(result);
        display_expression.setSelection(display_expression.getText().length());
    }


    public void calculateToHistory(String expression_string){
        String result = calculateExpression(expression_string);

        clearText();

        updateHistory(result);
    }
}


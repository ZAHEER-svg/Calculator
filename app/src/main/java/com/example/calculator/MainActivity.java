package com.example.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;


enum CalculatorMode{
    RadianMode,
    DegreeMode
}

public class MainActivity extends AppCompatActivity
        implements  BasicCalculatorButtonsFragment.Listener, ScientificCalculatorFragment.Listener {
    Button History;

    private TextView display_expression;
    private TextView history;
    private final String BAD_EXPRESSION_MSG = "Bad Expression";
    private final String HISTORY_TEXT_KEY = "historyText";
    private final String DISPLAY_TEXT_KEY = "displayExpression";
    private CalculatorMode currentCalculatorMode;

    private Activity view;


    public void disableKeyBoardSystem (){
        display_expression.setShowSoftInputOnFocus(false);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setDegreeMode();


        history = findViewById(R.id.history);
        display_expression = findViewById(R.id.display_expressions);
        disableKeyBoardSystem();

        if(savedInstanceState != null){
            String historyText = savedInstanceState.getString(HISTORY_TEXT_KEY);
            String displayExpress = savedInstanceState.getString(DISPLAY_TEXT_KEY);

            history.setText(historyText);
            display_expression.setText(displayExpress);
        }



    }

    private void startActivityFromFragment(Intent intent) {
        History = findViewById(R.id.History);
        History.setOnClickListener(view -> startActivityFromFragment(new Intent(MainActivity .this , History.class)));
    }

    public void toggleCalculatorMode(){
        if (currentCalculatorMode == CalculatorMode.RadianMode) setDegreeMode();
        else setRadianMode();
    }

    private void setDegreeMode(){
        mXparser.setDegreesMode();
        currentCalculatorMode = CalculatorMode.DegreeMode;
    }

    private void setRadianMode(){
        mXparser.setRadiansMode();
        currentCalculatorMode = CalculatorMode.RadianMode;
    }



    public void calculatePercentage(){
        String display_text =  display_expression.getText().toString();
        Expression expression = new Expression(display_text + "%");
        String result = String.valueOf(expression.calculate());
        display_expression.setText(result);
    }


    public void updateText(String value){
        StringBuilder oldStr =  new StringBuilder(display_expression.getText().toString());

        if(oldStr.toString().contains(BAD_EXPRESSION_MSG)){
           clearText();
        }

        oldStr.append(value);

        display_expression.setText(oldStr);
    }



    public void updateHistory(String operator){
        String history_text = (String) history.getText();
        String display_text =  display_expression.getText().toString();

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
        String oldHistoryText = history.getText().toString();
        String display_text =  display_expression.getText().toString();
        history.setText(String.format("%s%s(%s)", oldHistoryText, type, display_text));
        clearText();
    }


    public void clearText(){
        display_expression.setText("");
    }


    public void clearHistory(){
        history.setText("");
    }


    public void backSpace() {
        String str =  display_expression.getText().toString();
        if (str.length() != 0) display_expression.setText(str.subSequence(0, str.length() -1));
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
            return history_expression + display_expression_text;
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
    }


    public void calculateToHistory(String expression_string){
        String result = calculateExpression(expression_string);

        clearText();

        updateHistory(String.format("(%s)", result));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String historyText = history.getText().toString();
        String expressionText = display_expression.getText().toString();

        outState.putString(HISTORY_TEXT_KEY, historyText);
        outState.putString(DISPLAY_TEXT_KEY, expressionText);
    }
}


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
import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;
import org.w3c.dom.Text;

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

        history = (TextView) findViewById(R.id.history);
        display_expression = (EditText) findViewById(R.id.display_expressions);
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

    public void updateHistoryWithFunction(String type){
        String history_text = (String) history.getText();
        Editable display_text = display_expression.getText();

        history.setText(String.format("%s(%s)", type, display_text));
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
//        else if(display_expression_text.matches("")){
//            return backSpaceHistoryText();
//        }
        else{
            return history_expression + display_expression_text;
        }

    }


    public void calculateExpression(String expression_string){

        Expression expression = new Expression(expression_string);
        String result = String.valueOf(expression.calculate());

        if(result.equals("NaN")) result = BAD_EXPRESSION_MSG;

        clearHistory();

        display_expression.setText(result);
        display_expression.setSelection(display_expression.getText().length());
    }
}


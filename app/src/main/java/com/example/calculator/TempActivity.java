package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TempActivity extends AppCompatActivity implements  BasicCalculatorButtonsFragment.Listener, ScientificCalculatorFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing);
    }

    @Override
    public void updateText(String text) {

    }

    @Override
    public void backSpace() {

    }

    @Override
    public void clearText() {

    }

    @Override
    public void clearHistory() {

    }

    @Override
    public void updateHistoryWithFunction(String type) {

    }

    @Override
    public void updateHistory(String text) {

    }

    @Override
    public void calculatePower(String power, String base) {

    }

    @Override
    public String getDisplayExpression() {
        return null;
    }

    @Override
    public void calculateInverse(String denominator) {

    }

    @Override
    public void calculatePermutation(String value) {

    }

    @Override
    public void getRandomNumber() {

    }

    @Override
    public void calculateRoot(String nthRoot, String value) {

    }

    @Override
    public void calculatePercentage() {

    }

    @Override
    public void calculateToDisplay(String expression_string) {

    }

    @Override
    public String returnFinalExpression() {
        return null;
    }
}
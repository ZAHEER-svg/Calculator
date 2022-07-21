package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends Activity {

    private EditText display_expression;

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
    Button equal;
    Button reset;
    EditText data;

    public void addListenerToButtons(){
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
        reset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                clearText("");
            }
        });
    }




    public void disableKeyBoardSystem (){
        display_expression.setShowSoftInputOnFocus(false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        addListenerToButtons();

        display_expression = (EditText) findViewById(R.id.display_expressions);
        disableKeyBoardSystem();
    }


    public void updateText(String value){
        Editable oldStr = display_expression.getText();

        display_expression.setText(oldStr + value);
    }

    public void clearText(String value){
        display_expression.setText("");
    }


    class DisplayValue implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Button button_view = (Button) v;
            updateText((String) button_view.getText());
        }
    }
}


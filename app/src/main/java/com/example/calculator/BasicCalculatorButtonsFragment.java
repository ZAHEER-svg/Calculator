package com.example.calculator;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BasicCalculatorButtonsFragment extends Fragment {

    public interface Listener{
        void displayValue();

        void updateText(String text);
    }

    private Listener listener;


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
        Button caret;
        Button percentage_btn;

        View view = getView();

        zero = view.findViewById(R.id.zero_btn);
        zero.setOnClickListener(new DisplayValue());

//        one = view.findViewById(R.id.one_btn);
//        one.setOnClickListener(new MainActivity.DisplayValue());
//
//        two = view.findViewById(R.id.two_btn);
//        two.setOnClickListener();
//
//        three = view.findViewById(R.id.three_btn);
//        three.setOnClickListener();
//
//        four = view.findViewById(R.id.four_btn);
//        four.setOnClickListener();
//
//        five = view.findViewById(R.id.five_btn);
//        five.setOnClickListener();
//
//        six = view.findViewById(R.id.six_btn);
//        six.setOnClickListener();
//
//        seven = view.findViewById(R.id.seven_btn);
//        seven.setOnClickListener();
//
//        eight = view.findViewById(R.id.eight_btn);
//        eight.setOnClickListener();
//
//        nine = view.findViewById(R.id.nine_btn);
//        nine.setOnClickListener(new MainActivity.DisplayValue());

//        reset = view.findViewById(R.id.reset);
//        reset.setOnClickListener(v -> backSpace());
//        reset.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                clearText();
//                clearHistory();
//                return true;
//            }
//        });
//
//        plus = view.findViewById(R.id.plus);
//        plus.setOnClickListener(new MainActivity.AppendHistory());
//
//        minus = view.findViewById(R.id.minus);
//        minus.setOnClickListener(new MainActivity.AppendHistory());
//
//        divide = view.findViewById(R.id.divide_btn);
//        divide.setOnClickListener(new MainActivity.AppendHistory());
//
//        multiply = findViewById(R.id.multiply);
//        multiply.setOnClickListener(new MainActivity.AppendHistory());
//
//        dot = view.findViewById(R.id.dot_btn);
//        dot.setOnClickListener(new MainActivity.DisplayValue());
//
//        equal = view.findViewById(R.id.equals);
//        equal.setOnClickListener(v -> calculateExpression());
//
//        caret = view.findViewById(R.id.caret);
//        caret.setOnClickListener(new MainActivity.AppendHistory());
//
//        percentage_btn = view.findViewById(R.id.percentage_btn);
//        percentage_btn.setOnClickListener(v -> calculatePercentage());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic_calculator_buttons, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (Listener) context;
    }

    private class DisplayValue implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Button button_view = (Button) v;
            listener.updateText((String) button_view.getText());
        }
    }
}
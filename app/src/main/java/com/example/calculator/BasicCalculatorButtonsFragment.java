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
        void updateText(String text);

        void backSpace();

        void clearText();

        void clearHistory();

        void updateHistory(String text);

        void calculatePercentage();

        void calculateToDisplay(String expression_string);

        String returnFinalExpression();
    }

    private Listener listener;


    public void addListenerToButtons(View view){
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

        zero = view.findViewById(R.id.zero_btn);
        zero.setOnClickListener(new DisplayValue());

        one = view.findViewById(R.id.one_btn);
        one.setOnClickListener(new DisplayValue());

        two = view.findViewById(R.id.two_btn);
        two.setOnClickListener(new DisplayValue());

        three = view.findViewById(R.id.three_btn);
        three.setOnClickListener(new DisplayValue());

        four = view.findViewById(R.id.four_btn);
        four.setOnClickListener(new DisplayValue());

        five = view.findViewById(R.id.five_btn);
        five.setOnClickListener(new DisplayValue());

        six = view.findViewById(R.id.six_btn);
        six.setOnClickListener(new DisplayValue());

        seven = view.findViewById(R.id.seven_btn);
        seven.setOnClickListener(new DisplayValue());

        eight = view.findViewById(R.id.eight_btn);
        eight.setOnClickListener(new DisplayValue());

        nine = view.findViewById(R.id.nine_btn);
        nine.setOnClickListener(new DisplayValue());

        reset = view.findViewById(R.id.reset);
        reset.setOnClickListener(v -> listener.backSpace());
        reset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.clearText();
                listener.clearHistory();
                return true;
            }
        });

        plus = view.findViewById(R.id.plus);
        plus.setOnClickListener(new AppendHistory());

        minus = view.findViewById(R.id.minus);
        minus.setOnClickListener(new AppendHistory());

        divide = view.findViewById(R.id.divide_btn);
        divide.setOnClickListener(new AppendHistory());

        multiply = view.findViewById(R.id.multiply);
        multiply.setOnClickListener(new AppendHistory());

        dot = view.findViewById(R.id.dot_btn);
        dot.setOnClickListener(new DisplayValue());

        equal = view.findViewById(R.id.equals);
        equal.setOnClickListener(v -> listener.calculateToDisplay(listener.returnFinalExpression()));

        caret = view.findViewById(R.id.caret);
        caret.setOnClickListener(new AppendHistory());

        percentage_btn = view.findViewById(R.id.percentage_btn);
        percentage_btn.setOnClickListener(v -> listener.calculatePercentage());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_calculator_buttons, container, false);
        addListenerToButtons(view);

        return view;
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

    private class AppendHistory implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Button button_view = (Button) v;
            listener.updateHistory((String) button_view.getText());
        }
    }
}
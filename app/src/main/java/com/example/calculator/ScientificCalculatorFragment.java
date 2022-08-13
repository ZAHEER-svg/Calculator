package com.example.calculator;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ScientificCalculatorFragment extends Fragment {

    public interface Listener{
        void updateHistoryWithFunction(String type);
    }

    private Listener listener;

    private Button sinBtn;

    public void addListenersToButton(View view){
        sinBtn = view.findViewById(R.id.sin_btn);
        sinBtn.setOnClickListener(new MathFunctionClicked());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_scientific_calculator, container, false);
        addListenersToButton(view);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (Listener) context;
    }

    public class MathFunctionClicked implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            listener.updateHistoryWithFunction((String) button.getText());
        }
    }
}
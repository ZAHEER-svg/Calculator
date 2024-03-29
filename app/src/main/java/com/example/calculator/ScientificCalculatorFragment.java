package com.example.calculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ScientificCalculatorFragment extends Fragment {


    public interface Listener{
        void updateHistoryWithFunction(String type);
        void updateHistory(String operator);
        void calculatePower(String power, String base);
        String getDisplayExpression();
        void calculateInverse(String denominator);
        void calculatePermutation(String value);
        void getRandomNumber();
        void calculateRoot(String nthRoot, String value);
        void toggleCalculatorMode();
    }

    private Listener listener;



    public void addListenersToButton(@NonNull View view){
        Button sinBtn;
        Button cosBtn;
        Button tanBtn;
        Button invSinBtn;
        Button invCosBtn;
        Button invTanBtn;
        Button piBtn;
        Button logBtn;
        Button squareBtn;
        Button cubeBtn;
        Button exponentialToPowerBtn;
        Button tenToPowerBtn;
        Button inverseOfOneBtn;
        Button squareRootBtn;
        Button cubeRootBtn;
        Button nthRootBtn;
        Button permutationBtn;
        Button exponentialConstBtn;
        Button randConstBtn;
        Button twoToPowerBtn;


        sinBtn = view.findViewById(R.id.sin_btn);
        sinBtn.setOnClickListener(new MathFunctionClicked());

        cosBtn = view.findViewById(R.id.cos_btn);
        cosBtn.setOnClickListener(new MathFunctionClicked());


        tanBtn = view.findViewById(R.id.tan_btn);
        tanBtn.setOnClickListener(new MathFunctionClicked());


        invCosBtn = view.findViewById(R.id.inverse_cos_btn);
        invCosBtn.setOnClickListener(v -> listener.updateHistoryWithFunction(getString(R.string.inverse_cos_btn_text)));


        invSinBtn = view.findViewById(R.id.inverse_sin_btn);
        invSinBtn.setOnClickListener(v -> listener.updateHistoryWithFunction(getString(R.string.inverse_sin_btn_text)));

        invTanBtn = view.findViewById(R.id.inverse_tan_btn);
        invTanBtn.setOnClickListener(v -> listener.updateHistoryWithFunction(getString(R.string.inverse_tan_btn_text)));


        piBtn = view.findViewById(R.id.pi_btn);
        piBtn.setOnClickListener(v -> listener.updateHistory("pi"));


        logBtn = view.findViewById(R.id.log_btn);
        logBtn.setOnClickListener(new MathFunctionClicked());


        squareBtn = view.findViewById(R.id.square_btn);
        squareBtn.setOnClickListener(v -> listener.calculatePower("2", listener.getDisplayExpression()));

        cubeBtn = view.findViewById(R.id.cube_btn);
        cubeBtn.setOnClickListener(v -> listener.calculatePower("3", listener.getDisplayExpression()));

        exponentialToPowerBtn = view.findViewById(R.id.exponential_to_power_btn);
        exponentialToPowerBtn.setOnClickListener(v -> listener.calculatePower(listener.getDisplayExpression(), "e"));


        tenToPowerBtn = view.findViewById(R.id.ten_to_power_btn);
        tenToPowerBtn.setOnClickListener(v -> listener.calculatePower(listener.getDisplayExpression(), "10"));


        inverseOfOneBtn = view.findViewById(R.id.inverse_of_one_btn);
        inverseOfOneBtn.setOnClickListener(v -> listener.calculateInverse(listener.getDisplayExpression()));


        squareRootBtn = view.findViewById(R.id.square_root_btn);
        squareRootBtn.setOnClickListener(v -> listener.calculateRoot("2", listener.getDisplayExpression()));


        cubeRootBtn = view.findViewById(R.id.cube_root_btn);
        cubeRootBtn.setOnClickListener(v -> listener.calculateRoot("3", listener.getDisplayExpression()));


        nthRootBtn = view.findViewById(R.id.nth_root_btn);
        nthRootBtn.setOnClickListener(v -> listener.updateHistory("√"));

        permutationBtn = view.findViewById(R.id.permutation_btn);
        permutationBtn.setOnClickListener(v -> listener.calculatePermutation(listener.getDisplayExpression()));


        randConstBtn = view.findViewById(R.id.rand_num_btn);
        randConstBtn.setOnClickListener(v -> listener.getRandomNumber());

        exponentialConstBtn = view.findViewById(R.id.exponential_const_btn);
        exponentialConstBtn.setOnClickListener(v -> listener.updateHistory("e"));

        twoToPowerBtn = view.findViewById(R.id.two_to_power_btn);
        twoToPowerBtn.setOnClickListener(v -> listener.calculatePower(listener.getDisplayExpression(), "2"));

        ToggleButton modeToggle = view.findViewById(R.id.mode_toggle_btn);
        modeToggle.setOnCheckedChangeListener((buttonView, isChecked) -> listener.toggleCalculatorMode());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.bottom, container, false);
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
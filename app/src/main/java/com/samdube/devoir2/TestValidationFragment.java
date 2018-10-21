package com.samdube.devoir2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.samdube.devoir2.UtilsCommand.*;
import static com.samdube.devoir2.UtilsCommand.VerificationCommand.Entre;
import static com.samdube.devoir2.UtilsCommand.VerificationCommand.EstVide;
import static com.samdube.devoir2.UtilsCommand.VerificationCommand.Maximum;
import static com.samdube.devoir2.UtilsCommand.VerificationCommand.Minimum;
import static com.samdube.devoir2.UtilsCommand.VerificationCommand.MinimumChiffre;
import static com.samdube.devoir2.UtilsCommand.VerificationCommand.MinimumMajuscule;

public class TestValidationFragment extends Fragment implements View.OnClickListener {

    private EditText InputValidation;
    private EditText InputMaximum;
    private EditText InputMinimum;
    private TextView ConsoleValidation;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.test_validation_fragment, container, false);

        InputValidation     = view.findViewById(R.id.inputValidation);
        InputMinimum        = view.findViewById(R.id.inputMinimum);
        InputMaximum        = view.findViewById(R.id.inputMaximum);
        ConsoleValidation   = view.findViewById(R.id.textValidation);

        Button btnMinimum = view.findViewById(R.id.btnMinimum);
        Button btnMaximum = view.findViewById(R.id.btnMaximum);
        Button btnEntre = view.findViewById(R.id.btnEntre);
        Button btnMinimumMaj = view.findViewById(R.id.btnMinMaj);
        Button btnMinimumChiffre = view.findViewById(R.id.btnMinDigit);
        Button btnEstVide = view.findViewById(R.id.btnVide);

        btnMinimum.setOnClickListener(this);
        btnMaximum.setOnClickListener(this);
        btnEntre.setOnClickListener(this);
        btnMinimumMaj.setOnClickListener(this);
        btnMinimumChiffre.setOnClickListener(this);
        btnEstVide.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMinimum:
                Verification(Minimum);
                break;
            case R.id.btnMaximum:
                Verification(Maximum);
                break;
            case R.id.btnEntre:
                Verification(Entre);
                break;
            case R.id.btnMinMaj:
                Verification(MinimumMajuscule);
                break;
            case R.id.btnMinDigit:
                Verification(MinimumChiffre);
            break;
            case R.id.btnVide:
                Verification(EstVide);
                break;
        }
    }

    public void Verification(VerificationCommand command)
    {
        String input = InputValidation.getText().toString();
        Integer minimum = (!InputMinimum.getText().toString().isEmpty())? Integer.valueOf(InputMinimum.getText().toString()): 5;
        Integer maximum = (!InputMaximum.getText().toString().isEmpty())? Integer.valueOf(InputMaximum.getText().toString()): 10;
        Boolean DidVerificationPassed = false;

        switch (command)
        {
            case Minimum:
                DidVerificationPassed = Minimum(input, minimum);
                break;
            case Maximum:
                DidVerificationPassed = Maximum(input, maximum);
                break;
            case Entre:
                DidVerificationPassed = Entre(input, minimum, maximum);
                break;
            case MinimumChiffre:
                DidVerificationPassed = MiniumChiffre(input, minimum);
                break;
            case MinimumMajuscule:
                DidVerificationPassed = MinimumMajuscule(input, minimum);
                break;
            case EstVide:
                DidVerificationPassed = EstVide(input);
                break;
        }
        if(DidVerificationPassed)
        {
            ConsoleValidation.setTextColor(Color.BLUE);
            ConsoleValidation.setText("Vérification " + command + " réussi.");
        }
        else{
            ConsoleValidation.setTextColor(Color.RED);
            ConsoleValidation.setText("Vérification " + command + " échoué.");
        }
    }
}

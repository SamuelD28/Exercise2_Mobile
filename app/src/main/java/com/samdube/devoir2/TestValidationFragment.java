package com.samdube.devoir2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static com.samdube.devoir2.UtilsCommand.Entre;
import static com.samdube.devoir2.UtilsCommand.EstVide;
import static com.samdube.devoir2.UtilsCommand.Maximum;
import static com.samdube.devoir2.UtilsCommand.Minimum;
import static com.samdube.devoir2.UtilsCommand.MinimumMajuscule;
import static com.samdube.devoir2.UtilsCommand.MiniumChiffre;
import static com.samdube.devoir2.UtilsCommand.VerificationCommand;

//We implement the OnClickListener in the class to simplify the code. No need to create innerclass for each button listener
public class TestValidationFragment extends Fragment implements View.OnClickListener {

    private EditText InputValidation;
    private EditText InputMaximum;
    private EditText InputMinimum;
    private TextView ConsoleValidation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test_validation_fragment, container, false);

        //Input Elements
        //Use to store all the button. Creates a lighter code in the onCreate method
        ArrayList<Button> listeBtnCommand = new ArrayList<>();
        InputValidation = view.findViewById(R.id.inputValidation);
        InputMinimum = view.findViewById(R.id.inputMinimum);
        InputMaximum = view.findViewById(R.id.inputMaximum);
        ConsoleValidation = view.findViewById(R.id.textValidation);

        //Command Button
        listeBtnCommand.add(view.findViewById(R.id.btnMinimum));
        listeBtnCommand.add(view.findViewById(R.id.btnMaximum));
        listeBtnCommand.add(view.findViewById(R.id.btnEntre));
        listeBtnCommand.add(view.findViewById(R.id.btnMinMaj));
        listeBtnCommand.add(view.findViewById(R.id.btnMinDigit));
        listeBtnCommand.add(view.findViewById(R.id.btnVide));

        //Set the onClickListener for all the btn
        for (Button btn : listeBtnCommand)
            btn.setOnClickListener(this);

        return view;
    }

    //Method that execute the command linked with the button.
    //It uses the tag attribute on the button to determine which command to perform.
    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        try {
            //Throws an error if the command doesnt exist
            VerificationCommand command = VerificationCommand.valueOf(btn.getTag().toString().toLowerCase());
            EffectuerVerification(command);
        } catch (Exception e) {
            AfficherMessageConsole("Assurez-vous d'assigner une commande au bouton", false);
        }
    }

    //Method that perform the required verification on the input string
    private void EffectuerVerification(VerificationCommand command) {
        //Initialize the maximum and minimum in case we dont use the inputs
        Integer minimum = (!InputMinimum.getText().toString().isEmpty()) ? Integer.valueOf(InputMinimum.getText().toString()) : 5;
        Integer maximum = (!InputMaximum.getText().toString().isEmpty()) ? Integer.valueOf(InputMaximum.getText().toString()) : 10;
        String input = InputValidation.getText().toString();
        //Keeps informations about the verification results
        Boolean DidVerificationPassed;
        String message = String.valueOf(command);
        try {
            switch (command) {
                case minimum:
                    DidVerificationPassed = Minimum(input, minimum);
                    message = message.concat(" {" + minimum + "}");
                    break;
                case maximum:
                    DidVerificationPassed = Maximum(input, maximum);
                    message = message.concat(" {" + maximum + "}");
                    break;
                case entre:
                    DidVerificationPassed = Entre(input, minimum, maximum);
                    message = message.concat(" {" + minimum + "-" + maximum + "}");
                    break;
                case minimumchiffre:
                    DidVerificationPassed = MiniumChiffre(input, minimum);
                    message = message.concat(" {" + minimum + "}");
                    break;
                case minimummajuscule:
                    DidVerificationPassed = MinimumMajuscule(input, minimum);
                    message = message.concat(" {" + minimum + "}");
                    break;
                case estvide:
                    DidVerificationPassed = EstVide(input);
                    break;
                default:
                    throw new IllegalArgumentException("La commande n'est pas disponible");
            }
            AfficherMessageConsole(message, DidVerificationPassed);
        } catch (Exception e) {
            AfficherMessageConsole(e.getMessage(), false);
        }
    }

    //Method that display a message inside the textview over the input validation
    private void AfficherMessageConsole(String message, Boolean success) {
        if (success) {
            ConsoleValidation.setTextColor(Color.BLUE);
            ConsoleValidation.setText(message);
        } else {
            ConsoleValidation.setTextColor(Color.RED);
            ConsoleValidation.setText(message);
        }
    }
}

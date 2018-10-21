package com.samdube.devoir2;

//Interface that comes with command enum for performing various task on a input string
public interface UtilsCommand {

    //Method that verify that the string length is superior to the minimum specified
    static Boolean Minimum(String texte, Integer minimum) {
        return (texte.length() >= minimum);
    }

    //Method that verify that the string lenght in inferior to the specified maximum
    static Boolean Maximum(String texte, Integer maximum) {
        return (texte.length() <= maximum);
    }

    //Method that verify that the strin lenght is in between a maximum and minimum
    static Boolean Entre(String texte, int minimum, int maximum) {
        return (Minimum(texte, minimum) && Maximum(texte, maximum));
    }

    //Method that verify that the string contains a specified minimum of caps letter
    static Boolean MinimumMajuscule(String texte, Integer minimum) {
        int nombreMajuscule = 0;
        for (int i = 0; i < texte.length(); i++) {
            if (Character.isUpperCase(texte.charAt(i)))
                nombreMajuscule++;
        }
        return (nombreMajuscule >= minimum);
    }

    //Method that verify if the string is empty
    static Boolean EstVide(String texte) {
        return (texte.isEmpty());
    }

    //Method that verify that the string contains a specfied minimum of digits
    static Boolean MiniumChiffre(String texte, Integer minimum) {
        int nombreChiffre = 0;
        for (int i = 0; i < texte.length(); i++) {
            if (Character.isDigit(texte.charAt(i)))
                nombreChiffre++;
        }
        return (nombreChiffre >= minimum);
    }

    //Enum used to perform commands
    enum VerificationCommand {
        minimum,
        maximum,
        entre,
        minimummajuscule,
        minimumchiffre,
        estvide
    }
}

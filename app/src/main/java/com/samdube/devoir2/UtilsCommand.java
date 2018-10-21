package com.samdube.devoir2;

public interface UtilsCommand{

    static Boolean Minimum(String texte, Integer minimum)
    {
        return (texte.length() >= minimum);
    }

    static Boolean Maximum(String texte, Integer maximum)
    {
        return (texte.length() <= maximum);
    }

    static Boolean Entre(String texte, int minimum, int maximum)
    {
        return (Minimum(texte, minimum) && Maximum(texte, maximum));
    }

    static Boolean MinimumMajuscule(String texte, Integer minimum)
    {
        int nombreMajuscule = 0;
        for(int i = 0 ; i < texte.length(); i++)
        {
            if(Character.isUpperCase(texte.charAt(i)))
                nombreMajuscule++;
        }
        return (nombreMajuscule >= minimum);
    }

    static Boolean EstVide(String texte)
    {
        return (texte.isEmpty());
    }

    static Boolean MiniumChiffre(String texte, Integer minimum)
    {
        int nombreChiffre = 0;
        for(int i = 0 ; i < texte.length(); i++)
        {
            if(Character.isDigit(texte.charAt(i)))
                nombreChiffre++;
        }
        return (nombreChiffre >= minimum);
    }

    enum VerificationCommand{
        Minimum,
        Maximum,
        Entre,
        MinimumMajuscule,
        MinimumChiffre,
        EstVide
    }
}

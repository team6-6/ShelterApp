package com.example.example;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

public class Checkfunction {

    public int notEmpty(String string){
        if(string.equals("")){
            return 1;
        }
        return 0;
    }

    public boolean RangeValues(int num1, int num2, String string){

        if(string.length()<num1 || string.length()>num2){
            return true;
        }
        return false;

    }

    public boolean RangeValue(int num1, String string){

        if(string.length()<num1){
            return true;
        }
        return false;

    }

    public String ParseToDouble(String string){

        if (string == null) {
            return "null";
        }

        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return "exception";
        }
        return "true";

    }


    public boolean CheckPermission(String string){

        if (!string.equals("A") && !string.equals("B") && !string.equals("C")){
               return true;
        }
        return false;
    }

    public boolean CheckPassword(Pattern pattern, String string){

        if (pattern.matcher(string).find()){
            return true;
        }
        return false;
    }


    public boolean validName(String string){

        Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        if (!digitCasePatten.matcher(string).find()){
            return true;
        }
        return false;
    }


    public boolean SamePassword(String password1, String password2){

        if (password1.equals(password2)){
            return true;
        }
        return false;
    }

}

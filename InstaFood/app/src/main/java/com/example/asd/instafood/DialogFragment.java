package com.example.asd.instafood;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

public class DialogFragment extends android.support.v4.app.DialogFragment {

    public  final static String ARGUMENTO_TITLE_= "TITLE";
    public  final static String ARGUMENTO_FULL_SNIPPET_= "FULL_SNIPPET";
    private String title;
    private String fullSnipper;




    public static DialogFragment newInstance(String title, String fullSnipper)
    {
        DialogFragment df=new DialogFragment();
        Bundle bundle= new Bundle();
        bundle.putString(ARGUMENTO_TITLE_,title);
        bundle.putString(ARGUMENTO_FULL_SNIPPET_,fullSnipper);
        df.setArguments(bundle);
        return df;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();

        title=bundle.getString(ARGUMENTO_TITLE_);
        fullSnipper=bundle.getString(ARGUMENTO_FULL_SNIPPET_);
    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState)
    {
        Dialog dialog= new AlertDialog.Builder(getActivity()).setTitle(title).setMessage(fullSnipper).create();
        return dialog;
    }
}

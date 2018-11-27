package com.example.taf.taf_bm.helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.example.taf.taf_bm.R;
import com.example.taf.taf_bm.activity.TafActivity;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;

public class SetUpViews  {

    public void spinnerAdapter(Context context, NiceSpinner spinner, ArrayList<Integer> array){
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(context,  android.R.layout.simple_spinner_dropdown_item, array);
        spinner.setAdapter(adapter);
    }

    public void dialog(String concept, final Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyDialogTheme);
        builder.setTitle("Resultados");
        builder.setMessage(concept);
        String positiveText = "OK";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void refresh(Class fragmentClass, String title, int type, FragmentManager manager){
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            bundle.putString("test",title);
            bundle.putInt("test_type",type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            fragment.setArguments(bundle);
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.main_content_fragment, fragment);
            ft.commit();
        }
    }
}
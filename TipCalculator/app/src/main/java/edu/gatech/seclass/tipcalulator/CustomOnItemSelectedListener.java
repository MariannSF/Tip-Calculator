package edu.gatech.seclass.tipcalulator;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by maria on 10/11/2017.
 */
public class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
       /* Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}

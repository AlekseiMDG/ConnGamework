package com.OshurkovAlekseiDevelopment.conngame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ActivityInstruction extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction);

        Typeface tapeface = Typeface.createFromAsset(getAssets(), "fonts/comissans.ttf");

        TextView txtFisrt = (TextView) findViewById(R.id.txtFirstWord);
        txtFisrt.setTypeface(tapeface);

        TextView txtSecond = (TextView) findViewById(R.id.txtSecondWord);
        txtSecond.setTypeface(tapeface);
    }

    public void onClickBack(View view) {
        startActivity(new Intent(ActivityInstruction.this, ActivityPlaySelector.class));
    }
}

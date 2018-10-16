package com.corp.tajine.la_cacalculatrice;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by regis on 08/04/2018.
 */

public class ViewModeActivity extends Activity  implements View.OnClickListener {

    ImageButton btn0 = null;
    ImageButton btn1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmode);

        btn0 = (ImageButton) findViewById(R.id.btn_mode_00);
        btn0.setOnClickListener(this);
        btn1 = (ImageButton) findViewById(R.id.btn_mode_01);
        btn1.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_mode_00:
                cacal();
                break;

            case R.id.btn_mode_01:
                histo();
                break;

        }

    }

    public void cacal(){
        Intent intent = new Intent(this, Cacalculatrice.class);
        startActivity(intent);
    }

    public void histo(){
        Intent intent = new Intent(this, ViewCalculActivity.class);
        startActivity(intent);
    }

}

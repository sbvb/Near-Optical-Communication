package com.heinzelmann.ricardo.near_optical_communication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final int REQUEST_CODE = 0;

    TextView labelQRCode;
    Button buttonReader;
    Button buttonGenerator;

    private String scan_result;
    private String scan_format;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        labelQRCode = (TextView)v.findViewById(R.id.labelQRCode);
        buttonReader = (Button)v.findViewById(R.id.buttonReader);
        buttonReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callZXing();

            }
        });

        buttonGenerator = (Button)v.findViewById(R.id.buttonGenerator);
        buttonGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), GeneratorActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    public void callZXing(){
        Intent it = new Intent(getActivity().getBaseContext(), SimpleCameraActivity.class);
        startActivityForResult(it, REQUEST_CODE);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(REQUEST_CODE == requestCode && getActivity().RESULT_OK == resultCode){
            scan_result = data.getStringExtra("SCAN_RESULT");

            scan_format = data.getStringExtra("SCAN_FORMAT");
            labelQRCode.setText("RESULTADO: "+scan_result+" ("+scan_format+")");

        }
    }
}

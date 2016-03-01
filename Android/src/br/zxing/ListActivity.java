/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.zxing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import br.exemplozxingintegration.R;

/**
 *
 * @author Ricardo
 */
public class ListActivity extends Activity {

    private TextView txResult1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        
            setContentView(R.layout.activity_cupons);
            txResult1 = (TextView) findViewById(R.id.txResult1);
            
            Intent it = getIntent();
            String name = it.getStringExtra("my_name");
            String result_txt = it.getStringExtra("result");
            String format_txt = it.getStringExtra("format");
            
            txResult1.setText("RESULTADO: " + result_txt + " " + format_txt + " ");
        
        // ToDo add your GUI initialization code here        
    }
    
}

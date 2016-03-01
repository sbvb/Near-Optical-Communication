package br.zxing;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import br.exemplozxingintegration.R;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends Activity {
	public static final int REQUEST_CODE = 0;
	private TextView txResult;
        private String scan_result;
        private String scan_format;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txResult = (TextView) findViewById(R.id.txResult);
	}
	
	
	public void callZXing(View view){
		Intent it = new Intent(MainActivity.this, com.google.zxing.client.android.CaptureActivity.class);
                
		startActivityForResult(it, REQUEST_CODE);
	}
        
        public void listCupons(View view){
                //String name = "Dragão";
		Intent it = new Intent(MainActivity.this, ListActivity.class);
                //it.putExtra("my_name", name);
                it.putExtra("result", scan_result);
                it.putExtra("format", scan_format);
                //it.putStringArrayListExtra("loja", 0);
		startActivity(it);
	}
        
        
       	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
                        scan_result = data.getStringExtra("SCAN_RESULT");
                        
                        scan_format = data.getStringExtra("SCAN_FORMAT");
			txResult.setText("RESULTADO: "+scan_result+" ("+scan_format+")");
                        
                        
                
                        try {
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            String url = "jdbc:mysql://127.3.6.2/near_optical_communication";
                            Connection c = DriverManager.getConnection(url, "adminDPrRJ6s", "ANgNffEl_I9v");
                            PreparedStatement st = c.prepareStatement("insert into clientes values(?,?,?)");
                            st.setString(1, "McDonalds");
                            st.setString(2, "valor em reais");
                            st.setString(3, "data limite");
                            st.execute();
                            st.close();
                            c.close();
                            
                    
                
                        }   catch (SQLException ex) {
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }    
	}
}

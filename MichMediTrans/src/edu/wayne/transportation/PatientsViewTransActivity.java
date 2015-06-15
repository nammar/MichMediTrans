package edu.wayne.transportation;

import edu.wayne.michmeditrans.R;
import edu.wayne.michmeditrans.R.id;
import edu.wayne.michmeditrans.R.layout;
import edu.wayne.michmeditrans.R.menu;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class PatientsViewTransActivity extends Activity {
	
	// ----------------Declare Widget variables----------------//
		private Spinner spCompanies, spTransportType;
		private Button btSubmit;
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patients_view_trans);
		
		//Define Widget variables
		spCompanies = (Spinner) findViewById(R.id.spCompanies);
		spTransportType = (Spinner) findViewById(R.id.spTransportType);
		btSubmit = (Button) findViewById(R.id.btSubmit); 
		
		
		//Define Submit Button
		btSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
				
					
					String companyChoice = String.valueOf(spCompanies.getSelectedItem());
					String TransTypeChoice = String.valueOf(spTransportType.getSelectedItem());
					
					//alert dialog checking users input
					AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
					builder.setTitle("Transportation Choice");
					builder.setMessage("Company: " + companyChoice + "\n"
								        + "Transportation Type: " + TransTypeChoice + "\nAre You Sure?");

					//Accept user input or not-
					//start new activity
					builder.setPositiveButton("Yes",null);   ////-------Add code to start activity here in positive button-------//
					builder.setNegativeButton("No",null);
					builder.show();
					
				} catch (NumberFormatException e) {
					System.out.println("Error: A input field is blank.");
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patients_view_trans, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

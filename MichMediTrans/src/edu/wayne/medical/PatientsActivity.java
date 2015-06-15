package edu.wayne.medical;

import edu.wayne.michmeditrans.R;
import edu.wayne.michmeditrans.R.id;
import edu.wayne.michmeditrans.R.layout;
import edu.wayne.michmeditrans.R.menu;
import edu.wayne.transportation.PatientsViewTransActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PatientsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patients);
		
		Button patientTrans = (Button) findViewById(R.id.Button05);
		patientTrans.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// switching screens is done in two steps
				//1. create intent 
				Intent myIntent = new Intent(view.getContext(),PatientsViewTransActivity.class);

				//2. launch activity
				startActivity(myIntent);
				
				
			}
		
			
			
		
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patients, menu);
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

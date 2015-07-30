package edu.wayne.transportation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import edu.wayne.michmeditrans.R;

public class PatientsViewTransActivity extends FragmentActivity implements
		GoogleApiClient.OnConnectionFailedListener {

	/**
	 * GoogleApiClient wraps our service connection to Google Play Services and
	 * provides access to the user's sign in state as well as the Google's APIs.
	 */
	protected GoogleApiClient mGoogleApiClient;
	MapView mapView;
	GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patients_view_trans);

		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.enableAutoManage(this, 0, this).addApi(Places.GEO_DATA_API)
				.build();

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

	/**
	 * Called when the Activity could not connect to Google Play services and
	 * the auto manager could resolve the error automatically. In this case the
	 * API is not available and notify the user.
	 * 
	 * @param connectionResult
	 *            can be inspected to determine the cause of the failure
	 */
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {

		Log.e("patients trans activity",
				"onConnectionFailed: ConnectionResult.getErrorCode() = "
						+ connectionResult.getErrorCode());

		Toast.makeText(
				this,
				"Could not connect to Google API Client: Error "
						+ connectionResult.getErrorCode(), Toast.LENGTH_SHORT)
				.show();
	}

}

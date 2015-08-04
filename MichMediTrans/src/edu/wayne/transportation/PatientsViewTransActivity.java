package edu.wayne.transportation;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import edu.wayne.michmeditrans.R;
import edu.wayne.model.DriverData;
import edu.wayne.utils.Constants;

public class PatientsViewTransActivity extends FragmentActivity implements
		GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback,
		ConnectionCallbacks, LocationListener {

	/**
	 * GoogleApiClient wraps our service connection to Google Play Services and
	 * provides access to the user's sign in state as well as the Google's APIs.
	 */
	protected GoogleApiClient mGoogleApiClient;

	// TODO: get from a json/csv files/ database etc. But for now hard coded
	// with 3 Driver objects
	private List<DriverData> drivers = null;

	MapView mapView;
	GoogleMap map;

	private Location mLastLocation;
	LocationRequest mLocationRequest;
	protected String mAddressOutput;
	protected TextView fromText;
	protected TextView toText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_patients_view_trans);
		fromText = (TextView) findViewById(R.id.AutoCompleteTextView01);
		toText = (TextView) findViewById(R.id.autocomplete_places);
		buildGoogleApiClient();

		MapFragment mapFragment = (MapFragment) getFragmentManager()
				.findFragmentById(R.id.mapview1);
		mapFragment.getMapAsync(this);
		map = mapFragment.getMap();

		// TODO:loadSampleDrivers();

	}

	private void loadSampleDrivers() {
		// DriverData driver1 = new DriverData();
		// driver1.setLocation();
		// driver2.setName();
		// drivers.add(driver1);

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

	@Override
	public void onConnected(Bundle connectionHint) {

		mLastLocation = LocationServices.FusedLocationApi
				.getLastLocation(mGoogleApiClient);

		if (mLastLocation != null) {

			LatLng locc = new LatLng(mLastLocation.getLatitude(),
					mLastLocation.getLongitude());
			
			//Zooms into the currently detected location
			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(locc).zoom(17).bearing(90).tilt(10).build();
			map.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));
			
			//Retrieves the address corresponding to the detected location
			Geocoder geo = new Geocoder(this);
			List<Address> locs = null;
			try {
				locs = geo.getFromLocation(mLastLocation.getLatitude(),
						mLastLocation.getLongitude(), 1);
			} catch (IOException e) {

				e.printStackTrace();
			}
			Address a = locs.get(0);

			//displays detected address in from field
			fromText.setText(a.getFeatureName() + "," + a.getLocality() + ","
					+ a.getAdminArea() + " " + a.getPostalCode());

		}
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		Log.i("patients trans activity", "Connection suspended");
		mGoogleApiClient.connect();

	}

	protected void startLocationUpdates() {

		mLocationRequest.setInterval(10000);
		mLocationRequest.setFastestInterval(5000);
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		if (mGoogleApiClient.isConnected()) {
			LocationServices.FusedLocationApi.requestLocationUpdates(
					mGoogleApiClient, mLocationRequest, this);
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		mLastLocation = location;
		
	}

	protected synchronized void buildGoogleApiClient() {
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API).build();
	}

	@Override
	protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}

	class AddressResultReceiver extends ResultReceiver {
		public AddressResultReceiver(Handler handler) {
			super(handler);
		}

		@Override
		protected void onReceiveResult(int resultCode, Bundle resultData) {

			mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
			fromText.setText(mAddressOutput);

		}
	}

	@Override
	/**
	 * https://developers.google.com/maps/documentation/android/marker
	 */
	public void onMapReady(GoogleMap map) {
		// map.addMarker(new MarkerOptions().position(
		// new LatLng(
		// 42.3314, -83.0458
		// )) // TODO: ABDUL: get this from
		// // // driver location
		// .title("Nariman Ammar, Liscence plate 1234, cell 33388800, rating 5 stars"
		// // // +
		// // // driver1.getName()
		// ));
		//displays the blue circle/dot on google maps representing current user location
		map.setMyLocationEnabled(true);

	}

}

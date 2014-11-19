package com.healthmcm.controllers;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.healthmcm.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
public class FragmentCarte extends MapFragment implements LocationListener {
	private LocationManager locationManager;
	private GoogleMap gMap; 
	private SupportMapFragment map;
	private Marker marker;
	private MapView mapView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState); 
		View view =inflater.inflate(R.layout.layout_fragment_map,container, false);
		mapView = (MapView) view.findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);
		//Fragment f=getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
		gMap=mapView.getMap();
		gMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
		marker = gMap.addMarker(new MarkerOptions().title("Patient").position(new LatLng(0, 0)));
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		//Obtention de la référence du service
		locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		//Si le GPS est disponible, on s'y abonne
		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			abonnementGPS();
		}
	}
	@Override
	public void onPause() {
		super.onPause();

		//On appelle la méthode pour se désabonner
		desabonnementGPS();
	}

	/**
	 * Méthode permettant de s'abonner à la localisation par GPS.
	 */
	public void abonnementGPS() {
		//On s'abonne
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
	}

	/**
	 * Méthode permettant de se désabonner de la localisation par GPS.
	 */
	public void desabonnementGPS() {
		//Si le GPS est disponible, on s'y abonne
		locationManager.removeUpdates(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onLocationChanged(final Location location) {
		//On affiche dans un Toat la nouvelle Localisation
		final StringBuilder msg = new StringBuilder("lat : ");
		msg.append(location.getLatitude());
		msg.append( "; lng : ");
		msg.append(location.getLongitude());

		Toast.makeText(getActivity(), msg.toString(), Toast.LENGTH_SHORT).show();

		//Mise à jour des coordonnées
		final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());     
		gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
		marker.setPosition(latLng);
	}
	@Override
	public void onProviderDisabled(final String provider) {
		//Si le GPS est désactivé on se désabonne
		if("gps".equals(provider)) {
			desabonnementGPS();
		}      
	}

	@Override
	public void onProviderEnabled(final String provider) {
		//Si le GPS est activé on s'abonne
		if("gps".equals(provider)) {
			abonnementGPS();
		}
	}
	@Override
	public void onStatusChanged(final String provider, final int status, final Bundle extras) { }
}

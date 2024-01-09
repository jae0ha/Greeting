package com.emgram.greeting

import android.Manifest
import android.content.ContentProviderClient
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.emgram.greeting.databinding.ActivityMapsBinding
import com.emgram.greeting.ui.theme.GreetingTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.lang.Thread.sleep

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var mLocationPermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // FusedLocationProviderClient 초기화
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        // SupportMapFragment를 가져와서 지도가 준비되면 알림을 받습니다.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // 1. 기본 설정된 위치로 지도 위치를 설정하기
        // 지도에 표시할 마커의 위치를 추가
        val hanlasan = LatLng(33.362361, 126.533277)
        mMap.addMarker(MarkerOptions().position(hanlasan).title("Marker in hanla mt"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hanlasan))

        // 지도 확대하기
        val zoomLevel = 12f
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hanlasan, zoomLevel))
        
        // 2. FusedLocationProviderCient를 사용해 사용위치 업데이트 하기
        getLocationPermission()
    }
    
    // 실시간 사용자 위치 가져오기
    /*
     런타임 권한 요청
     안드로이드 6.0 (API레벨 23) 이상에서는 런타임에 위치 권한을
     요청해야 한다.
     */
    
    // 사용자에게 위치 권한을 요청하는 코드를 추가
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // 권한이 이미 부여된 경우
            onLocationPermissionGranted()
        } else {
            // 권한 요청
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    private fun onLocationPermissionGranted() {
        // 권한이 부여된 후에 수행할 작업을 여기에 구현
        getDeviceLocation()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 사용자가 권한을 부여한 경우
                    onLocationPermissionGranted()
                } else {
                    // 사용자가 권한을 거부한 경우
                    // 권한 거부에 대한 처리 로직
                }
                return
            }
        }
    }


    // FusedLocationProviderClient 사용 위치 업데이트
    // FusedLocationProviderClient 의 의존성을 추가해야 한다.
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

    private fun getDeviceLocation() {
        try {
            val locationResult = mFusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    // 현재 위치를 찾은 경우, 위치 정보 세팅
                    val lastKnownLocation = task.result
                    if (lastKnownLocation != null) {
                        val currentPlace = LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
                        mMap.addMarker(MarkerOptions().position(currentPlace).title("You are here!!"))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                            LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude), 8f)
                        )
                    }
                } else {
                    // 현재 위치를 찾지 못한 경우, 한라산을 찍어 줍시다.
                    val hanlasan = LatLng(33.362361, 126.533277)
                    mMap.addMarker(MarkerOptions().position(hanlasan).title("Marker in hanla mt"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hanlasan, 8f))
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }
}

package org.root.bluetooth1

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothDevice.ACTION_FOUND
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.root.bluetooth1.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bluetoothBroadcastReceiver: BluetoothReceiver

    private val bluetoothPermissionList: List<String> = buildList {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            add(Manifest.permission.BLUETOOTH_SCAN)
            add(Manifest.permission.BLUETOOTH_CONNECT)
        }
            add(Manifest.permission.ACCESS_COARSE_LOCATION)
            add(Manifest.permission.ACCESS_FINE_LOCATION)
            add(Manifest.permission.BLUETOOTH)
            add(Manifest.permission.BLUETOOTH_ADMIN)
    }

    private val requestPermissionLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionResult ->
            handleResult(permissionResult)
        }

    private val manualBluetoothTurning = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->
        if (result.resultCode == RESULT_OK) {
            Log.i(TAG, "Bluetooth turned on manually")
        } else {
            Log.i(TAG, "Bluetooth not turned on manually")
        }
    }

    private fun handleResult(permissionResult: Map<String, Boolean>?) {
            permissionResult?.forEach {
                if (it.value) {
                    Log.i(TAG, "Permission granted: ${it.key}")
                } else {
                    Log.i(TAG, "Permission not granted ${it.key}")
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bluetoothBroadcastReceiver = BluetoothReceiver()

        askForPermissions()

        doCheckConnectivity()

        binding.btnTurnOnBluettoth.setOnClickListener {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            manualBluetoothTurning.launch(enableBtIntent)
        }

    }

    @SuppressLint("MissingPermission")
    private fun doCheckConnectivity() {

//        if( bluetoothAdapter == null ) {
//            Log.w(TAG, "Bluetooth not supported on this device, quitting!" )
//            Toast.makeText(this, "Bluetooth is not supported in this device, sorry!!", Toast.LENGTH_SHORT).show()
//        }

        val pairedDevices: MutableSet<BluetoothDevice>? = bluetoothAdapter.bondedDevices
        pairedDevices?.forEach { device ->
            val deviceName = device.name
            val deviceMacAddress = device.address
            val bonded = device.bondState.toBondState()
            Log.i(TAG, "Paired Devices")
            Log.i(TAG, "Name: $deviceName, MacAddress: $deviceMacAddress, pairedState: $bonded")
        }

        binding.btnSearchDevices.setOnClickListener {
            registerAndStartListening()
        }

        }

    @SuppressLint("MissingPermission")
    private fun registerAndStartListening() {
        val filter = IntentFilter().apply {
            addAction(ACTION_FOUND)
            addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
            addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        }
        registerReceiver(bluetoothBroadcastReceiver, filter)
        bluetoothAdapter.startDiscovery()
    }

    private fun askForPermissions() {
        val message =
            "Permissions are required for proper functioning of the application. Please grant these permissions."
        val permissionsToRequest = mutableListOf<String>()

        bluetoothPermissionList.forEach { permission ->
            bluetoothPermissionList.filterTo(permissionsToRequest) { !isPermissionGranted(permission) }
        }
        if (permissionsToRequest.isEmpty()) {
            Log.i(TAG, "All permissions are granted!Q")
        } else {
            val shouldShowRationale = permissionsToRequest.any { permission ->
                ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
            }

            if (shouldShowRationale) {
                AlertDialog.Builder(this)
                    .setMessage(message)
                    .setTitle("Permissions Needed!!")
                    .setPositiveButton("Yes") { _, _ ->
                        requestPermissionLauncher.launch(permissionsToRequest.toTypedArray())
                    }
                    .setNegativeButton("No") { dialogue, _ ->
                        dialogue.dismiss()
                    }.create().show()
            } else {
                requestPermissionLauncher.launch(permissionsToRequest.toTypedArray())
            }
        }
    }

    private fun isPermissionGranted(permission: String) =
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED


    private fun allPermissionsGranted(): Boolean =
        bluetoothPermissionList.all {  permission ->
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        }

    companion object {
        val Context.bluetoothAdapter: BluetoothAdapter
            get() = (getSystemService(BLUETOOTH_SERVICE) as BluetoothManager).adapter

        fun Int.toBondState() = when(this) {
            10 -> PairedState.UNPAIRED
            11 -> PairedState.PAIRING
            12 -> PairedState.PAIRED
            else -> null
        }
    }
}

enum class PairedState {
    UNPAIRED, PAIRED, PAIRING
}








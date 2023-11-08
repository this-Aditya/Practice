package org.root.bluetooth1

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import org.root.bluetooth1.MainActivity.Companion.bluetoothAdapter

private const val TAG = "BluetoothReceiver"

class BluetoothReceiver : BroadcastReceiver() {

    private var numberOfDevices = 0

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
//        Log.i(TAG, "OnReceive: Action: ${intent?.action} ")
        val action = intent?.action ?: return
        val bluetoothAdapter = context?.bluetoothAdapter

        when (action) {
            BluetoothDevice.ACTION_FOUND -> {
                numberOfDevices++
                val bondedDevices = bluetoothAdapter?.bondedDevices
                Log.i(TAG, "New device found: Number of devices = $numberOfDevices. Also Bonded Devices: ${bondedDevices?.size}")
                val device: BluetoothDevice? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)
                } else {
                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                }
                device?.let { _ ->
                    Log.i(
                        TAG,
                        "New device Added-> Name: ${device.name}, Mac: ${device.address}, bluetoothClass: ${device.bluetoothClass},"
                    )
                }
                Log.i(TAG, "")
                Log.i(TAG, "Bonded Devices List: ")
                bondedDevices?.forEach {
                    Log.i(TAG, "Name: ${it.name}, Mac: ${it.address}, Class: ${it.bluetoothClass}, Type: ${it.type}, BoundState: ${it.bondState}")
                }
            }
            BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
                Log.d(TAG, "Bluetooth Discovery has started!!")
            }
            BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                Log.d(TAG, "Bluetooth discovery finished!!")
                context?.unregisterReceiver(this)
            }
        }

    }
}
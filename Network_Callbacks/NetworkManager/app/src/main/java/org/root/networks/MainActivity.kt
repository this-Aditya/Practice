package org.root.networks

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.root.networks.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val connectivityManager =
            getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val currentNetwork: Network? = connectivityManager?.activeNetwork
        val capabilities: NetworkCapabilities? =
            connectivityManager?.getNetworkCapabilities(currentNetwork)
        val linkProperties = connectivityManager?.getLinkProperties(currentNetwork)

        val callbacks = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.e(TAG, "(onAvailable) The default network is now: $network")
            }

            override fun onLost(network: Network) {
                Log.e(
                    TAG,
                    "(onLost) The application no longer has a default network. The last default network was $network"
                )
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities,
            ) {
                Log.e(TAG, "(onCapabilitiesChanged) The default network changed capabilities: $networkCapabilities")
            }

            override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
                Log.e(TAG, "(onLinkPropertiesChanged) The default network changed link properties: $linkProperties")
            }
        }

        val request = NetworkRequest.Builder().build()

        connectivityManager?.registerNetworkCallback(request, callbacks)
    }
}
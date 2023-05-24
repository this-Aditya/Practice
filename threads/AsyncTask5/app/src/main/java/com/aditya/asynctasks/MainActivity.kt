package com.aditya.asynctasks

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.aditya.asynctasks.databinding.ActivityMainBinding
import java.lang.ref.WeakReference

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            startAsyncTask()
        }

    }

    fun startAsyncTask() {
        val myAyncTask = MyAsyncTasks(this)
        myAyncTask.execute(14)
    }

    companion object {
        class MyAsyncTasks(activity: MainActivity) : AsyncTask<Int, Int, String>() {
            private var activityWeakReference: WeakReference<MainActivity>

            init {
                activityWeakReference = WeakReference(activity)
            }

            //Execute before doInBackground. This works on UI thread.
            override fun onPreExecute() {
                super.onPreExecute()
                //activity variable stores a strong reference but it is in the scope of the function.
                // So this scope won't run longer.
                val activity: MainActivity = activityWeakReference.get()!!
                activity.binding.progressBar.visibility = View.VISIBLE
            }

            //Only this method works in background thread.
            override fun doInBackground(vararg params: Int?): String {
                for (i in 1..params[0]!!) {
                    Log.i(TAG, "Happening in background $i")
                    publishProgress((i * 100) / params[0]!!)
                    Thread.sleep(1000)
                }
                return "Task Completed"
            }

            // Updates the progress
            override fun onProgressUpdate(vararg values: Int?) {
                super.onProgressUpdate(*values)
                val activity: MainActivity = activityWeakReference.get()!!
                activity.binding.progressBar.setProgress(values[0]!!)
            }

            // Can publish the result on UI
            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                val activity: MainActivity = activityWeakReference.get()!!
                activity.binding.progressBar.setProgress(0)
                activity.binding.progressBar.visibility = View.INVISIBLE
                Toast.makeText(activity, "AsyncTask Completed.", Toast.LENGTH_LONG).show()
            }


        }
    }
}
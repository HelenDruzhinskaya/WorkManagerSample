package ru.myitacademy.samsung.workmanagersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pProc = PeriodicWorkRequestBuilder<Woker1>(3,TimeUnit.MINUTES).addTag("song").build()
        val proc2 = OneTimeWorkRequest.Builder(Worker2::class.java).build()
        val proc3 = OneTimeWorkRequestBuilder<Worker3>().build()
        val proc4 = OneTimeWorkRequestBuilder<Worker4>().build()
        val proc5 = OneTimeWorkRequestBuilder<Worker5>().build()

        WorkManager.getInstance(this).enqueue(pProc)
        WorkManager.getInstance(this)
            .beginWith(proc2)
            .then(Arrays.asList(proc3,proc4))
            .then(proc5)
            .enqueue()

    }

    override fun onStop() {
        super.onStop()
        WorkManager.getInstance(this).cancelAllWorkByTag("song")
    }
}



package ru.myitacademy.samsung.workmanagersample

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.lang.Thread.sleep

class Woker1(c: Context, wp: WorkerParameters): Worker(c, wp) {
    override fun doWork(): Result {
        Log.d("tagg1","Work1 is start")
        val song: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.song)
        song.start()
        Log.d("tagg1","Work1 is complete")
        return Result.success()
    }
}

class Worker2(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("tagg1","Work2 is start")
        sleep(5000)
        Log.d("tagg1","Work2 is complete")
        return Result.success(Data.Builder().putString("answer","Я проснулся").build())
    }
}

class Worker3(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams){
    override fun doWork(): Result {
        Log.d("tagg1","Work3 is start")
        val aS = inputData.getString("answer")
        var s: Long = 0
        for ( i in Int.MIN_VALUE..Int.MAX_VALUE){
            s+=i
        }
        val d = Data.Builder().putLong("resS",s).putString("answer", aS).build()
        Log.d("tagg1","Work3 is complete")
        return Result.success(d)
    }
}

class Worker4(context: Context, workerParams: WorkerParameters):Worker(context, workerParams){
    override fun doWork(): Result {
        Log.d("tagg1","Work4 is start")
        var m: Long = 1
        for ( i in 1..10){
           m*=i
        }
        val d = Data.Builder().putLong("resM",m).build()
        Log.d("tagg1","Work4 is complete")
        return Result.success(d)
    }
}

class Worker5(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams){
    override fun doWork(): Result {
        Log.d("tagg1","Work5 is start")
        val sum = inputData.getLong("resS",0)
        val mult = inputData.getLong("resM",0)
        val aS = inputData.getString("answer")
        val res = mult.toFloat() / sum
        val output = workDataOf("result" to res,
            "s" to aS)
        Log.d("tagg1","Work5 is complete\n result: "+ output.getFloat("result",-10f) + "\t" + output.getString("s"))
        return Result.success(output)
    }

}
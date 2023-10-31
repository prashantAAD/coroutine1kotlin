package www.revengerfitness.blogspot.com.coroutine1kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    private val Tag:String="prashant chauhan"
    private lateinit var counterText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterText=findViewById(R.id.counter)
        Log.d(Tag, Thread.currentThread().name)
    }
    fun updateCounter(view:View){
        Log.d(Tag, Thread.currentThread().name)
        counterText.text="${counterText.text.toString().toInt()+1}"
    }

    private fun executeLongRunningTask(){
        for (i in 1..1000000000000L){

        }
    }

    /**fun doAction(view: View) {
        thread (start = true) { // new thread for long running task.but we can have limited thread and context switching is not easy with the threads.we can not easily switch from main thread to background thread.so we have coroutines for this problem.

            executeLongRunningTask()
        }
    */

    fun doAction(view: View) {
       CoroutineScope(Dispatchers.IO).launch {
           Log.d(Tag,"1 - ${Thread.currentThread().name}")
       }

        // we have predefined dispatchers in the same way we have predefined scopes those are connected with the particular component.
        // GlobalScope connected with the whole application.

        GlobalScope.launch(Dispatchers.Main) {
            Log.d(Tag,"2 - ${Thread.currentThread().name}" )
        }

        // MainScope connected with the main Activity.
        MainScope().launch(Dispatchers.Default) {
            Log.d(Tag, "3 - ${Thread.currentThread().name}")
        }
    }
    /**
     * Context Switching :
     * what is the solution in java - no solution we only have threads in java.
     * what is the solution in kotlin - coroutines .
     * coroutine are just like threads( light weight threads ) but not threads.
     * coroutines run on top of threads. its a framework just above threads and interact with threads behind the scenes. */

    /**
     * Coroutines implementation: coroutine scope (decides lifetime of coroutine) , coroutine context (on which threads our coroutine will going to execute.)
     * scope defines boundaries for coroutines.
     * */
}
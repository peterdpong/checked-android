package com.peterdpong.mintask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.peterdpong.mintask.edittask.EditFragment
import com.peterdpong.mintask.listtasks.ListFragment
import java.util.*

class MainActivity : AppCompatActivity(), ListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null){
            val fragment = ListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }

    }

    override fun onTaskSelect(taskId: UUID) {
        val fragment = EditFragment.newInstance(taskId)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}

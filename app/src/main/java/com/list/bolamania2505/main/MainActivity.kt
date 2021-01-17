package com.list.bolamania2505

import Fragment
import android.os.Bundle
import android.widget.FrameLayout
import com.list.bolamania2505.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var content: FrameLayout? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_hasil -> {
                val fragment = Fragment.newInstance("eventspastleague")
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_terjadwal -> {
                val fragment = Fragment.newInstance("eventsnextleague")
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = Fragment.newInstance("eventspastleague")
        addFragment(fragment)

    }
}
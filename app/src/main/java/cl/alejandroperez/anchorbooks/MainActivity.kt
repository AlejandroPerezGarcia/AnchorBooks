package cl.alejandroperez.anchorbooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.alejandroperez.anchorbooks.view.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragments, ListFragment.newInstance("", ""), "lista")
                .commit()
        } else {
            supportFragmentManager.findFragmentByTag("lista")
        }
    }
}
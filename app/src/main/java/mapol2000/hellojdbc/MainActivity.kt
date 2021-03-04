package mapol2000.hellojdbc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mapol2000.hellojdbc.jdbc.SELECTEMP

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // AsyncTask 인터페이스를 구현해서 만든 클래스를 실행할때는
        // execute 메서드를 호출해서 실행함
        SELECTEMP().execute()

    }
}
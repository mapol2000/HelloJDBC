package mapol2000.hellojdbc

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.Exception

class ProgressActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var progressBar: ProgressBar
    lateinit var progressTask: ProgressTask2
    var v: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        textView = findViewById(R.id.textView)
        progressBar = findViewById(R.id.progress)

    }

    // 실행버튼
    fun execBtn(v: View) {
        //at = ProgressTask()
        //at.execute(100)

        progressTask = ProgressTask2()
        var params = arrayListOf<Any>()
        params.add(100)
        params.add(progressBar)
        params.add(textView)

        progressTask.execute(params)
    }

    // 취소버튼
    // 추가 조치 사항 : 실행버튼 누르기 전까진 취소버튼을 누를수 없도록 함
    fun ccBtn(v: View) {
        // 비동기 작업 취소시 cancel메서드에 true를 넘김
        progressTask.cancel(true)
    }

    // 비동기 처리 클래스 정의
    inner class ProgressTask : AsyncTask<Int, Int, Int>() {

        // 초기화 작업
        override fun onPreExecute() {
            v = 0
            progressBar.setProgress(v)
            // 상태진행막대의 초기값을 0으로 설정
        }

        // 비동기 실제 작업
        override fun doInBackground(vararg vals: Int?): Int {

            // 취소버튼이 눌러 task가 취소되기 전까지 계속 실행
            while(!isCancelled()) {
                v++
                textView.setText("AsyncTask를 이용한 백그라운드 작업 : ${v}%")

                // 상태진행수치가 100이면 task실행을 중단함
                if (v > vals[0]!!.toInt()) {
                    break
                } else {
                    // 상태진행수치가 100보다 작으면 onProgressUpdate메서드를 호출함
                    // 비동기 작업진행 중에는 UI변경 불가
                    // 따라서 onProgressUpdate 호출해서 UI를 변경함
                    publishProgress(v)
                }

                // 0.1초 정도 cpu 실행 중지함
                try { Thread.sleep(100) } catch (e: Exception) {}
            }

            return vals[0]?.toInt()!!
        }

        // 비동기 작업중 처리
        override fun onProgressUpdate(vararg vals: Int?) {
            progressBar.setProgress(vals[0]?.toInt()!!)
        }

        // 비동기 작업 종료
        override fun onPostExecute(result: Int?) {
            textView.setText("완료되었습니다!!")
        }

        // 비동기 작업 취소
        override fun onCancelled(result: Int?) {
            progressBar.setProgress(v)
            textView.setText("취소되었습니다!!")
        }

    }


}



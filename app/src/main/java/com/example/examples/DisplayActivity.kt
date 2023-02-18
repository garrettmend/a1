import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examples.R

class DisplayActivity : AppCompatActivity() {

    private var mFName: String? = null
    private var mMName: String? = null
    private var mLName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val receivedIntent = intent

        mFName = receivedIntent.getStringExtra("ET_FN")
        mMName = receivedIntent.getStringExtra("ET_MN")
        mLName = receivedIntent.getStringExtra("ET_LN")

        Toast.makeText(
            this@DisplayActivity,
            "Enter only first and last name!",
            Toast.LENGTH_SHORT
        ).show()
    }
}
import com.example.residence_app.MainActivity



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.example.residence_app.HomeAdminActivity
import com.example.residence_app.R


class LogoutUser(val inter: MainActivity) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_logout, container, false)
        val cancel = view.findViewById<ConstraintLayout>(R.id.cancellogout)
        val logout = view.findViewById<Button>(R.id.logout)
        cancel.setOnClickListener { this.dismiss() }
        logout.setOnClickListener {
            inter.logout()
            this.dismiss()
        }

        return view
    }


}
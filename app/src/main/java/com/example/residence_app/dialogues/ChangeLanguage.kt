package com.example.residence_app.dialogues

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.residence_app.BaseActivity
import com.example.residence_app.R
import com.example.residence_app.SplashScreen
import java.util.Locale


class ChangeLanguage : DialogFragment() {
companion object{

}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view =inflater.inflate(R.layout.fragment_change_language, container, false)
        val en = view.findViewById<RadioButton>(R.id.en)
        val fr =  view.findViewById<RadioButton>(R.id.fr)

        if(BaseActivity.dLocale.toString()=="en"){
            en.isChecked=true
            fr.isChecked=false
        }else{
            en.isChecked=false
            fr.isChecked=true
        }



        en.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
            BaseActivity.dLocale = Locale("en")
            val sharedPref= PreferenceManager.getDefaultSharedPreferences(requireContext())
            sharedPref.edit().putString("language","en").apply()
            requireActivity().finish()
            startActivity(requireActivity().intent)
            this.dismiss()}
        }


        fr.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
            BaseActivity.dLocale = Locale("fr")
            val sharedPref= PreferenceManager.getDefaultSharedPreferences(requireContext())
            sharedPref.edit().putString("language","fr").apply()
            requireActivity().finish()
            startActivity(requireActivity().intent)}
            this.dismiss()
        }






        return view
    }


}
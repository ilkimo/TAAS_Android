package com.example.datahubapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.datahubapp.data.model.UserData
import androidx.appcompat.app.AppCompatDelegate

import android.net.http.SslCertificate.saveState

import com.example.datahubapp.R

import com.google.android.material.textview.MaterialTextView

import com.google.android.material.switchmaterial.SwitchMaterial
import java.util.*


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_settings, container, false)

        //Set back arrow visible and enabled
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayShowHomeEnabled(true)


        val themeSwitch: SwitchMaterial = root.findViewById(R.id.themeSwitch)
        val appVersion: MaterialTextView = root.findViewById(R.id.appVersion)

        //saveState = SaveState(requireContext())

        //themeSwitch.isChecked = saveState.getState()

        themeSwitch.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                //saveState.setState(true)
                (requireActivity() as AppCompatActivity).delegate.localNightMode =
                    AppCompatDelegate.MODE_NIGHT_NO
                Log.d("SettingsFragment", "Light Mode")
            } else {
                //saveState.setState(false)
                (requireActivity() as AppCompatActivity).delegate.localNightMode =
                    AppCompatDelegate.MODE_NIGHT_YES
                Log.d("SettingsFragment", "Dark Mode")
            }
        }

        appVersion.text = BuildConfig.VERSION_NAME
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)?.setDisplayHomeAsUpEnabled(false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)?.supportActionBar?.title = getString(R.string.settings)
    }
}
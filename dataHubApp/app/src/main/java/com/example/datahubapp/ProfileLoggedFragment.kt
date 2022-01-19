package com.example.datahubapp

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.datahubapp.controller.login
import com.example.datahubapp.databinding.FragmentLoginBinding
import com.example.datahubapp.databinding.FragmentProfileLoggedBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import com.example.datahubapp.controller.googleLogout
import com.example.datahubapp.controller.logout
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileLoggedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileLoggedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit private var model: AppViewModel

    private val googleToken: String = "282646887193-mj946se9m6a7qgmkl2npmrjfksbcht6r.apps.googleusercontent.com"

    //Google Login
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001

    private var _binding: FragmentProfileLoggedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        var viewModelFactory = AppViewModelFactory(requireContext())
        model = ViewModelProviders.of(requireParentFragment(), viewModelFactory).get(AppViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)?.supportActionBar?.title = getString(R.string.profile)

        _binding = FragmentProfileLoggedBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Google Login
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(googleToken)
            .requestEmail()
            .build()
        mGoogleSignInClient = activity?.let { GoogleSignIn.getClient(it, gso) }!!

        val logoutButton = binding.logoutButton

        logoutButton.setOnClickListener {
            //TODO implement backend token invalidation
            logout(requireParentFragment(), requireContext())

            //SE IL LOGOUT VA A BUON FINE
            var navigationDirection: NavDirections = ProfileLoggedFragmentDirections.actionProfileLoggedFragmentToProfileFragment();
            NavHostFragment.findNavController(requireParentFragment()).navigate(navigationDirection)

            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
            bottomNavigationView?.menu?.findItem(R.id.loginFragment)?.isVisible = true
            bottomNavigationView?.menu?.findItem(R.id.profileLoggedFragment)?.isVisible = false

            //NavHostFragment.findNavController(requireParentFragment())
                //.navigate(R.id.action_profileLoggedFragment_to_profileFragment)
        }
        logoutButton.isVisible = !model.getLoggedWithGoogle()

        val logoutFromGoogleButton = binding.logoutFromGoogleButton

        logoutFromGoogleButton.setOnClickListener {
            mGoogleSignInClient.signOut()
            googleLogout(requireParentFragment(), requireContext())
            model.setLoggedWithGoogle(false)

            //SE IL LOGOUT VA A BUON FINE
            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
            bottomNavigationView?.menu?.findItem(R.id.loginFragment)?.isVisible = true
            bottomNavigationView?.menu?.findItem(R.id.profileLoggedFragment)?.isVisible = false

            NavHostFragment.findNavController(this)
                .navigate(R.id.action_profileLoggedFragment_to_profileFragment)
        }
        logoutFromGoogleButton.isVisible = model.getLoggedWithGoogle()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileLoggedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileLoggedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
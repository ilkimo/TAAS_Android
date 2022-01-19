package com.example.datahubapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.datahubapp.databinding.FragmentCreateAccountBinding
import android.R

import androidx.appcompat.app.AppCompatActivity

import android.widget.TextView
import android.text.Editable

import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

import android.widget.Toast

import android.content.Intent
import android.util.Patterns
import java.lang.NullPointerException
import android.app.ProgressDialog
import android.os.Build
import android.widget.Button

import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.datahubapp.controller.createAccount
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateAccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateAccountFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var _binding: FragmentCreateAccountBinding? = null
    private val googleToken: String = "282646887193-mj946se9m6a7qgmkl2npmrjfksbcht6r.apps.googleusercontent.com"

    //Google Login
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001

    private val TAG = "CreateAccountFragment"
    private var emailText: EditText? = null
    private var passwordText: EditText? = null
    private var confirmPasswordText: EditText? = null
    private var createAccountButton: Button? = null
    //private val activityViewModel: ActivityViewModel? = null
    private var emailTIL: TextInputLayout? = null
    private var passwordTIL: TextInputLayout? = null
    private var confirmPasswordTIL: TextInputLayout? = null
    private var progressDialog: ProgressDialog? = null

    /*
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
     */

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Create account page", "Vista creata!")

        (activity as AppCompatActivity?)?.supportActionBar?.title = getString(com.example.datahubapp.R.string.create_account)

        val logIn = binding.goToLogin

        logIn.setOnClickListener {
            Log.d("Create account page", "hai cliccato log in!")

            var navigationDirection: NavDirections = CreateAccountFragmentDirections.actionCreateAccountFragmentToProfileFragment();
            NavHostFragment.findNavController(this).navigate(navigationDirection)
        }


        //TODO: remove me -> aggiunto qua della roba

        createAccountButton  = binding.btnCreateAccount
        createAccountButton!!.setOnClickListener { _ -> createAccount() }

        emailText = binding.inputEmail
        passwordText = binding.inputPassword
        confirmPasswordText = binding.inputConfirmPassword

        /*
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)
            .setDisplayHomeAsUpEnabled(false)

         */

        emailTIL = binding.emailTextInputLayout
        passwordTIL = binding.passwordTextInputLayout
        confirmPasswordTIL = binding.confirmPasswordTextInputLayout

        emailText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                passwordTIL!!.error = null
                emailTIL!!.error = null
            }
        })

        passwordText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                passwordTIL!!.error = null
            }
        })

        confirmPasswordText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                confirmPasswordTIL!!.error = null
            }
        })

        //Google Login
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(googleToken)
            .requestEmail()
            .build()

        mGoogleSignInClient = activity?.let { GoogleSignIn.getClient(it, gso) }!!

        val googleLoginBtn = binding.signUpButton

        googleLoginBtn.setOnClickListener {
            Log.d("Google Create Account", "Create account con google cliccato!")
            signUp()
        }
    }

    private fun signUp() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(
            signInIntent, RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignUpResult(task)
        }
    }

    private fun handleSignUpResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java
            )
            // Signed in successfully
            val googleId = account?.id ?: ""
            Log.i("Google ID",googleId)

            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)

            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)

            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)

            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)

            val googleIdToken = account?.idToken ?: ""
            Log.i("Google ID Token", googleIdToken)

            //TODO: loggare nell'app

        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                "failed code=", e.statusCode.toString()
            )
            Log.d("Google token", googleToken)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createAccount() {
        Log.d(TAG, "Create account")
        if (!validate()) {
            onCreateAccountFailed()
            return
        }
        createAccountButton!!.setEnabled(false)
        //progressDialog!!.setIndeterminate(true)
        //progressDialog!!.setMessage("Authenticating...")
        //progressDialog!!.show()

        Log.d(TAG, "Create account controls passed");

        val email: String = emailText!!.getText().toString()
        val password: String = passwordText!!.getText().toString()
        val confirmPassword: String = confirmPasswordText!!.getText().toString()
        if (email == "") {
            emailTIL!!.setError("Please insert a valid email")
        } else if (password == "") {
            passwordTIL!!.setError("Please insert a password")
        } else if (confirmPassword == "") {
            passwordTIL!!.setError("Please confirm password")
        } else if (password != confirmPassword) {
            confirmPasswordTIL!!.setError("New password does not match")
        } else {
            createAccount(
                requireParentFragment(), requireContext(),
                emailText!!.text.toString(), passwordText!!.text.toString())
        }
    }

    fun onCreateAccountSuccess(email: String?, password: String?) {
        createAccountButton!!.setEnabled(true)
        Toast.makeText(requireContext(), "Logged In", Toast.LENGTH_LONG).show()

        // Set Logged In status to 'true'
        //SaveSharedPreference.setLoggedIn(requireContext(), true, email, password, "User")

        //Restart main activity ----> CHANGE MODE
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }

    fun onCreateAccountFailed() {
        Toast.makeText(requireContext(), "Wrong Email or Password", Toast.LENGTH_LONG).show()
        createAccountButton!!.setEnabled(true)
    }

    fun validate(): Boolean {
        var valid = true
        val email: String = emailText!!.getText().toString()
        val password: String = passwordText!!.getText().toString()
        val emailTIL: TextInputLayout = binding.emailTextInputLayout
        val passwordTIL: TextInputLayout = binding.passwordTextInputLayout
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTIL.error = "Enter a valid email address"
            valid = false
        } else {
            emailTIL.error = null
        }
        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            passwordTIL.error = "Lenght between 4 and 10 alphanumeric characters"
            valid = false
        } else {
            passwordTIL.error = null
        }
        return valid
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateAccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateAccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
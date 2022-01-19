package com.example.datahubapp.controller

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.datahubapp.*
import com.example.datahubapp.data.convertToJSON
import com.example.datahubapp.data.model.*
import com.example.datahubapp.data.parseJSON
import com.example.datahubapp.data.viewmodel.AppViewModel
import com.example.datahubapp.data.viewmodel.AppViewModelFactory
import com.example.datahubapp.ui.login.LoginFragment
import com.example.datahubapp.ui.login.LoginFragmentDirections
import kotlinx.coroutines.*
import kotlinx.coroutines.launch




private const val url = "http://10.0.2.2:8080/gateway/"
private const val TAG = "AppController"

enum class REQUEST {
    ALL_USERS,
    CREATE_ACCOUNT,
    CHANGE_PASSWORD,
    LOGIN,
    LOGIN_GOOGLE,
    GET_TOPICS_USER,
    NEW_TOPIC,
    NEW_REGISTRATION,
    DELETE_TOPIC,
    DELETE_REGISTRATION,
    GET_SHARED_TOPICS,
    CHANGE_TOPIC_SHARED_STATUS,
    CHANGE_NAME_TOPIC,
    DELETE_USER,
    REFRESH,
    REFRESH_SHARED_TOPICS
}

enum class RETURNTYPE {
    USERANDDATA,
    USER,
    USERDATA,
    TOPIC_LIST,
    SHARED_TOPICS
}

@RequiresApi(Build.VERSION_CODES.O)
fun addRegistration(fragment: Fragment, context: Context, newRegistration: NewRegistration) {
    val jsonObject = convertToJSON(newRegistration, NewRegistration::class.java)

    asyncRequest(fragment, context, jsonObject, REQUEST.NEW_REGISTRATION, RETURNTYPE.USERDATA)
}

@RequiresApi(Build.VERSION_CODES.O)
fun addTopic(fragment: Fragment, context: Context, newTopic: NewTopic) {
    val jsonObject = convertToJSON(newTopic, NewTopic::class.java)

    asyncRequest(fragment, context, jsonObject, REQUEST.NEW_TOPIC, RETURNTYPE.USERDATA)
}

@RequiresApi(Build.VERSION_CODES.O)
fun changeSharedTopicStatus(fragment: Fragment, context: Context,
                            idUser: String, topicName: String, newName: String?) {
    var obj = ChangeTopicSharedStatus(idUser, topicName, newName)
    val jsonObject = convertToJSON(obj, ChangeTopicSharedStatus::class.java)

    asyncRequest(fragment, context, jsonObject, REQUEST.CHANGE_TOPIC_SHARED_STATUS, RETURNTYPE.USERDATA)
}

@RequiresApi(Build.VERSION_CODES.O)
fun createAccount(fragment: Fragment, context: Context,
                  email: String, password: String) {
    var obj = User("", "", email, password)
    val jsonObject = convertToJSON(obj, User::class.java)

    asyncRequest(fragment, context, jsonObject, REQUEST.CREATE_ACCOUNT, RETURNTYPE.USERANDDATA)
}

@RequiresApi(Build.VERSION_CODES.O)
fun deleteRegistration(fragment: Fragment, context: Context, idUser: String,
                       idRegistration: Long, topicName: String) {
    val delRegistration = DeleteReg(idUser, idRegistration, topicName)
    val jsonObject = convertToJSON(delRegistration, DeleteReg::class.java)

    asyncRequest(fragment, context, jsonObject, REQUEST.DELETE_REGISTRATION, RETURNTYPE.USERDATA)
}

@RequiresApi(Build.VERSION_CODES.O)
fun deleteTopic(fragment: Fragment, context: Context, topicName: String, userID: Long) {
    val delTopic = DeleteTopic(userID.toString(), topicName)
    val jsonObject = convertToJSON(delTopic, DeleteTopic::class.java)

    asyncRequest(fragment, context, jsonObject, REQUEST.DELETE_TOPIC, RETURNTYPE.USERDATA)
}

@RequiresApi(Build.VERSION_CODES.O)
fun logout(fragment: Fragment, context: Context) {
    //TODO invalidate backend token
    getViewModel(fragment, context).deleteData()
}

@RequiresApi(Build.VERSION_CODES.O)
fun googleLogout(fragment: Fragment, context: Context) {
    getViewModel(fragment, context).deleteData()
}

@RequiresApi(Build.VERSION_CODES.O)
fun refresh(fragment: Fragment, context: Context, idUser: String) {
    val userData = UserData(null, idUser, null)
    val jsonObject = convertToJSON(userData, UserData::class.java)

    asyncRequest(fragment, context, jsonObject, REQUEST.REFRESH, RETURNTYPE.USERDATA)
}

@RequiresApi(Build.VERSION_CODES.O)
fun refreshSharedTopics(fragment: Fragment, context: Context, idUser: String) {
    val userData = UserData(null, idUser, null)
    val jsonObject = convertToJSON(userData, UserData::class.java)

    asyncRequest(fragment, context, jsonObject, REQUEST.REFRESH_SHARED_TOPICS, RETURNTYPE.SHARED_TOPICS)
}

private fun getUrlString(type: REQUEST): String {
    val str: String = url + when(type) {
        REQUEST.ALL_USERS -> "all"
        REQUEST.CREATE_ACCOUNT -> "create"
        REQUEST.CHANGE_PASSWORD -> "changePassword"
        REQUEST.LOGIN -> "login"
        REQUEST.LOGIN_GOOGLE -> "loginGoogle"
        REQUEST.GET_TOPICS_USER -> "topicUser"
        REQUEST.REFRESH -> "topicUser"
        REQUEST.REFRESH_SHARED_TOPICS -> "sharedTopic"
        REQUEST.GET_SHARED_TOPICS -> "sharedTopic"
        REQUEST.NEW_TOPIC -> "newTopic"
        REQUEST.NEW_REGISTRATION -> "newReg"
        REQUEST.DELETE_TOPIC -> "delTopic"
        REQUEST.DELETE_REGISTRATION -> "delReg"
        REQUEST.CHANGE_TOPIC_SHARED_STATUS -> "changSharedTopic"
        REQUEST.CHANGE_NAME_TOPIC -> "changeNameTopic"
        REQUEST.DELETE_USER -> "deleteUser"
    }

    Log.d("$TAG", "getUrlString for REQUEST $type=$str")

    return str
}

fun getViewModel(fragment: Fragment, context: Context): AppViewModel {
    var viewModelFactory = AppViewModelFactory(context)
    return ViewModelProviders.of(fragment, viewModelFactory).get(AppViewModel::class.java)
}

@RequiresApi(Build.VERSION_CODES.O)
fun login(fragment: Fragment, context: Context, username: String, password: String) {
    val jsonObject = convertToJSON(
        User("", "", username, password),
        User::class.java
    )

    asyncRequest(fragment, context, jsonObject, REQUEST.LOGIN, RETURNTYPE.USERANDDATA)
    asyncRequest(fragment, context, "", REQUEST.GET_SHARED_TOPICS, RETURNTYPE.SHARED_TOPICS)
}

@RequiresApi(Build.VERSION_CODES.O)
fun loginGoogle(fragment: Fragment, context: Context, username: String) {
    val jsonObject = convertToJSON(
        User("", "", username, ""),
        User::class.java
    )

    asyncRequest(fragment, context, jsonObject, REQUEST.LOGIN_GOOGLE, RETURNTYPE.USERANDDATA)
    asyncRequest(fragment, context, "", REQUEST.GET_SHARED_TOPICS, RETURNTYPE.SHARED_TOPICS)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun asyncRequest(fragment: Fragment, context: Context,
                         jsonObject: String, requestType: REQUEST,
                         returnType: RETURNTYPE) {
    var viewModel = getViewModel(fragment, context)

    //viewModel.viewModelScope
    viewModel.viewModelScope.launch(Dispatchers.IO) {
        val result = try {
            makeRequest(getUrlString(requestType), jsonObject)
        } catch(e: Exception) {
            Result.Error(Exception("Network request failed: ${e.message}"))
        }

        processResult(fragment, returnType, requestType, result, viewModel, context)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun processResult(fragment: Fragment, returnType: RETURNTYPE, requestType: REQUEST,
                          result: Result<*>, viewModel: AppViewModel, context: Context) {
    Log.d("$TAG", "processResult")
    lateinit var obj: Any

    //TODO remove Toast with hard coded strings
    when(requestType) {
        REQUEST.LOGIN -> {
            when(result) {
                is Result.Success -> {
                    obj = parseJSON((result.data as String), returnType)
                    viewModel.setUser((obj as UserAndData).userInformation)
                    viewModel.setUserData((obj as UserAndData).dataInformation)

                    Handler(Looper.getMainLooper()).post {
                        var navigationDirection: NavDirections = LoginFragmentDirections.actionLoginFragmentToTopicsFragment();
                        NavHostFragment.findNavController(fragment).navigate(navigationDirection)
                    }
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Login error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        REQUEST.CREATE_ACCOUNT -> {
            when(result) {
                is Result.Success -> {
                    obj = parseJSON((result.data as String), returnType)
                    viewModel.setUser((obj as UserAndData).userInformation)
                    viewModel.setUserData((obj as UserAndData).dataInformation)

                    Handler(Looper.getMainLooper()).post {
                        //TODO
                        //var navigationDirection: NavDirections = LoginFragmentDirections.actionLoginFragmentToTopicsFragment();
                        //NavHostFragment.findNavController(fragment).navigate(navigationDirection)
                    }
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Login error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        REQUEST.LOGIN_GOOGLE -> {
            when(result) {
                is Result.Success -> {
                    obj = parseJSON((result.data as String), returnType)
                    viewModel.setUser((obj as UserAndData).userInformation)
                    viewModel.setUserData((obj as UserAndData).dataInformation)

                    Handler(Looper.getMainLooper()).post {
                        var navigationDirection: NavDirections = LoginFragmentDirections.actionLoginFragmentToTopicsFragment();
                        NavHostFragment.findNavController(fragment).navigate(navigationDirection)
                    }
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Login error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        REQUEST.NEW_TOPIC -> {
            when(result) {
                is Result.Success -> {
                    obj = parseJSON((result.data as String), returnType)
                    viewModel.setUserData(obj as UserData)

                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Topic added", Toast.LENGTH_SHORT).show()
                        NavHostFragment.findNavController(fragment).popBackStack()
                    }
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Error adding topic!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        REQUEST.DELETE_TOPIC -> {
            when(result) {
                is Result.Success -> {
                    obj = parseJSON((result.data as String), returnType)
                    viewModel.setUserData(obj as UserData)
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Error deleting topic!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        REQUEST.NEW_REGISTRATION -> {
            when(result) {
                is Result.Success -> {
                    obj = parseJSON((result.data as String), returnType)
                    viewModel.setUserData(obj as UserData)
                    //make main Thread show Toast and navigate backwards
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Registration added!", Toast.LENGTH_SHORT).show()
                        NavHostFragment.findNavController(fragment).popBackStack()
                    }
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Error adding registration!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        REQUEST.DELETE_REGISTRATION -> {
            when(result) {
                is Result.Success -> {
                    obj = parseJSON((result.data as String), returnType)
                    viewModel.setUserData(obj as UserData)
                    //make main Thread show Toast and navigate backwards
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Registration deleted", Toast.LENGTH_SHORT).show()
                        NavHostFragment.findNavController(fragment).popBackStack()
                    }
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Error deleting registration!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        REQUEST.CHANGE_TOPIC_SHARED_STATUS -> {
            when(result) {
                is Result.Success -> {
                    obj = parseJSON((result.data as String), returnType)
                    viewModel.setUserData(obj as UserData)
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                        //TODO make switch go back to previous state
                    }
                }
            }
        }
        REQUEST.REFRESH -> {
            when(result) {
                is Result.Success -> {
                    obj = parseJSON((result.data as String), returnType)
                    viewModel.setUserData(obj as UserData)
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Data refreshed!", Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Error refreshing", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        REQUEST.GET_SHARED_TOPICS -> {
            when(result) {
                is Result.Success -> {
                    obj = parseJSON((result.data as String), returnType)
                    viewModel.setSharedTopics((obj as TopicList).sharedTopicList)
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Error fetching shared topics!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        REQUEST.REFRESH_SHARED_TOPICS -> {
            when(result) {
                is Result.Success -> {
                    obj = parseJSON((result.data as String), returnType)
                    viewModel.setSharedTopics((obj as TopicList).sharedTopicList)
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Shared topics refreshed!", Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "Error refreshing", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        else -> TODO()
    }
}
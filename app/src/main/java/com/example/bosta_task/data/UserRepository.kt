package com.example.bosta_task.data

import android.app.Application
import android.util.Log
import com.example.bosta_task.api.UserAPi
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.bosta_task.Models.AlbumsModel
import com.example.bosta_task.Models.PhotoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class UserRepository @Inject constructor(private val api: UserAPi) {

    private val _isLoading = MutableStateFlow(true)
    private val _showErrorMessage = MutableStateFlow(false)
    private val _userName = MutableStateFlow("")
    private val _userAddress = MutableStateFlow("")
    private val _userAlbums = MutableStateFlow<List<AlbumsModel>>(listOf())
    private val _userPhotos = MutableStateFlow<List<PhotoModel>>(listOf())

    val isLoading: LiveData<Boolean>
        get() = _isLoading.asLiveData()

    val showErrorMessage: LiveData<Boolean>
        get() = _showErrorMessage.asLiveData()

    val albums: LiveData<List<AlbumsModel>>
        get() = _userAlbums.asLiveData()

    val photos: LiveData<List<PhotoModel>>
        get() = _userPhotos.asLiveData()

    val userName: LiveData<String>
        get() = _userName.asLiveData()

    val userAddress: LiveData<String>
        get() = _userAddress.asLiveData()

    fun getUsers() {

        CoroutineScope(Dispatchers.IO).launch {
            _isLoading.emit(true)
            try {
                _userName.emit(api.getUsers()[2].name.toString())
                _userAddress.emit(
                    api.getUsers()[2].address!!.street.toString() + ", "
                            + api.getUsers()[2].address!!.suite.toString() + ", "
                            + api.getUsers()[2].address!!.city.toString() + ",\n"
                            + api.getUsers()[2].address!!.zipcode.toString()
                )

                _userAlbums.emit(api.getUserAlbums(api.getUsers()[2].id!!.toInt()))
                _isLoading.emit(false)

            } catch (e: Exception) {
                _isLoading.emit(false)
                _showErrorMessage.emit(true)


            }


        }

    }

    fun getPhotos(albumId: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            _isLoading.emit(true)
            try {

                _userPhotos.emit(api.getPhotos(albumId))
                
                _isLoading.emit(false)

            } catch (e: Exception) {
                Log.d("TAG", "BEFO: $e ")
                _isLoading.emit(false)
                _showErrorMessage.emit(true)


            }


        }

    }
}
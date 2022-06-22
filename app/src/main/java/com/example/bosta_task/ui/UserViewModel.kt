package com.example.bosta_task.ui

import androidx.lifecycle.ViewModel
import com.example.bosta_task.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val isLoading = userRepository.isLoading
    val showErrorMessage = userRepository.showErrorMessage
    val userName = userRepository.userName
    val userAddress = userRepository.userAddress
    val userAlbums = userRepository.albums
    val photos = userRepository.photos

    fun getUserData() {
        userRepository.getUsers()
    }

    fun getPhotos(albumId: Int) {
        userRepository.getPhotos(albumId)
    }

}
package com.aditya.hilt_retrofit

import androidx.lifecycle.ViewModel
import com.aditya.hilt_retrofit.domain.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewmodel @Inject constructor(
    private val repository: MyRepository
) : ViewModel() {

}
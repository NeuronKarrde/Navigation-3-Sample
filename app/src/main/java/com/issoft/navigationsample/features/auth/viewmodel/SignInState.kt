package com.issoft.navigationsample.features.auth.viewmodel

import com.issoft.navigationsample.features.auth.UserSession

data class SignInState (val isLoading: Boolean = false,
                        val isPasswordVisible : Boolean = false,
                        val session: UserSession? = null,
                        val error: String? = null,
                        val email : String = "",
                        val password : String = "")


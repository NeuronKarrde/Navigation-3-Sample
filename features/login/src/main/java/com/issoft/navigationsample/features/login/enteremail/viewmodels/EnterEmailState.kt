package com.issoft.navigationsample.features.login.enteremail.viewmodels

import com.issoft.navigationsample.features.login.UserSession

data class EnterEmailState (val isLoading: Boolean = false,
                            val isPasswordVisible : Boolean = false,
                            val session: UserSession? = null,
                            val error: String? = null,
                            val email : String = "",
                            val password : String = "")


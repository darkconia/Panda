package com.example.pandasoft.util


import java.io.IOException

class NoNetworkException : IOException() {

    override fun getLocalizedMessage(): String? {
        return "No connectivity exception"
    }

}

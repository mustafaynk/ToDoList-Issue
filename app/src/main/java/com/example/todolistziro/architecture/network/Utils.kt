package com.example.todolistziro.architecture.network

object Utils {

    object Urls {
        private const val API_BASE_URL = "http://192.168.1.242"

        //LOGIN
        const val LOGIN_URL = "$API_BASE_URL:8100/api/auth/"

        const val ISSUE_API_URL = "$API_BASE_URL:8092/api/"
    }

   object LocalDataKeys {
       const val TOKEN = "token"
   }

}
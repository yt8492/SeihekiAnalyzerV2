package com.yt8492.serihekianalyzerv2.common.domain.model

import java.security.MessageDigest

data class Password(
    val hashedValue: String
) {
    companion object {
        private val sha256 = MessageDigest.getInstance("SHA-256")
        private const val salt = "seihekianalyzer"

        fun fromRowValue(
            rowValue: String
        ): Password = sha256.digest((rowValue + salt).toByteArray())
            .joinToString("") {
                "%02x".format(it)
            }.let {
                Password(it)
            }
    }
}
package com.octane.pbd.data

import com.octane.pbd.network.PwnedApi
import com.octane.pbd.util.CryptoUtils
import com.octane.pbd.util.PwnedParser
import javax.inject.Inject

data class BreachResult(
    val sha1: String,
    val count: Int
) {
    val breached: Boolean get() = count > 0
    val prefix: String get() = sha1.substring(0, 5)
    val suffix: String get() = sha1.substring(5)
}

class PwnedRepository @Inject constructor(
    private val api: PwnedApi
) {
    suspend fun checkPassword(password: String): BreachResult {
        val sha1 = CryptoUtils.sha1Hex(password)
        val prefix = sha1.substring(0, 5)
        val suffix = sha1.substring(5)
        val response = api.getRange(prefix) // plaintext
        val count = PwnedParser.countForSuffix(response, suffix)
        return BreachResult(sha1 = sha1, count = count)
    }
}

package com.yt8492.seihekianalyzerv2.server.domain.repository

import com.yt8492.seihekianalyzerv2.common.domain.model.Account
import com.yt8492.seihekianalyzerv2.common.domain.model.Username

interface AccountRepository {
    suspend fun findByUsername(username: Username): Account
    suspend fun save(account: Account)
}
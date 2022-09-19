package io.github.droidkaigi.confsched2022.model

import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

interface ContributorsRepository {
    fun contributors(): Flow<PersistentList<Contributor>>
    suspend fun refresh()
}

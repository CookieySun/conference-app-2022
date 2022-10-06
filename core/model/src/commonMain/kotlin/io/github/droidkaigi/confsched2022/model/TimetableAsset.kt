package io.github.droidkaigi.confsched2022.model

import kotlinx.serialization.Serializable

@Serializable
public data class TimetableAsset(
    val videoUrl: String?,
    val slideUrl: String?,
) {
    val isAvailable: Boolean
        get() = videoUrl != null || slideUrl != null
}

package io.github.droidkaigi.confsched2022.model

import kotlinx.serialization.Serializable

@Serializable
public data class MultiLangText(
    val jaTitle: String,
    val enTitle: String,
) {
    val currentLangTitle: String get() = getByLang(defaultLang())

    private fun getByLang(lang: Lang): String {
        return if (lang == Lang.JAPANESE) {
            jaTitle
        } else {
            enTitle
        }
    }

    public companion object
}

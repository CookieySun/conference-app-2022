package io.github.droidkaigi.confsched2022.model

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.Serializable

@Serializable
public data class Sponsor(
    val name: String,
    val logo: String,
    val plan: Plan,
    val link: String
) {
    public companion object
}

@Serializable
public enum class Plan {
    PLATINUM,
    GOLD,
    SUPPORTER;

    public companion object {
        public fun ofOrNull(plan: String): Plan? {
            return values().firstOrNull { it.name == plan }
        }
    }
}

public fun Sponsor.Companion.fakes(): PersistentList<Sponsor> = (
    List(3) { index ->
        Sponsor(
            name = "DroidKaigi",
            logo = "https://placehold.jp/150x150.png#$index",
            plan = Plan.PLATINUM,
            link = "https://developer.android.com/"
        )
    } + List(5) { index ->
        Sponsor(
            name = "DroidKaigi",
            logo = "https://placehold.jp/150x150.png#$index",
            plan = Plan.GOLD,
            link = "https://developer.android.com/"
        )
    } + List(12) { index ->
        Sponsor(
            name = "DroidKaigi",
            logo = "https://placehold.jp/150x150.png#$index",
            plan = Plan.SUPPORTER,
            link = "https://developer.android.com/"
        )
    }
    ).toPersistentList()

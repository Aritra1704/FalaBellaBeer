package com.example.falabellabeer.utils

enum class FilterCategory(val filterType: String) {
    LAGER("LAGER"),
    ALE("ALE"),
    IPA("IPA");

    companion object {
        fun create(x: String): FilterCategory {
            return when (x) {
                "LAGER" -> LAGER
                "ALE" -> ALE
                "IPA" -> IPA
                else -> throw IllegalStateException()
            }
        }
    }
}
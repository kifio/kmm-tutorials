package me.kifio.organize.presentation

import me.kifio.organize.Platform
import me.kifio.organize.screenInfo

class AboutViewModel(private val platform: Platform) : BaseViewModel() {

    data class RowItem(
        val title: String,
        val subtitle: String
    )

    val items: List<RowItem> = makeItems()

    private fun makeItems(): List<RowItem> {
        return mutableListOf(
            RowItem("Os", "${platform.osName} ${platform.osVersion}"),
            RowItem("Device", platform.deviceModel),
            RowItem("CPU", platform.cpuType),
        ).apply {
            platform.screen?.let { add(RowItem("Display", platform.screenInfo)) }
        }
    }

}
package org.example.jetshop.preference

import androidx.compose.ui.text.font.FontVariation
import com.russhwolf.settings.Settings

expect class MultiplatformSettingsFactory {
    fun getSettings():  Settings
}
package org.example.jetshop.preference

import com.russhwolf.settings.Settings

import com.russhwolf.settings.NSUserDefaultsSettings
import platform.Foundation.NSUserDefaults

actual class MultiplatformSettingsFactory {
    actual fun getSettings(): Settings {
        val delegate = NSUserDefaults.standardUserDefaults()
        return NSUserDefaultsSettings(delegate)
    }
}
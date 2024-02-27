package com.marwa.ecinterviewtask.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class PreferenceConfig {
    companion object {
        private const val SHARED_PREFS_FILE_NAME = "ec_encrypted_prefs"

        fun provideEncryptedSharedPreferences(context: Context): SharedPreferences {
            val masterKeyAlias = MasterKey.Builder(context)
               .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            return EncryptedSharedPreferences.create(
                context,
                SHARED_PREFS_FILE_NAME,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
    }
}
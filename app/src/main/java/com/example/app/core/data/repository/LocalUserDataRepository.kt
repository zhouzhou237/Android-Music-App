package com.example.app.core.data.repository

import com.example.app.core.datastore.PlaybackModePreferences
import com.example.app.core.datastore.SessionPreferences
import com.example.app.core.datastore.UserPreferences
import com.example.app.core.datastore.datasource.MyPreferencesDatasource
import com.example.app.core.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Local storage for user preference repository
 */
class LocalUserDataRepository @Inject
constructor(
    private val myPreferencesDatasource: MyPreferencesDatasource
) : UserDataRepository {
    override val userData: Flow<UserData> =
        myPreferencesDatasource.userData

    override suspend fun setNotShowGuide(notShowGuide: Boolean) =
        myPreferencesDatasource.setNotShowGuide(notShowGuide)

    override suspend fun setNotShowTermsServiceAgreement(notShow: Boolean) =
        myPreferencesDatasource.setNotShowTermsServiceAgreement(notShow)

    override suspend fun setSession(data: SessionPreferences?) {
        myPreferencesDatasource.setSession(data!!)
    }

    override suspend fun setUser(data: UserPreferences?) {
        myPreferencesDatasource.setUser(data!!)
    }

    override suspend fun logout() {
        myPreferencesDatasource.logout()
    }

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        myPreferencesDatasource.setDynamicColorPreference(useDynamicColor)
    }

    override suspend fun saveRecentSong(
        id: String,
        currentPosition: Long,
        duration: Long,
    ) {
        myPreferencesDatasource.saveRecentSong(id, currentPosition, duration)
    }

    override suspend fun setRepeatModel(
        data: PlaybackModePreferences
    ) {
        myPreferencesDatasource.setRepeatModel(data)
    }

    override suspend fun setGlobalLyricTextColorIndex(data: Int) {
        myPreferencesDatasource.setGlobalLyricTextColorIndex(data)
    }

    override suspend fun setGlobalLyricTextSize(data: Int) {
        myPreferencesDatasource.setGlobalLyricTextSize(data)
    }

    override suspend fun setGlobalLyricViewPositionY(y: Int) {
        myPreferencesDatasource.setGlobalLyricViewPositionY(y)
    }

    override suspend fun setShowGlobalLyric(data: Boolean) {
        myPreferencesDatasource.setShowGlobalLyric(data)
    }

    override suspend fun setGlobalLyricLock(data: Boolean) {
        myPreferencesDatasource.setGlobalLyricLock(data)
    }


}
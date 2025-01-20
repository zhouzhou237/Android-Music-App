package com.example.app.core.model


data class UserData(


    val notShowGuide: Boolean = false,
//    val session: SessionPreferences = SessionPreferences.newBuilder().build(),
//    val user: UserPreferences = UserPreferences.newBuilder().build(),
    val notShowTermsServiceAgreement: Boolean = false,
//    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
    val useDynamicColor: Boolean = false,

    //播放循环模式
    val playRepeatMode: PlaybackMode = PlaybackMode.REPEAT_LIST,

    //播放音乐id
    val playMusicId: String = "",

    //播放进度
    val playProgress: Long = 0,

    //播放音乐时长
    val playDuration: Long = 0,

//    val globalLyricStyle: GlobalLyricStylePreferences = GlobalLyricStylePreferences.newBuilder()
//        .build(),
) {
    /**
     * 是否已经登录了
     */
    fun isLogin(): Boolean {
//        return session.userId.isNotBlank()
        return false
    }
}

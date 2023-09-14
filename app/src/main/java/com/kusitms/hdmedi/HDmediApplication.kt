package com.kusitms.hdmedi

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HDmediApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "f195ae8c23b92631efab4b034c32bc85")
    }
}
package com.ericktijerou.bluetoothsettings

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.leanback.app.GuidedStepSupportFragment

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null == savedInstanceState) {
            GuidedStepSupportFragment.addAsRoot(this, SettingFragment(), android.R.id.content)
        }
    }
}
package com.ericktijerou.bluetoothsettings

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    ActionType.NONE,
    ActionType.ON,
    ActionType.OFF,
    ActionType.NO_DISCOVERABLE
)

annotation class ActionType {
    companion object {
        const val NONE = -1
        const val ON = 1
        const val OFF = 2
        const val NO_DISCOVERABLE = 3
    }
}
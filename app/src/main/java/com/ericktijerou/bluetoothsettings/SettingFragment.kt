package com.ericktijerou.bluetoothsettings

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.GuidanceStylist
import androidx.leanback.widget.GuidedAction

class SettingFragment : GuidedStepSupportFragment() {

    private val bAdapter by lazy { BluetoothAdapter.getDefaultAdapter() }

    private fun addAction(
        actions: MutableList<GuidedAction>,
        id: Long,
        title: String,
        desc: String
    ) {
        actions.add(
            GuidedAction.Builder(context)
                .id(id)
                .title(title)
                .description(desc)
                .build()
        )
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        addAction(actions, ActionType.ON.toLong(), getString(R.string.on), "")
        addAction(actions, ActionType.OFF.toLong(), getString(R.string.off), "")
        addAction(actions, ActionType.NO_DISCOVERABLE.toLong(), getString(R.string.no_discoverable), "")
    }

    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        val title = getString(R.string.two_opts_title)
        val breadcrumb = getString(R.string.two_opts_breadcrumb)
        val description = getString(R.string.two_opts_desc)
        val icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_bluetooth)
        return GuidanceStylist.Guidance(title, description, breadcrumb, icon)
    }

    override fun onGuidedActionClicked(action: GuidedAction?) {
        super.onGuidedActionClicked(action)
        when (action?.id?.toInt()) {
            ActionType.ON -> onClickTurnOn()
            ActionType.OFF -> onClickTurnOff()
            ActionType.NO_DISCOVERABLE -> onClickDiscovering()
        }
    }

    private fun onClickTurnOn() {
        bAdapter?.let {
            if (!bAdapter.isEnabled) {
                startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1)
                Toast.makeText(context, "Bluetooth Turned ON", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(context, "Bluetooth Not Supported", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClickTurnOff() {
        bAdapter?.disable()
        Toast.makeText(context, "Bluetooth Turned OFF", Toast.LENGTH_SHORT).show()
    }

    private fun onClickDiscovering() {
        bAdapter?.let {
            if (!bAdapter.isDiscovering) {
                startActivityForResult(
                    Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).putExtra(
                        BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
                        1
                    ), 1
                )
                Toast.makeText(context, "Making Device Discoverable", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(context, "Bluetooth Not Supported", Toast.LENGTH_SHORT).show()
        }
    }
}


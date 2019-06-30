package com.example.hangboard.components.beeps

import android.media.ToneGenerator
import com.facebook.litho.sections.Children
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.annotations.GroupSectionSpec
import com.facebook.litho.sections.annotations.OnCreateChildren
import com.facebook.litho.sections.common.SingleComponentSection


@GroupSectionSpec
object BeepListSpec {



    fun getBeeps() : MutableMap<Int, String?> {
        val tones  = mutableMapOf<Int, String?>()

        tones.put(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, "TONE_CDMA_ALERT_CALL_GUARD") // Perfect double
        tones.put(ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE, "TONE_CDMA_KEYPAD_VOLUME_KEY_LITE") //perfect one timer
        tones.put(ToneGenerator.TONE_CDMA_PIP, "TONE_CDMA_PIP")




        //3
        tones.put(ToneGenerator.TONE_SUP_PIP, "TONE_SUP_PIP")
        tones.put(ToneGenerator.TONE_SUP_CONFIRM, "TONE_SUP_CONFIRM")





        //2
        tones.put(ToneGenerator.TONE_SUP_RADIO_NOTAVAIL, "TONE_SUP_RADIO_NOTAVAIL") // double good
        tones.put(ToneGenerator.TONE_PROP_BEEP2, "TONE_PROP_BEEP2") // good double
        tones.put(ToneGenerator.TONE_PROP_ACK, "TONE_PROP_ACK")


        //1
        tones.put(ToneGenerator.TONE_CDMA_ONE_MIN_BEEP, "TONE_CDMA_ONE_MIN_BEEP") //good
        tones.put(ToneGenerator.TONE_CDMA_NETWORK_BUSY_ONE_SHOT, "TONE_CDMA_NETWORK_BUSY_ONE_SHOT") // good
        tones.put(ToneGenerator.TONE_SUP_RADIO_ACK, "TONE_SUP_RADIO_ACK") //good
        tones.put(ToneGenerator.TONE_PROP_BEEP, "TONE_PROP_BEEP") // good
        tones.put(ToneGenerator.TONE_PROP_PROMPT, "TONE_PROP_PROMPT")
        tones.put(ToneGenerator.TONE_CDMA_PRESSHOLDKEY_LITE, "TONE_CDMA_PRESSHOLDKEY_LITE")
        tones.put(ToneGenerator.TONE_CDMA_ABBR_ALERT, "TONE_CDMA_ABBR_ALERT")


        tones.put(ToneGenerator.TONE_DTMF_0, "TONE_DTMF_0")
        tones.put(ToneGenerator.TONE_DTMF_1, "TONE_DTMF_1")
        tones.put(ToneGenerator.TONE_DTMF_2, "TONE_DTMF_2")
        tones.put(ToneGenerator.TONE_DTMF_3, "TONE_DTMF_3")
        tones.put(ToneGenerator.TONE_DTMF_4, "TONE_DTMF_4")
        tones.put(ToneGenerator.TONE_DTMF_5, "TONE_DTMF_5")
        tones.put(ToneGenerator.TONE_DTMF_6, "TONE_DTMF_6")
        tones.put(ToneGenerator.TONE_DTMF_7, "TONE_DTMF_7")
        tones.put(ToneGenerator.TONE_DTMF_8, "TONE_DTMF_8")
        tones.put(ToneGenerator.TONE_DTMF_9, "TONE_DTMF_9")
        tones.put(ToneGenerator.TONE_DTMF_A, "TONE_DTMF_A")
        tones.put(ToneGenerator.TONE_DTMF_B, "TONE_DTMF_B")
        tones.put(ToneGenerator.TONE_DTMF_C, "TONE_DTMF_C")
        tones.put(ToneGenerator.TONE_DTMF_D, "TONE_DTMF_D")

//        tones.put(ToneGenerator.TONE_PROP_NACK, "TONE_PROP_NACK")
//        tones.put(ToneGenerator.TONE_SUP_INTERCEPT, "TONE_SUP_INTERCEPT")
//        tones.put(ToneGenerator.TONE_SUP_INTERCEPT_ABBREV, "TONE_SUP_INTERCEPT_ABBREV")
//        tones.put(ToneGenerator.TONE_SUP_CONGESTION_ABBREV, "TONE_SUP_CONGESTION_ABBREV")
//        tones.put(ToneGenerator.TONE_CDMA_DIAL_TONE_LITE, "TONE_CDMA_DIAL_TONE_LITE")
//        tones.put(ToneGenerator.TONE_CDMA_NETWORK_USA_RINGBACK, "TONE_CDMA_NETWORK_USA_RINGBACK")
//        tones.put(ToneGenerator.TONE_CDMA_INTERCEPT, "TONE_CDMA_INTERCEPT")
//        tones.put(ToneGenerator.TONE_CDMA_ABBR_INTERCEPT, "TONE_CDMA_ABBR_INTERCEPT")
//        tones.put(ToneGenerator.TONE_CDMA_REORDER, "TONE_CDMA_REORDER")
//        tones.put(ToneGenerator.TONE_CDMA_ABBR_REORDER, "TONE_CDMA_ABBR_REORDER")
//        tones.put(ToneGenerator.TONE_CDMA_NETWORK_BUSY, "TONE_CDMA_NETWORK_BUSY")
//        tones.put(ToneGenerator.TONE_CDMA_CONFIRM, "TONE_CDMA_CONFIRM")
//        tones.put(ToneGenerator.TONE_CDMA_ANSWER, "TONE_CDMA_ANSWER")
//        tones.put(ToneGenerator.TONE_CDMA_NETWORK_CALLWAITING, "TONE_CDMA_NETWORK_CALLWAITING")
//        tones.put(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_NORMAL, "TONE_CDMA_CALL_SIGNAL_ISDN_NORMAL")
//        tones.put(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP, "TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP")
//        tones.put(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_SP_PRI, "TONE_CDMA_CALL_SIGNAL_ISDN_SP_PRI")
//        tones.put(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT3, "TONE_CDMA_CALL_SIGNAL_ISDN_PAT3")
//        tones.put(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PING_RING, "TONE_CDMA_CALL_SIGNAL_ISDN_PING_RING")
//        tones.put(ToneGenerator.TONE_CDMA_HIGH_PBX_S_X4, "TONE_CDMA_HIGH_PBX_S_X4")
//        tones.put(ToneGenerator.TONE_CDMA_MED_PBX_S_X4, "TONE_CDMA_MED_PBX_S_X4")
//        tones.put(ToneGenerator.TONE_CDMA_LOW_PBX_S_X4, "TONE_CDMA_LOW_PBX_S_X4")
//        tones.put(ToneGenerator.TONE_CDMA_ALERT_INCALL_LITE, "TONE_CDMA_ALERT_INCALL_LITE")
//        tones.put(ToneGenerator.TONE_CDMA_EMERGENCY_RINGBACK, "TONE_CDMA_EMERGENCY_RINGBACK")
//        tones.put(ToneGenerator.TONE_CDMA_SOFT_ERROR_LITE, "TONE_CDMA_SOFT_ERROR_LITE")
//        tones.put(ToneGenerator.TONE_CDMA_CALLDROP_LITE, "TONE_CDMA_CALLDROP_LITE")
//
//        tones.put(ToneGenerator.TONE_CDMA_SIGNAL_OFF, "TONE_CDMA_SIGNAL_OFF")

//        tones.put(ToneGenerator.TONE_SUP_DIAL, "TONE_SUP_DIAL")
//        tones.put(ToneGenerator.TONE_SUP_BUSY, "TONE_SUP_BUSY")
//        tones.put(ToneGenerator.TONE_SUP_CONGESTION, "TONE_SUP_CONGESTION")
//        tones.put(ToneGenerator.TONE_SUP_ERROR, "TONE_SUP_ERROR")
//        tones.put(ToneGenerator.TONE_SUP_CALL_WAITING, "TONE_SUP_CALL_WAITING")
//        tones.put(ToneGenerator.TONE_SUP_RINGTONE, "TONE_SUP_RINGTONE")
//        tones.put(ToneGenerator.TONE_CDMA_ALERT_NETWORK_LITE, "TONE_CDMA_ALERT_NETWORK_LITE") //ok
//        tones.put(ToneGenerator.TONE_CDMA_ALERT_AUTOREDIAL_LITE, "TONE_CDMA_ALERT_AUTOREDIAL_LITE") // ok
//        tones.put(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT5, "TONE_CDMA_CALL_SIGNAL_ISDN_PAT5")
//        tones.put(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT6, "TONE_CDMA_CALL_SIGNAL_ISDN_PAT6")
//        tones.put(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT7, "TONE_CDMA_CALL_SIGNAL_ISDN_PAT7")
//        tones.put(ToneGenerator.TONE_CDMA_HIGH_L, "TONE_CDMA_HIGH_L")
//        tones.put(ToneGenerator.TONE_CDMA_MED_L, "TONE_CDMA_MED_L")
//        tones.put(ToneGenerator.TONE_CDMA_LOW_L, "TONE_CDMA_LOW_L")
//        tones.put(ToneGenerator.TONE_CDMA_HIGH_SS, "TONE_CDMA_HIGH_SS")
//        tones.put(ToneGenerator.TONE_CDMA_MED_SS, "TONE_CDMA_MED_SS")
//        tones.put(ToneGenerator.TONE_CDMA_LOW_SS, "TONE_CDMA_LOW_SS")
//        tones.put(ToneGenerator.TONE_CDMA_HIGH_SSL, "TONE_CDMA_HIGH_SSL")
//        tones.put(ToneGenerator.TONE_CDMA_MED_SSL, "TONE_CDMA_MED_SSL")
//        tones.put(ToneGenerator.TONE_CDMA_LOW_SSL, "TONE_CDMA_LOW_SSL")
//        tones.put(ToneGenerator.TONE_CDMA_HIGH_SS_2, "TONE_CDMA_HIGH_SS_2")
//        tones.put(ToneGenerator.TONE_CDMA_MED_SS_2, "TONE_CDMA_MED_SS_2")
//        tones.put(ToneGenerator.TONE_CDMA_LOW_SS_2, "TONE_CDMA_LOW_SS_2")
//        tones.put(ToneGenerator.TONE_CDMA_HIGH_SLS, "TONE_CDMA_HIGH_SLS")
//        tones.put(ToneGenerator.TONE_CDMA_MED_SLS, "TONE_CDMA_MED_SLS")
//        tones.put(ToneGenerator.TONE_CDMA_LOW_SLS, "TONE_CDMA_LOW_SLS")
//        tones.put(ToneGenerator.TONE_CDMA_HIGH_S_X4, "TONE_CDMA_HIGH_S_X4")
//        tones.put(ToneGenerator.TONE_CDMA_MED_S_X4, "TONE_CDMA_MED_S_X4")
//        tones.put(ToneGenerator.TONE_CDMA_LOW_S_X4, "TONE_CDMA_LOW_S_X4")
//        tones.put(ToneGenerator.TONE_CDMA_HIGH_PBX_L, "TONE_CDMA_HIGH_PBX_L")
//        tones.put(ToneGenerator.TONE_CDMA_MED_PBX_L, "TONE_CDMA_MED_PBX_L")
//        tones.put(ToneGenerator.TONE_CDMA_LOW_PBX_L, "TONE_CDMA_LOW_PBX_L")
//        tones.put(ToneGenerator.TONE_CDMA_HIGH_PBX_SS, "TONE_CDMA_HIGH_PBX_SS")
//        tones.put(ToneGenerator.TONE_CDMA_MED_PBX_SS, "TONE_CDMA_MED_PBX_SS")
//        tones.put(ToneGenerator.TONE_CDMA_LOW_PBX_SS, "TONE_CDMA_LOW_PBX_SS")
//        tones.put(ToneGenerator.TONE_CDMA_HIGH_PBX_SSL, "TONE_CDMA_HIGH_PBX_SSL")
//        tones.put(ToneGenerator.TONE_CDMA_MED_PBX_SSL, "TONE_CDMA_MED_PBX_SSL")
//        tones.put(ToneGenerator.TONE_CDMA_LOW_PBX_SSL, "TONE_CDMA_LOW_PBX_SSL")
//        tones.put(ToneGenerator.TONE_CDMA_HIGH_PBX_SLS, "TONE_CDMA_HIGH_PBX_SLS")
//        tones.put(ToneGenerator.TONE_CDMA_MED_PBX_SLS, "TONE_CDMA_MED_PBX_SLS")
//        tones.put(ToneGenerator.TONE_CDMA_LOW_PBX_SLS, "TONE_CDMA_LOW_PBX_SLS")

        return tones
    }


    @OnCreateChildren
    fun onCreateChildren(c: SectionContext): Children {
        val builder = Children.create()

        val beeps = getBeeps()


        for (beep in beeps) {

            builder.child(
                SingleComponentSection.create(c)
                    .component(beep.value?.let { Beep.create(c).name(it).tone(beep.key).build() })
            )
        }


        return builder.build()
    }


}



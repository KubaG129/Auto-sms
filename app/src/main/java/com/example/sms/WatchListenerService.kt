package com.example.sms

import android.telephony.SmsManager
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService

class WatchListenerService : WearableListenerService() {

    override fun onMessageReceived(messageEvent: MessageEvent) {
        val nr_tel = ""//place your phone number here
        if (messageEvent.path == "/send_sms_1") {
            wyslijSms(nr_tel, "Brama")
        }
        if (messageEvent.path == "/send_sms_2") {
            wyslijSms(nr_tel, "Wlacz")
        }
        if (messageEvent.path == "/send_sms_3") {
            wyslijSms(nr_tel, "Wylacz")
        }
        if (messageEvent.path == "/send_sms_4") {
            wyslijSms(nr_tel, "Garaz")
        }
    }

    private fun wyslijSms(numer: String, wiadomosc: String) {
        try {
            val smsManager = getSystemService(SmsManager::class.java)
            smsManager.sendTextMessage(numer, null, wiadomosc, null, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
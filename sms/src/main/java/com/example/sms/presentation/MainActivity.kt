/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.example.sms.presentation

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.ButtonDefaults
import androidx.wear.compose.material3.EdgeButton
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.SurfaceTransformation
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import com.example.sms.R

import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSms1 = findViewById<Button>(R.id.btnSms1)
        val btnSms2 = findViewById<Button>(R.id.btnSms2)
        val btnSms3 = findViewById<Button>(R.id.btnSms3)
        val btnSms4 = findViewById<Button>(R.id.btnSms4)

        // Podpinamy różne ścieżki pod różne przyciski
        btnSms1.setOnClickListener { wyslijKomende("/send_sms_1") }
        btnSms2.setOnClickListener { wyslijKomende("/send_sms_2") }
        btnSms3.setOnClickListener { wyslijKomende("/send_sms_3") }
        btnSms4.setOnClickListener { wyslijKomende("/send_sms_4") }
    }

    private fun wyslijKomende(sciezka: String) {
        lifecycleScope.launch {
            try {
                val nodes = Wearable.getNodeClient(this@MainActivity).connectedNodes.await()
                for (node in nodes) {
                    Wearable.getMessageClient(this@MainActivity)
                        .sendMessage(node.id, sciezka, byteArrayOf())
                        .await()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

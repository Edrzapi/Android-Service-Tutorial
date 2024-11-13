package uk.co.devfoundry.servicetutorial

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.co.devfoundry.servicetutorial.ui.theme.ServiceTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ButtonSupport(
                modifier = Modifier.fillMaxSize(),
                onStartService = { startService(Intent(this, MyBackgroundService::class.java)) },
                onStopService = { stopService(Intent(this, MyBackgroundService::class.java)) },
                boundNavigation = {
                    startActivity(Intent(this, CountActivity::class.java))
                }
            )


        }
    }
}

@Composable
fun ButtonSupport(
    modifier: Modifier = Modifier,
    onStartService: () -> Unit,
    onStopService: () -> Unit,
    boundNavigation: () -> Unit
) {

    Column(
        modifier
            .fillMaxSize()
            .padding(100.dp, 250.dp)
            .border(5.dp, color = Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Vertically center the buttons within Column
    ) {

        // First row: Start Service button
        Row {

            Button(
                onClick = { onStartService() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Start Service")
            }
        }

        // Second row: Stop Service button
        Row {
            Button(
                onClick = { onStopService() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Stop Service")
            }
        }

        Row {
            Button(
                onClick = { boundNavigation() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Go to bound service")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ServiceTutorialTheme {

        ButtonSupport(
            modifier = Modifier.fillMaxSize(),
            onStartService = {},
            onStopService = {},
            boundNavigation = {})

    }
}
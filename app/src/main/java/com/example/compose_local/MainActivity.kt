package com.example.compose_local

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_local.ui.theme.Compose_localTheme

/**
 * Links: https://developer.android.com/jetpack/compose/compositionlocal
 *        https://medium.com/geekculture/jetpack-compose-compositionlocal-what-you-need-to-know-979a4aef6412
 * **/

val LocalFontStyle = compositionLocalOf { FontStyle.Normal }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_localTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // When the device rotates, we want to change the font of the app.
                    // Now, we don't want to pass in FontStyle as a parameter to Greeting() composable,
                    // so we are using ComposeLocal to pass FontStyle implicitly.
                    val fontStyle = when (LocalConfiguration.current.orientation) {
                        Configuration.ORIENTATION_LANDSCAPE -> {
                            FontStyle.Italic
                        } else -> {
                            FontStyle.Normal
                        }
                    }

                    CompositionLocalProvider(LocalFontStyle provides fontStyle) {
                        Greeting("Jeff Emuveyan")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        fontStyle = LocalFontStyle.current
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose_localTheme {
        Greeting("Android")
    }
}
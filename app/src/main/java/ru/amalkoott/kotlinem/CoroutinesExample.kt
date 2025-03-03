package ru.amalkoott.kotlinem

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import ru.amalkoott.kotlinem.utils.throttleFirst
import ru.amalkoott.kotlinem.utils.throttleLast

class CoroutinesExample : Example {

    @Composable
    override fun draw() {
        var isExpanded by remember { mutableStateOf(false) }
        val firstClickFlow = remember { MutableStateFlow(0) }
        val lastClickFlow = remember { MutableStateFlow(0) }

        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        LaunchedEffect(lifecycleOwner) {
            firstClickFlow
                .throttleFirst(5000)
                .collect{
                   Toast.makeText(context,"You clicked $it times", Toast.LENGTH_SHORT).show()
                }

            lastClickFlow
                .throttleLast(5000)
                .collect{
                    Toast.makeText(context,"You clicked $it times", Toast.LENGTH_SHORT).show()
                }
        }

        Button(
            onClick = { isExpanded = !isExpanded}
        ) {
            Text("Coroutines")
        }
        if (isExpanded){
            Row{
                Button(
                    onClick = {
                        firstClickFlow.value += 1
                    }
                ) {
                    Text(
                        text = "Click me fast to throttle first!"
                    )
                }
                Button(
                    onClick = {
                        firstClickFlow.value += 1
                    }
                ) {
                    Text(
                        text = "Click me fast to throttle last!"
                    )
                }
            }
        }
    }
}
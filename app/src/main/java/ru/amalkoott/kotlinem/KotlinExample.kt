package ru.amalkoott.kotlinem

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class KotlinExample : Example{
    @Composable
    override fun draw() {
        var isKotlinExpanded by remember { mutableStateOf(false) }

        Button(
            onClick = { isKotlinExpanded = !isKotlinExpanded}
        ) {
            Text("Kotlin")
        }
        if (isKotlinExpanded){
            Row{
                Button(
                    onClick = { findInt() }
                ) {
                    Text(
                        text = "Find Int"
                    )
                }
                Button(
                    onClick = {
                        shakerSort()
                        shakerSortWithNull()
                    }
                ) {
                    Text(
                        text = "Shaker Sort"
                    )
                }
            }
        }
    }


}
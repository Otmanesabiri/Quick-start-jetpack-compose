package fr.iutlan.tp4

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

data class Feu3State(val rouge: Boolean = true, val orange: Boolean = false, val vert: Boolean = false)

class Feu3ViewModel : ViewModel() {
    private val _state = mutableStateOf(Feu3State())
    val state: Feu3State get() = _state.value

    fun suivant() {
        _state.value = when {
            state.rouge -> Feu3State(rouge = false, orange = false, vert = true)
            state.vert -> Feu3State(rouge = false, orange = true, vert = false)
            else -> Feu3State(rouge = true, orange = false, vert = false)
        }
    }
}

@Composable
fun Feu3View(state: Feu3State) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .width(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Feu ${if (state.rouge) "Rouge" else if (state.orange) "Orange" else "Vert"}")
        
        // Cercle rouge
        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .background(if (state.rouge) Color.Red else Color.Gray, shape = androidx.compose.foundation.shape.CircleShape)
        )
        
        // Cercle orange
        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .background(if (state.orange) Color.Yellow else Color.Gray, shape = androidx.compose.foundation.shape.CircleShape)
        )
        
        // Cercle vert
        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .background(if (state.vert) Color.Green else Color.Gray, shape = androidx.compose.foundation.shape.CircleShape)
        )
    }
}

@Preview
@Composable
fun Feu3ViewPreview() {
    val state = remember { mutableStateOf(Feu3State()) }
    Feu3View(state.value)
}
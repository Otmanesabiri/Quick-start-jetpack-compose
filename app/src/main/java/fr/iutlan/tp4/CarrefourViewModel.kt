package fr.iutlan.tp4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

// Modèle pour représenter l'état d'un carrefour à 4 feux
data class CarrefourState(
    val feuNord: Feu3State = Feu3State(rouge = true),
    val feuSud: Feu3State = Feu3State(rouge = true),
    val feuEst: Feu3State = Feu3State(rouge = true),
    val feuOuest: Feu3State = Feu3State(rouge = true),
    val phase: Int = 0
)

class CarrefourViewModel : ViewModel() {
    private val _state = mutableStateOf(
        // Phase initiale: Nord-Sud sont verts, Est-Ouest sont rouges
        CarrefourState(
            feuNord = Feu3State(rouge = false, vert = true),
            feuSud = Feu3State(rouge = false, vert = true),
            feuEst = Feu3State(rouge = true),
            feuOuest = Feu3State(rouge = true)
        )
    )
    val state: CarrefourState get() = _state.value

    fun suivant() {
        val currentPhase = (state.phase + 1) % 4
        
        _state.value = when(currentPhase) {
            0 -> // Nord-Sud verts, Est-Ouest rouges
                CarrefourState(
                    feuNord = Feu3State(rouge = false, vert = true),
                    feuSud = Feu3State(rouge = false, vert = true),
                    feuEst = Feu3State(rouge = true),
                    feuOuest = Feu3State(rouge = true),
                    phase = currentPhase
                )
            1 -> // Nord-Sud orange, Est-Ouest rouges
                CarrefourState(
                    feuNord = Feu3State(rouge = false, orange = true),
                    feuSud = Feu3State(rouge = false, orange = true),
                    feuEst = Feu3State(rouge = true),
                    feuOuest = Feu3State(rouge = true),
                    phase = currentPhase
                )
            2 -> // Nord-Sud rouges, Est-Ouest verts
                CarrefourState(
                    feuNord = Feu3State(rouge = true),
                    feuSud = Feu3State(rouge = true),
                    feuEst = Feu3State(rouge = false, vert = true),
                    feuOuest = Feu3State(rouge = false, vert = true),
                    phase = currentPhase
                )
            3 -> // Nord-Sud rouges, Est-Ouest orange
                CarrefourState(
                    feuNord = Feu3State(rouge = true),
                    feuSud = Feu3State(rouge = true),
                    feuEst = Feu3State(rouge = false, orange = true),
                    feuOuest = Feu3State(rouge = false, orange = true),
                    phase = currentPhase
                )
            else -> state // Ne devrait jamais arriver
        }
    }
}

@Composable
fun CarrefourView(state: CarrefourState) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Carrefour - Phase ${state.phase}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Feu Nord
        Text("Nord")
        Feu3View(state.feuNord)
        
        // Feux Est-Ouest
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Ouest")
                Feu3View(state.feuOuest)
            }
            
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Est")
                Feu3View(state.feuEst)
            }
        }
        
        // Feu Sud
        Text("Sud")
        Feu3View(state.feuSud)
    }
}

@Preview
@Composable
fun CarrefourViewPreview() {
    val state = remember {
        mutableStateOf(
            CarrefourState(
                feuNord = Feu3State(rouge = false, vert = true),
                feuSud = Feu3State(rouge = false, vert = true),
                feuEst = Feu3State(rouge = true),
                feuOuest = Feu3State(rouge = true)
            )
        )
    }
    CarrefourView(state.value)
}
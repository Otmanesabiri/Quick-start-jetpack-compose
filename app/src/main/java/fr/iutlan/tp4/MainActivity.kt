package fr.iutlan.tp4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.iutlan.tp4.ui.theme.TP4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP4Theme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Bonjour tout le monde !",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.Magenta
            )
            
            Accueil(name = "Étudiant")
            
            AccueilMultiple(names = listOf("Alice", "Bob", "Charlie"))
            
            // Intégration du feu tricolore avec ViewModel
            val feuViewModel: Feu3ViewModel = viewModel()
            Feu3View(state = feuViewModel.state)
            Button(onClick = { feuViewModel.suivant() }) {
                Text("Changer")
            }
            
            // Intégration du carrefour
            val carrefourViewModel: CarrefourViewModel = viewModel()
            CarrefourView(state = carrefourViewModel.state)
            Button(onClick = { carrefourViewModel.suivant() }) {
                Text("Changer le carrefour")
            }
        }
    }
}

@Composable
fun Accueil(name: String) {
    Column {
        Text(text = "Bonjour $name", fontSize = 20.sp)
        Text(text = "Bienvenue dans Compose", color = Color.Blue)
    }
}

@Composable
fun AccueilMultiple(names: List<String>) {
    Column {
        for (name in names) {
            Text(text = "Bonjour $name !", modifier = Modifier.padding(4.dp))
        }
    }
}

// Version horizontale (Row) de l'accueil
@Composable
fun AccueilRow(name: String) {
    Row {
        Text(text = "Bonjour $name", fontSize = 20.sp)
        Text(text = "Bienvenue dans Compose", color = Color.Blue, modifier = Modifier.padding(start = 8.dp))
    }
}

@Preview
@Composable
fun AccueilPreview() {
    Accueil(name = "Test")
}

@Preview
@Composable
fun AccueilMultiplePreview() {
    AccueilMultiple(listOf("Alice", "Bob", "Charlie"))
}

@Preview
@Composable
fun AccueilRowPreview() {
    AccueilRow(name = "Test")
}

@Preview
@Composable
fun MainContentPreview() {
    TP4Theme {
        MainContent()
    }
}
package br.edu.ifpb.pdm.yuri.galeriadearte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifpb.pdm.yuri.galeriadearte.ui.theme.GaleriaDeArteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GaleriaDeArteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtGalleryScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtGalleryScreen(modifier: Modifier = Modifier) {
    // Lista de imagens e descrições
    val artworks = listOf(
        Artwork(R.drawable.ic_launcher_background, "Obra 1", "Artista 1", 2010),
        Artwork(R.drawable.ic_launcher_foreground, "Obra 2", "Artista 2", 2020),
        Artwork(R.drawable.ic_launcher_background, "Obra 3", "Artista 3", 2021)
    )

    // Estado para armazenar a posição atual
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Imagem da obra de arte
        Image(
            painter = painterResource(id = artworks[currentIndex].imageResId),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Texto da descrição da obra
        Text(
            text = artworks[currentIndex].title,
            fontSize = 24.sp
        )
        Text(
            text = "${artworks[currentIndex].artist}, ${artworks[currentIndex].year}",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botões de controle
        Row {
            Button(onClick = {
                if (currentIndex > 0) currentIndex--
            }) {
                Text("Anterior")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                if (currentIndex < artworks.size - 1) currentIndex++
            }) {
                Text("Próxima")
            }
        }
    }
}

// Modelo para armazenar dados da obra de arte
data class Artwork(val imageResId: Int, val title: String, val artist: String, val year: Int)

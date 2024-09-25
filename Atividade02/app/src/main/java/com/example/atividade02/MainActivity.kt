package com.example.atividade02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Updater
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.atividade02.ui.theme.Atividade02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun JogadorUI(jogador: Jogador, onJogadorUpdate: (Jogador) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Nome do Jogador
        var nomeState by remember { mutableStateOf(TextFieldValue(jogador.nome)) }
        BasicTextField(
            value = nomeState,
            onValueChange = {
                nomeState = it
                onJogadorUpdate(jogador.copy(nome = it.text))
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))

        // Level do Jogador
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Level: ${jogador.level}")
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { if (jogador.level > 1) onJogadorUpdate(jogador.copy(level = jogador.level - 1)) }) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { if (jogador.level < 10) onJogadorUpdate(jogador.copy(level = jogador.level + 1)) }) {
                Text("+")
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))

        // Bonus de Equipamento
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Bônus de Equipamento: ${jogador.bonusEquipamento}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { jogador.bonusEquipamento--; onJogadorUpdate(jogador) }) {
                Text("-")
            }
            Button(onClick = { jogador.bonusEquipamento++; onJogadorUpdate(jogador) }) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Modificadores
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Modificadores: ${jogador.modificadores}")
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onJogadorUpdate(jogador.copy(modificadores = jogador.modificadores - 1)) }) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onJogadorUpdate(jogador.copy(modificadores = jogador.modificadores + 1)) }) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Poder Total
        Text("Poder Total: ${jogador.poderTotal}")
    }
}

@Composable
fun App() {
    var jogadores by remember { mutableStateOf(
        List(6) { Jogador("Jogador ${it + 1}", 1, 0, 0) }
    ) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        jogadores.forEachIndexed { index, jogador ->
            JogadorUI(jogador = jogador) { updatedJogador ->
                jogadores = jogadores.toMutableList().apply { set(index, updatedJogador) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    App()
}
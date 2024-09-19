package com.androidcourse.exploracioncomponentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidcourse.exploracioncomponentes.ui.theme.ExploracionComponentesTheme
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowColumn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExploracionComponentesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExploracionComponentesTheme {
        FlowColumnExample()
    }
}

@Composable
fun LazyColumnEjemplo(){
    LazyColumn { // Una lista colmna larga
        items(5) { index -> // Funcion Lambda, se ejecuta sobre cada elemento
            // Establecemos la separacon de 5 dp para cada elemento
            Text("Ejempl $index", modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
fun LazyRowEjemplo(){
    // Una lista horizontal, ideal para carruseles o elementos desplazados e n horizontal
    // segun la cantidad de  "items" que elijamos
    LazyRow { items(10) { index ->

        Box( //Establecemos un contenendor con modificaciones tamaño, color de fondo
            modifier = Modifier
                .size(
                    width = 75.dp,
                    height = 35.dp
                ) //Definimos alto y ancho de la caja de cada elemento
                .background(Color.DarkGray) // Color de fondo
                .padding(5.dp)
        ) {
            Text(text = "LzRow $index", color = Color.White) //
        }
      }
    }
}

@Composable
fun GridEjemplo(){
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(15) { index -> // Expresion Lambda
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.DarkGray)
                    .padding(10.dp)
                ) {
                Text(
                    text = "Grid $index", color = Color.White
                )
            }
        }
    }
}

@Composable
fun GridExample() {
    //para crear una cuadrícula de elementos
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), //columnas
        content = {
            items(7) { index ->
                Text(text = "Grid Item #index ", modifier = Modifier.padding(7.dp))
            }
        }
    )
}

@Composable
fun ConstraintLayoutExample() { // Crea un diseño con un botón y un texto usando ConstraintLayout.
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        // Crea las referencias para los elementos
        val (button, text) = createRefs()

        Button(
            onClick = { /* Acción del botón */ },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start)
            }
        ) {
            Text("Button")
        }

        Text(
            text = "Hola ConstraintLayout",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(button.bottom, margin = 16.dp)
                start.linkTo(parent.start)
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() { // Crea un diseño básico con una barra superior y contenido principal utilizando Scaffold.

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scaffold Ejemplo") }
            )
        },
        content = { paddingValues ->
            Text(
                "Hola, scaffold!",
                modifier = Modifier.padding(paddingValues).padding(16.dp)
            )
        }
    )
}

@Composable
fun SurfaceExample() { // Surface permite definir el fondo, la elevación y el estilo de sus elementos hijos.

    Surface(
        modifier = Modifier.padding(8.dp),
        color = MaterialTheme.colors.primary
    ) {
        Text(
            text = "Hello, Surface!",
            modifier = Modifier.padding(16.dp),
            color = Color.White
        )
    }
}

@Composable
fun ChipExample(text: String) { // Se puede crear un componente Chip personalizado para mostrar pequeños elementos de información.
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = Color.White
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BackdropScaffoldExample() { // BackdropScaffold permite crear una interfaz con un fondo y un contenido frontal.
    BackdropScaffold(
        appBar = {
            TopAppBar(
                title = { Text("Backdrop Scaffold") }
            )
        },
        backLayerContent = {
            Text("Back Layer Content", modifier = Modifier.padding(16.dp))
        },
        frontLayerContent = {
            Text("Front Layer Content", modifier = Modifier.padding(16.dp))
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowExample() { // FlowRow distribuye los elementos en filas de forma fluida, ajustándose al espacio disponible.

    FlowRow {
        for (i in 1..10) {
            Text("Item $i", modifier = Modifier.padding(8.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowColumnExample() { // FlowColumn distribuye los elementos en columnas de forma fluida.
    FlowColumn {
        for (i in 1..10) {
            Text("Item $i", modifier = Modifier.padding(8.dp))
        }
    }
}

package com.thorugoh.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thorugoh.notes.components.SubNote
import com.thorugoh.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Main(){
    NotesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            data class Note (
                val title: String,
                val content: String,
                val done: Boolean
            )
            LazyColumn {
                stickyHeader {
                    Row(
                        Modifier.fillMaxWidth().background(Color.White).padding(end = 16.dp, start = 16.dp, top = 16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        AddSubNote()
                    }

                }
                items(
                    count = 1
                ) {
                    SubNote()
                }
            }
        }
    }
}

@Composable
fun AddSubNote(){
    Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 16.dp)) {
        Text(text = "Add SubNote")
    }
}

@Preview
@Composable
fun Preview(){
    Main()
}
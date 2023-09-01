package com.thorugoh.notes

import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thorugoh.notes.components.SubNote
import com.thorugoh.notes.ui.theme.NotesTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val done: Boolean
)


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Main() {
    NotesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val focusManager = LocalFocusManager.current

            var notes = remember { mutableStateListOf<Note>().apply {
                add(Note("teste-1", "title", "content 1", true))
                add(Note("teste-2", "title 2", "content 2", false))
            } }

            fun updateNoteTitle(index: Int, text: String) {
                val note = notes[index]
                notes[index] = note.copy(title = text)
            }

            fun updateNoteContent(index: Int, text: String) {
                val note = notes[index]
                notes[index] = note.copy(content = text)
            }

            fun selectNote(index: Int, note: Note){
                notes[index] = note.copy(done = !note.done)
            }

            LazyColumn {
                stickyHeader {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(end = 16.dp, start = 16.dp, top = 16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        AddSubNote {
                            notes.add(
                                Note(
                                    "teste-${notes.size + 1}",
                                    "",
                                    "",
                                    false
                                )
                            )
                        }
                    }
                }
                itemsIndexed(
                    items = notes,
                ) {index, it ->
                    val (id, title, content, selected) = it
                    SubNote(title, content, selected,
                        onSelect = {
                            focusManager.clearFocus()
                            selectNote(index, it)
                        },
                        onTitleChange = {text ->
                            updateNoteTitle(index, text)
                        },
                        onContentChange = { text ->
                            updateNoteContent(index, text)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AddSubNote(onClick: () -> Unit = {}) {
    Button(onClick, modifier = Modifier.padding(start = 16.dp)) {
        Text(text = "Add SubNote")
    }
}

@Preview
@Composable
fun Preview() {
    Main()
}
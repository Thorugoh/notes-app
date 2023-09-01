package com.thorugoh.notes.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubNote(
    title: String,
    content: String,
    selected: Boolean,
    onSelect: ()-> Unit = {},
    onTitleChange: (text: String) -> Unit = {},
    onContentChange: (text: String) -> Unit = {}
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(
                color = Color(0xFFF7F6D4),
                shape = RoundedCornerShape(20.dp)
            )
            .height(124.dp)
            .padding(12.dp),


        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(selected, onClick = onSelect,
                Modifier
                    .height(16.dp)
                    .padding(0.dp)
            )
            BasicTextField(
                value = title,
                onValueChange = onTitleChange,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (selected) TextDecoration.LineThrough else TextDecoration.None
                ),

                )
        }
        Divider(
            modifier =
            Modifier
                .padding(vertical = 8.dp)
                .alpha(0.25F)
                .fillMaxWidth()

        )
        Row() {
            BasicTextField(
                value = content,
                onValueChange = onContentChange,
                modifier = Modifier.fillMaxSize(),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    textDecoration = if (selected) TextDecoration.LineThrough else TextDecoration.None
                ),
            )
        }
    }
}

@Preview
@Composable
fun SubNotePreview() {
    SubNote("teste", "content", true)
}
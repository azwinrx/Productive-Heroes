package com.azwin.dotask.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azwin.dotask.R
import com.azwin.dotask.View.Components.GameButton
import com.azwin.dotask.ViewModel.Fight.TimerViewModel
import com.azwin.dotask.ViewModel.QuestViewModel
import com.azwin.dotask.ui.theme.jersey25

@Composable
fun QuestView(questViewModel: QuestViewModel = viewModel(), timerViewModel: TimerViewModel = viewModel()) {
    val quests by questViewModel.quests.collectAsState()
    var newQuestText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.backgroundarena2),
            contentDescription = "Background 2",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Input field for new quest
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = newQuestText,
                    onValueChange = { if (it.length <= 26) newQuestText = it },
                    label = { Text(text = "New Quest", fontFamily = jersey25) },
                    modifier = Modifier.weight(1f).padding(top = 16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White.copy(alpha = 0.7f),
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
                IconButton(
                    onClick = {
                        if (newQuestText.isNotBlank()) {
                            questViewModel.addQuest(newQuestText)
                            newQuestText = ""
                        }
                    },
                    modifier = Modifier
                        .padding(start = 8.dp, top = 24.dp)
                        .background(colorResource(id = R.color.Brown), CircleShape)
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add Quest",
                        tint = Color.White
                    )
                }
            }

            // Quests List
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(quests.filter { !it.isCompleted }, key = { it.id }) { quest ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(id = R.drawable.square),
                                contentDescription = "Quest background",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.FillBounds
                            )
                            Text(
                                text = quest.task,
                                fontFamily = jersey25,
                                color = colorResource(id = R.color.Brown),
                                fontSize = 28.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 40.dp, end = 40.dp, bottom = 32.dp)
                            )
                            // Buttons Row
                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 100.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Done Button
                                GameButton(backgroundRes = R.drawable.check, onClick = { questViewModel.toggleQuestCompleted(quest.id)
                                    timerViewModel.addExp(100)}, modifier = Modifier, enabled = true)
                                // Delete Button
                                GameButton(backgroundRes = R.drawable.delete, onClick = { questViewModel.deleteQuest(quest.id)
                                    timerViewModel.addExp(100)}, modifier = Modifier, enabled = true)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun QuestPreview() {
    val questViewModel = viewModel<QuestViewModel>()
    val timerViewModel = viewModel<TimerViewModel>()
    QuestView(questViewModel, timerViewModel)
}

package com.unitx.template.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.unitx.template.presentation.mvi.HomeEvent
import com.unitx.template.presentation.mvi.UserViewmodel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewmodel: UserViewmodel = koinViewModel(),
    onButtonClick: (String) -> Unit
){
    val test by viewmodel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.padding(18.dp)
    ) {
        Button(
            onClick = {
                viewmodel.onEvent(HomeEvent.ScreenChange("test"))
                onButtonClick("test")
            }
        ) {
            Text("Move from ${test.id}")
        }
    }
}
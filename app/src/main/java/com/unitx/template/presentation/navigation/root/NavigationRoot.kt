package com.unitx.template.presentation.navigation.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.unitx.template.presentation.navigation.route.TemplateRoute
import com.unitx.template.presentation.screen.HomeScreen
import com.unitx.template.presentation.screen.SecondScreen
import kotlin.collections.listOf

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(TemplateRoute.HomeScreenKey)

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<TemplateRoute.HomeScreenKey> {
                HomeScreen(
                    onButtonClick = {
                        backStack.add(TemplateRoute.SecondScreenKey(it))
                    }
                )
            }
            entry<TemplateRoute.SecondScreenKey> {
                SecondScreen(it.id)
            }
        }
    )
}
package ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.model.MasterUI

@Composable
internal fun <T> MasterView(
    modifier: Modifier = Modifier,
    state: MasterUI<T>,
    errorView: @Composable () -> Unit,
    loadingView: @Composable () -> Unit,
    successView: @Composable (data: T) -> Unit
) {
    Column(
        modifier = modifier
            .animateContentSize()
    ) {
        when (state) {
            is MasterUI.Loading -> {
                loadingView()
            }
            is MasterUI.Success -> {
                successView(state.data)
            }
            else -> {
                errorView()
            }
        }
    }
}
package com.example.app.feature.sheetdetail
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.R
import com.example.app.core.design.component.MyCenterTopAppBar
import com.example.app.core.design.component.MyErrorView
import com.example.app.core.design.component.MyLoading
import com.example.app.core.model.SHEET_EMPTY
import com.example.app.core.model.Sheet
import com.example.app.feature.song.component.ItemSongSheet


@Composable
fun SheetDetailRoute(
    finishPage: () -> Unit,
    toMusicPlayer: () -> Unit,
    viewModel: SheetDetailViewModel = hiltViewModel()
) {
    val data by viewModel.uiState.collectAsState()
    SheetDetailScreen(
        data = data,
        finishPage = finishPage,
        onRetryClick = viewModel::onRetryClick,
        onSongClick = viewModel::onSongClick,
        toMusicPlayer = toMusicPlayer,
    )

    LaunchedEffect(viewModel.toMusicPlayer.value) {
        if (viewModel.toMusicPlayer.value) {
            toMusicPlayer()
            viewModel.clearMusicPlayer()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetDetailScreen(
    data: SheetDetailUiState = SheetDetailUiState.Loading,
    finishPage: () -> Unit = {},
    onRetryClick:()-> Unit = {},
    onSongClick: (Int) -> Unit = {},
    toMusicPlayer: () -> Unit,

    ) {

    when(val data = data) {
        is SheetDetailUiState.Loading->{
            MyLoading()
        }

        is SheetDetailUiState.Error -> {
            MyErrorView(
                exception = data.exception,
                onRetryClick = onRetryClick,
            )
        }
        is SheetDetailUiState.Success -> {
            ContentView(
                finishPage = finishPage,
                data = data.data,
                onSongClick = onSongClick,
            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentView(
    finishPage:() -> Unit,
    data: Sheet,
    onSongClick: (Int) -> Unit,

) {
    Scaffold(
        topBar = {
            MyCenterTopAppBar(
                finishPage = finishPage,
                titleText = stringResource(id = R.string.sheet)
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {  paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            data.songs?.let { songs ->
                itemsIndexed(songs){ index,data ->
                    ItemSongSheet(data = data, index = index, modifier = Modifier.clickable{
                        onSongClick(index)
                    })
                }
            }
        }

    }
}





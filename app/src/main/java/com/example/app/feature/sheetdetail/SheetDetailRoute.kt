package com.example.app.feature.sheetdetail
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.app.R
import com.example.app.core.design.component.MyCenterTopAppBar
import com.example.app.core.model.SHEET_EMPTY
import com.example.app.core.model.Sheet
import com.example.app.feature.song.component.ItemSongSheet


@Composable
fun SheetDetailRoute(
    finishPage: () -> Unit,
    viewModel: SheetDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val data by viewModel.data.collectAsState()
    SheetDetailScreen(
        data = data,
        finishPage = finishPage
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetDetailScreen(
    data: Sheet = SHEET_EMPTY(),
    finishPage: () -> Unit,

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
                 modifier = Modifier.fillMaxSize()
                    .padding(paddingValues)
            ){
                data.songs?.let { songs ->
                    itemsIndexed(songs){ index,data ->
                        ItemSongSheet(data = data, index = index)
                    }
                }
            }

    }
}





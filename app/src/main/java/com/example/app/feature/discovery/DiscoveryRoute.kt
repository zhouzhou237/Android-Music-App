package com.example.app.feature.discovery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.R
import com.example.app.core.design.theme.LocalDividerColor
import com.example.app.core.design.theme.MyAppTheme
import com.example.app.core.design.theme.SpaceExtraMedium
import com.example.app.core.design.theme.SpaceOuter
import com.example.app.core.design.theme.SpaceSmall
import com.example.app.core.design.theme.SpaceSmallHeight
import com.example.app.core.model.Song
import com.example.app.core.ui.DiscoveryPreviewParameterData
import com.example.app.core.ui.DiscoveryPreviewParameterData.SONGS
import com.example.app.core.ui.DiscoveryPreviewParameterProvider
import androidx.compose.runtime.getValue
import coil.compose.AsyncImage
import com.example.app.core.model.ViewData
import com.example.app.feature.sheet.ItemSheet
import com.example.app.feature.song.component.ItemSong
import com.example.app.util.ResourceUtil


@Composable
fun DiscoveryRoute(
    toSheetDetail: (String) -> Unit = {},
    viewModel: DiscoveryViewModel = viewModel()
){
    val datum by viewModel.topDatum.collectAsState()

    DiscoveryScreen(
        topDatum = datum,
        toSheetDetail = toSheetDetail,
    )

}

@Composable
fun DiscoveryScreen(
    toggleDrawer: () -> Unit = {},
    toSearch: () -> Unit = {},
    topDatum:List<ViewData> = listOf(),
    toSheetDetail: (String) -> Unit = {},
) {
    Scaffold(
        topBar = {
            DiscoveryTopBar(
                toggleDrawer,toSearch
            )
        },
        //Exclude bottom navigation bar margins for content in box
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)



    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(paddingValues)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = SpaceOuter),
                verticalArrangement = Arrangement.spacedBy(SpaceExtraMedium),
                modifier = Modifier.fillMaxSize(),
            ) {
                topDatum.forEach { viewData ->
                    if(viewData.sheets != null){
                        items(viewData.sheets){ sheet ->
                            ItemSheet(data = sheet, modifier = Modifier.clickable {
                                toSheetDetail(sheet.id)
                            })
                        }
                    }else if(viewData.songs != null){
                        items(viewData.songs){ song ->
                            ItemSong(data = song)
                        }
                    }
                }
            }
        }
    }
}


// Discovery Interface Title Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoveryTopBar(toggleDrawer: () -> Unit, toSearch: () -> Unit) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = {toggleDrawer}) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.message),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(28.dp)
            )
        },
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable {
                        toSearch()
                    }
            ){
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(18.dp),
                )
                Text(
                    text = stringResource(id = R.string.search_tip) ,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                )

            }
        }
    )
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun DiscoverScreenPreview(
    @PreviewParameter(DiscoveryPreviewParameterProvider::class)
    song: List<Song>,
){
    MyAppTheme {
        DiscoveryScreen(
//            songs = song,
        )
    }
}
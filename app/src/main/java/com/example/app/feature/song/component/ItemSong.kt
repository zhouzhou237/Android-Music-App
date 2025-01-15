package com.example.app.feature.song.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.app.R
import com.example.app.core.design.theme.LocalDividerColor
import com.example.app.core.design.theme.MyAppTheme
import com.example.app.core.design.theme.SpaceMedium
import com.example.app.core.design.theme.SpaceSmallHeight
import com.example.app.core.model.Song
import com.example.app.core.ui.DiscoveryPreviewParameterData.SONG
import com.example.app.util.ResourceUtil

/**
 * Sing Song item
 */
@Composable
fun ItemSong(data: Song, modifier: Modifier = Modifier) {
    Log.d("Debug", "ItemSong is called with data: $data")
    Row(
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        if (data.icon != null)
            AsyncImage(
                model = ResourceUtil.r2(data.icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(LocalDividerColor.current)
            )

        else
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(LocalDividerColor.current)
            )


        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = SpaceMedium)
        ) {
            Text(
                text = data.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )

            SpaceSmallHeight()

            Text(
                text = "${data.artist} - ${data.album}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}


@Preview
@Composable
fun ItemSongPreview() {
    MyAppTheme {
        ItemSong(
            data = SONG
        )
    }
}
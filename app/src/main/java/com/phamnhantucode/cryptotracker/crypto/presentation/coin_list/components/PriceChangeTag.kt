package com.phamnhantucode.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.phamnhantucode.cryptotracker.crypto.presentation.models.DisplayableNumber
import com.phamnhantucode.cryptotracker.crypto.presentation.models.toDisplayableNumber
import com.phamnhantucode.cryptotracker.ui.theme.CryptoTrackerTheme
import com.phamnhantucode.cryptotracker.ui.theme.greenBackground

@Composable
fun PriceChangeTag(
    change: DisplayableNumber,
    modifier: Modifier = Modifier
) {
    val contentColor =
        if (change.value > 0) Color.Green else MaterialTheme.colorScheme.onErrorContainer
    val backgroundContainer =
        if (change.value > 0) greenBackground else MaterialTheme.colorScheme.errorContainer
    val icon = if (change.value > 0) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(backgroundContainer)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = change.format, tint = contentColor)
        Text(
            text = change.format,
            color = contentColor,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@PreviewLightDark
@Composable
private fun PriceChangePreview() {
    CryptoTrackerTheme {
        PriceChangeTag(
            change = (-0.05).toDisplayableNumber(),
        )
    }
}
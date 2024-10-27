package com.phamnhantucode.cryptotracker.crypto.presentation.coin_detail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phamnhantucode.cryptotracker.R
import com.phamnhantucode.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun InfoCard(
    icon: ImageVector,
    title: String,
    formattedText: String,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .shadow(
                elevation = 15.dp,
                shape = RectangleShape,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary,
            ),
        shape = RectangleShape,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        ),
    ) {
        val alignmentCenter = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(horizontal = 12.dp)
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedContent(
            targetState = icon,
            label = "AnimatedIcon",
            modifier = alignmentCenter
        ) { icon ->
            Icon(
                imageVector = icon, contentDescription = title,
                tint = contentColor,
                modifier = Modifier.size(75.dp)
            )
        }
        AnimatedContent(
            targetState = formattedText,
            label = "AnimatedText",
            modifier = alignmentCenter
        ) { formattedText ->
            Text(text = formattedText, color = contentColor, fontSize = 18.sp,)
        }
        Text(text = title, modifier = alignmentCenter, color = contentColor, fontSize = 16.sp)

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@PreviewLightDark
@Composable
private fun InfoCardPreview() {
    CryptoTrackerTheme {
        InfoCard(
            icon = ImageVector.vectorResource(R.drawable.dollar),
            "Bitcoin",
            formattedText = "$ 123123123"
        )
    }
}
package com.issoft.navigationsample.features.checkin

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import com.issoft.navigationsample.R
import com.issoft.navigationsample.ui.theme.AppThemeTokens
import qrcode.QRCode
import qrcode.color.Colors

@Composable
fun QrImage(qrCode: String, modifier: Modifier) {
    val context = LocalContext.current
    val resources = context.resources
    val extraColors = AppThemeTokens.extraColors

    val logoBytes by remember {
        mutableStateOf(
            resources.openRawResource(R.raw.default_avatar).use { it.readBytes() }
        )
    }

    val qr = remember(qrCode, logoBytes) {
        QRCode.ofSquares()
            .withColor(extraColors.success.toArgb())
            .withSize(25)
            .withLogo(logoBytes, 150, 150)
            .build(qrCode)
    }

    val pngBytes = remember(qr) { qr.render().getBytes() }
    val bitmap = remember(pngBytes) {
        BitmapFactory.decodeByteArray(pngBytes, 0, pngBytes.size)
    }
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "QR code for $qrCode",
        modifier = modifier
    )
}
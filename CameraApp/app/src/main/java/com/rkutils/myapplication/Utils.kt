package com.rkutils.myapplication

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.fonts.FontStyle
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

fun Context.isPermissionGranted(permission: String): Boolean {
    return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
//    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

@Composable
fun Context.CameraPermissionRequestDialog(positive: () -> Unit) {
    AlertDialog(
        onDismissRequest = {

        },
        confirmButton = {
            Text(
                text = "Allow",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth().clickable {
                    positive.invoke()
                }
            )
        },
        dismissButton = {
            Text(
                text = "Deny",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )
        },
        title = {
            Text(
                text = "Allow Camera Permission",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        text = {
            Text(
                text = "Camera Permission Required for accessing the camera",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}


fun Context.openPermissionSettings() {
    Intent(ACTION_APPLICATION_DETAILS_SETTINGS).also {
        val uri = Uri.fromParts("package", packageName, null)
        it.data = uri
        startActivity(it)
    }
}
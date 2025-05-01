package com.rkutils.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.MainThread
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import com.google.common.util.concurrent.ListenableFuture
import com.rkutils.myapplication.ui.theme.CameraAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun App() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val activity: ComponentActivity = context as ComponentActivity

    val previewView = remember { PreviewView(context) }
    var startCamera by remember { mutableStateOf(false) }
    val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> = remember {
        ProcessCameraProvider.getInstance(context)
    }

    var showPermissionDialog by remember { mutableStateOf(false) }


    DisposableEffect(startCamera) {
        if(startCamera) {
            kotlin.runCatching {
                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder()
                        .build()
                        .also {
                            it.surfaceProvider = previewView.surfaceProvider
                        }
                    val camera = cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_FRONT_CAMERA,
                        preview
                    )

                }, context.mainExecutor)
            }.onFailure {
                it.printStackTrace()
            }

        }
        onDispose {
            cameraProviderFuture.cancel(true)
        }
    }


    val requestPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if(isGranted) {
            // start camera
            startCamera = true
        }
    }

    val permission = android.Manifest.permission.CAMERA

    LaunchedEffect(Unit) {
        if(!context.isPermissionGranted(permission)) {
            requestPermissionLauncher.launch(permission)
            if(!shouldShowRequestPermissionRationale(activity , permission)) {
                showPermissionDialog = true
            }
        }
        else {
            startCamera = true
            showPermissionDialog = false
        }


    }


    if(showPermissionDialog) {
        context.CameraPermissionRequestDialog {
            context.openPermissionSettings()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
           AndroidView(
               modifier = Modifier.fillMaxSize(),
               factory = {
                  previewView
               }
           )
        }
    }

}


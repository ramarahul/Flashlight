package rr_apps_and_games.apps.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleFlashLightOnOff;
    private CameraManager cameraManager;
    private String getCameraID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        // Register the ToggleButton with specific ID
        toggleFlashLightOnOff = findViewById(R.id.toggle_flashlight);

        // cameraManager to interact with camera devices
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        // Exception is handled, because to check whether
        // the camera resource is being used by another
        // service or not.
        try {
            // O means back camera unit,
            // 1 means front camera unit
            getCameraID = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void toggleFlashLight(View view) {
        if (toggleFlashLightOnOff.isChecked()) {
            // Exception is handled, because to check
            // whether the camera resource is being used by
            // another service or not.
            try {
                // true sets the torch in ON mode
                cameraManager.setTorchMode(getCameraID, true);

                // Inform the user about the flashlight
                // status using Toast message
                Toast.makeText(MainActivity.this, "Flashlight is turned ON", Toast.LENGTH_SHORT).show();
            } catch (CameraAccessException e) {
                // prints stack trace on standard error
                // output error stream
                e.printStackTrace();
            }
        } else {
            // Exception is handled, because to check
            // whether the camera resource is being used by
            // another service or not.
            try {
                // true sets the torch in OFF mode
                cameraManager.setTorchMode(getCameraID, false);

                // Inform the user about the flashlight
                // status using Toast message
                Toast.makeText(MainActivity.this, "Flashlight is turned OFF", Toast.LENGTH_SHORT).show();
            } catch (CameraAccessException e) {
                // prints stack trace on standard error
                // output error stream
                e.printStackTrace();
            }
        }

    }
}
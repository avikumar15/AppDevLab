package com.example.menuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import static com.example.menuapp.Utils.createToast;

public class ControlActivity extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch bluetoothSwitch;
    Button volMinus, volPlus, brightMinus, brightPlus, orientLand, orientPort;

    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    AudioManager audioManager;
    int brightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        bind();
        manageBluetooth();
        brightness =
                Settings.System.getInt(this.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS, 0);

    }

    private void manageBluetooth() {
        if (mBluetoothAdapter != null) {
            if(mBluetoothAdapter.isEnabled())
                bluetoothSwitch.setChecked(true);
        }

        bluetoothSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBluetoothAdapter.enable();
                } else {
                    mBluetoothAdapter.disable();
                }
            }
        });
    }

    private void bind() {
        bluetoothSwitch = findViewById(R.id.switchBluetooth);
        volMinus = findViewById(R.id.btnVolMinus);
        volPlus = findViewById(R.id.btnVolPlus);
        brightMinus = findViewById(R.id.btnBrightMinus);
        brightPlus = findViewById(R.id.btnBrightPlus);
        orientLand = findViewById(R.id.btnOrientLand);
        orientPort = findViewById(R.id.btnOrientPort);

        volMinus.setOnClickListener(this);
        volPlus.setOnClickListener(this);
        brightPlus.setOnClickListener(this);
        brightMinus.setOnClickListener(this);
        orientPort.setOnClickListener(this);
        orientLand.setOnClickListener(this);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int currentVolume = audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);

        audioManager
                .setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnVolMinus:
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                createToast(this, "Current Volume: "+audioManager
                        .getStreamVolume(AudioManager.STREAM_MUSIC));
                break;
            case R.id.btnVolPlus:
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                createToast(this, "Current Volume: "+audioManager
                        .getStreamVolume(AudioManager.STREAM_MUSIC));
                break;

            case R.id.btnOrientLand:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;

            case R.id.btnOrientPort:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;

            case R.id.btnBrightPlus:
                if(brightness<95)
                    brightness+=5;
                changeBrightness();
                break;

            case R.id.btnBrightMinus:
                if(brightness>5)
                    brightness-=5;
                changeBrightness();
                break;
        }
    }

    private void changeBrightness() {
        Settings.System.putInt(this.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, brightness);
    }
}

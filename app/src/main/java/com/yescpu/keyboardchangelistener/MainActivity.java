package com.yescpu.keyboardchangelistener;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.yescpu.keyboardchangelib.KeyboardChangeListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements KeyboardChangeListener.KeyboardListener {
    private static final String TAG = "MainActivity";
    private KeyboardChangeListener mKeyboardChangeListener;
    private EditText mEdContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdContent = findViewById(R.id.edContent);
        mKeyboardChangeListener = KeyboardChangeListener.create(MainActivity.this);
        mKeyboardChangeListener.setKeyboardListener(MainActivity.this);
    }


    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        String text = String.format(Locale.getDefault(), "isShow %s, height=%d", String.valueOf(isShow), keyboardHeight);
        mEdContent.setText(text);
        mEdContent.setSelection(text.length());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mKeyboardChangeListener.destroy();
    }
}

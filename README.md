# KeyboardChangeListener
simple and powerful Keyboard show/hidden change listener,without having to add a layout and can run in every Activity;  

# useage  
make activity:
``` xml
android:windowSoftInputMode="adjustResize"
```
java:
``` java
 new KeyboardChangeListener(this).setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                Log.d(TAG, "isShow = [" + isShow + "], keyboardHeight = [" + keyboardHeight + "]");
            }
        });
```
#enjoy！

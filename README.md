
[中文](https://github.com/yescpu/KeyboardChangeListener/blob/yescpu-patch-1/README_ZH_HANS.MD)

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

License
=======

    Copyright (C) 2016 yescpu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

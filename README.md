# Android Pinpad
[![](https://jitpack.io/v/aliudurim/library-android-pinpad.svg)](https://jitpack.io/#aliudurim/library-android-pinpad)

This is an Android library for a pin pad view.

<img src="/images/demo_image.png" alt="Demo Screen Capture" width="300px" />

## Installation

Step 1. Add the JitPack repository to your build file

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Step 2. Add the dependency

```groovy
dependencies {
  implementation 'com.github.aliudurim:library-android-pinpad:${version}'
}
```

## How to use PinPadView?
#### XML layout usage:

```xml
<com.durimaliu.pinpad.PinPadView
    android:id="@+id/pinPadView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:size_grid_spacing="@dimen/space_10"
    app:item_size="@dimen/size_80"
    app:item_background="@color/colorPrimary"
    app:item_background_type="SQUARE"
    app:item_delete_background="@drawable/ic_backspace"
    app:item_enter_background="@drawable/ic_done"
    app:item_text_size="@dimen/text_size_14"
    app:item_text_color="@android:color/white"
    app:item_text_style="normal"/>
```

#### Kotlin usage:

```kotlin
class MainActivity : AppCompatActivity(), (View, PinPad) -> Unit {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pinPadView.onItemClickListener = this
    }

    override fun invoke(view: View, pinpad: PinPad) {
        textView.text = String.format("%s%s", view, pinpad.toString())
    }
}
```

#### Java usage:

```java
public class MainActivityAndroid extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PinPadView pinPadView = findViewById(R.id.pinPadView);
        final TextView textView = findViewById(R.id.textView);

        pinPadView.setOnItemClickListener(new Function2<View, PinPad, Unit>() {
            @Override
            public Unit invoke(View view, PinPad pinPad) {
                textView.setText(String.format("%s%s", view, pinPad.toString()));
                return null;
            }
        });
    }
}
```

## Contributing

Would you like to contribute? Fork us and send a pull request! Be sure to checkout our issues first.

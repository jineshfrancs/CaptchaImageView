# CaptchaImageView
Custom ImageView to generate captcha image.


![Output sample](https://github.com/jineshfrancs/CaptchaImageView/blob/master/screens/captcha_screen.gif) ![Output sample](https://github.com/jineshfrancs/CaptchaImageView/blob/master/screens/captcha_screen_2.gif)

Add CaptchaImageView to your layout
```
 <test.jinesh.captcha.CaptchaImageView
            android:layout_width="wrap_content"
            android:id="@+id/image"
            android:layout_weight="6"
            android:layout_margin="5dp"
            android:layout_height="50dp"
            />
```
Call regenerate() method on CaptchaImageView to regenerate your captcha

```
 captchaImageView= (CaptchaImageView) findViewById(R.id.image);
 refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captchaImageView.regenerate();
            }
 });
```

Call getCaptchaCode() method on CaptchaImageView to read last generated captcha code.

```
 captchaImageView.getCaptchaCode()

```

Use in your project
------

1.Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2.Add the dependency in your app build.gradle file:
```
dependencies {
	        compile 'com.github.jineshfrancs:CaptchaImageView:1.0'
}
```

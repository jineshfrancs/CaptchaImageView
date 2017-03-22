# CaptchaImageView

[![Github All Releases](https://img.shields.io/github/downloads/CaptchaImageView/CaptchaImageView/total.svg)](https://github.com/jineshfrancs/CaptchaImageView/archive/1.0.zip) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CaptchaImageView-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/4719)

Custom ImageView to generate captcha image.


![Output sample](https://github.com/jineshfrancs/CaptchaImageView/blob/master/screens/captcha_screen.gif) ![Output sample](https://github.com/jineshfrancs/CaptchaImageView/blob/master/screens/captcha_screen_2.gif)

Add CaptchaImageView to your layout
```xml
 <test.jinesh.captcha.CaptchaImageView
            android:layout_width="wrap_content"
            android:id="@+id/image"
            android:layout_weight="6"
            android:layout_margin="5dp"
            android:layout_height="50dp"
            />
```
Call regenerate() method on CaptchaImageView to regenerate your captcha

```java
 captchaImageView= (CaptchaImageView) findViewById(R.id.image);
 refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captchaImageView.regenerate();
            }
 });
```

Call getCaptchaCode() method on CaptchaImageView to read last generated captcha code.

```java
 captchaImageView.getCaptchaCode()

```

Use in your project
------

1.Add it in your root build.gradle at the end of repositories:
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2.Add the dependency in your app build.gradle file:
```gradle
dependencies {
	        compile 'com.github.jineshfrancs:CaptchaImageView:1.0'
}
```

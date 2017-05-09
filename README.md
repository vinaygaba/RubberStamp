RubberStamp :mailbox:
================

![Feature Image](images/FeatureImage.png)

RubberStamp is an Android library that makes it easy for you to watermark your images.

Screenshots
------------
![Screenshots](images/screenshots.png)


Features
---------

* Add a watermark to your images.
* It can be text or another image.
* Multiple ways and features to customize how the watermark looks including attributes like font, color, opacity, size, shadow properties, etc.
* Flexible API that has multiple pre-defined options to get started quickly.
* Ability to tile your watermark across an image.


Setup
------
The library is pushed to Maven Central as an AAR, so you just need to add the following to your build.gradle file:
```java
dependencies {
    compile ‘com.vinaygaba:rubberstamp:1.0.0’
}
```

Usage
------
Using RubberStamp is extremely easy.

First, define the characteristics of your watermark using RubberStampConfig

```java
RubberStampConfig config = new RubberStampConfigBuilder()
              .base(R.drawable.lenna)
              .rubberStamp("Watermark")
              .alpha(100)
              .textColor(Color.BLACK)
              .textBackgroundColor(Color.WHITE)
              .textShadow(0.1f,  5, 5, Color.BLUE)
              .textSize(90)
              .rotation(-45)
              .rubberStampPosition(RubberStamp.CENTER)
              .build();
```

That's all that is needed really. All you need to do next is just pass this config to the addStamp method of the RubberStamp class and voila!
```java
RubberStamp rubberStamp = new RubberStamp(this);
rubberStamp.addStamp(config);
```

Attribute Usage & Documentation
--------------------------------
All the attributes listed below are part of RubberStampConfig.

##### I. `base`
Use this to set the base image on top of which the watermark will be drawn. This can be a bitmap or a drawable.

```java
config.base(bitmap);

config.base(R.id.image);
```

##### II. `rubberStamp`
The rubberstamp is the actual watermark that will be drawn on top of the base image. This can either be a bitmap or a string.

```java
// String watermark
config.rubberStamp("Watermark" );

// Bitmap watermarkBitmap
config.rubberStamp(bitmap);
```

If you want to use a drawable, convert it to a bitmap and use that. You would do that using:

```java
Bitmap bitmap = BitmapFactory.decodeResources(getResources(), R.drawable.logo);

```

##### III. `rubberStampPosition`
The library provides 9 pre defined positions to display the location of the rubberstamp. They are as follows:

TOP_LEFT<br/>
TOP_CENTER<br/>
TOP_RIGHT<br/>
BOTTOM_LEFT<br/>
BOTTOM_CENTER<br/>
BOTTOM_RIGHT<br/>
CENTER_LEFT<br/>
CENTER<br/>
CENTER_RIGHT<br/>

```java
// RubberStampPosition position
config.rubberStampPosition(RubberStampPosition.TOP_LEFT);

```

In additon, if you would like to specify the exact position of the rubberstamp, you can pass the the RubberStampPosition to be `CUSTOM` and use the following constructor to specify the position.

```java
// RubberStampPosition position, int xCoordinate, int yCoordinate
config.rubberStampPosition(RubberStampPosition.CUSTOM, 100, 100);
```

There is another special position, `TILE` that you can use in order to tile the rubberstamp across the base image.
This is a fairly common use case for watermarking software so it made sense for the library
to support it. You can use it in the following way:

```java
// RubberStampPosition position
config.rubberStampPosition(RubberStampPosition.TILE);
```

##### IV. `alpha`

Use alpha to change the opacity of your rubberstamp. It accepts an int value between 0 and 255.

```java
//int alpha
config.alpha(255);
```

##### V. `rotation`

Rotation does exactly what you'd imagine it to: it rotates your rubberstamp. It expects a float value
to be passed to it.

```java
config.rotation(45);
config.rotation(-45);
```

### Text RubberStamp specific attributes

There are some additional attributes that this library provides when you pass a String as the RubberStamp.
These attributes won't have any side effects when you pass a bitmap as a rubberstamp and will be disregarded.
Most of them are pretty self explanatory.

##### VI. `textColor`

Sets the text color of the rubberstamp.
```java
config.textColor(Color.Red);
```

##### VII. `textBackgroundColor`

This lets you specify a background color for your text rubberstamp.

<b>Note</b> This attribute does not work when the position is set to RubberStampPosition.TILE

```java
//int color
config.textBackgroundColor(Color.WHITE);
```

##### VIII. `textSize`
Sets the size of the text rubberstamp.

```java
//int size
config.textSize(40);
```

##### VIII. `textShader`

This lets you specify a custom shader that you can use to customize the watermark.
Honestly, the sky is the limit when it comes to Shaders. Here is an example shader that
paints my watermark in rainbow colors.

```java
RubberStamp rubberStamp = new RubberStamp(this);
int[] rainbow = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
Shader shader = new LinearGradient(0, 0, 0, logo.getWidth(), rainbow,
        null, Shader.TileMode.MIRROR);
Matrix matrix = new Matrix();
matrix.setRotate(90);
shader.setLocalMatrix(matrix);

//set the shader
config.textShader(shader);
```

##### IX. `textShadow`
This lets you specify a shadow for your text rubberstamp.
<b>Note:</b> No shadow will be displayed if the blur radius is set to 0. This is how the paint
API in Android behaves underneath as well.
```java
//float blurRadius, float xOffset, float yOffset, int color
config.textShadow(1.0f, 5, 5, Color.BLUE);
```

Credits
-----------------
Author: Vinay Gaba (vinaygaba@gmail.com)

<a href="https://plus.google.com/+Vinaygaba">
  <img alt="Follow me on Google+"
       src="https://github.com/gabrielemariotti/cardslib/raw/master/demo/images/g+64.png" />
</a>
<a href="https://twitter.com/vinaygaba">
  <img alt="Follow me on Twitter"
       src="https://github.com/gabrielemariotti/cardslib/raw/master/demo/images/twitter64.png" />
</a>
<a href="https://www.linkedin.com/in/vinaygaba">
  <img alt="Follow me on LinkedIn"
       src="https://github.com/gabrielemariotti/cardslib/raw/master/demo/images/linkedin.png" />
</a>


License
-------

    Copyright 2017 Vinay Gaba

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

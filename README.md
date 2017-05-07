RubberStamp:mailbox:
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
The rubberstamp is the watermark that will be drawn on top of the base image. This can either be a bitmap or a string.

```java
config.rubberStamp("Watermark");

config.rubberStamp(bitmap);
```

Due to limitations of the Android API, supporting a drawable was not possible. However, it was really easy to convert a drawable to a bit. You would do that using:

```java
Bitmap bitmap = BitmapFactory.decodeResources(getResources(), R.drawable.logo);

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

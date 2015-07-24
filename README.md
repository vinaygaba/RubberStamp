RubberStamp:mailbox:
================

![Feature Image](images/FeatureImage.png)

RubberStamp is an Android library that makes it easy for you to add a stamp, logo or watermark to your images.

Screenshots
------------
![Screenshots](images/screenshots.png)


Features
---------

* Add text or logos (drawables) on top of your images.
* Customize the font, color, size, position, etc.
* Choose from 8 different pre defined positions for your logo/stamp.


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
Using RubberStamp is extremely easy. You can use it in the following manner:

```java
RubberStamp rubberStamp = new RubberStamp(this);

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

    Copyright 2015 Vinay Gaba

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

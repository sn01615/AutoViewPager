# AutoViewPager
重写ViewPager , 简单到不行的实现轮播 | by 刘某人

**beta1版本**

---
这是一个非常简单的ViewPager，我只是简单了实现了轮播，通过Handler实现的，比较小low，但是很实用

使用的方法很简单，把里面的AutoViewPager拿过去就好了

## 一.最简单的使用

### xml

```xml
	 <!--注意不要直接复制-->
    <com.liuguilin.autoviewpager.view.AutoViewPager
        android:id="@+id/mAutoViewPager"
        android:layout_width="match_parent"
        android:layout_height="200dp"/>
```

### java

```java
		mAutoViewPager = (AutoViewPager) findViewById(R.id.mAutoViewPager);
        //设置轮播开关
        mAutoViewPager.setStartAuto(true);
        //设置轮播间隔时长 默认3s
        mAutoViewPager.setAutoTime(2000);
        for (int i = 0; i < 3; i++) {
            View view = new View(this);
            mAutoViewPager.setView(view);
        }
```

这样就完成了，这只是初步的beta测试版，还不是很完整，慢慢的加入更多的style，可能会更加的优秀的

**如果不懂的话，可以参考文章中的例子哦，不过这个太简单了，你应该懂的**

## 二.预览

![]()

## 三.关于

* Blog:[http://blog.csdn.net/qq_26787115](http://blog.csdn.net/qq_26787115)
* Github:[https://github.com/LiuGuiLinAndroid](https://github.com/LiuGuiLinAndroid)
* Wechat:Android_LiuGuiLin

> 扫描二维码可以每天收到很多有深度的文章哦！

![weixin](http://img.blog.csdn.net/20160108203741937)

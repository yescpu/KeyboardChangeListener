监听软键盘高度变化涉及到两个较重要的知识点
----
## windowSoftInputMode
windowSoftInputMode总共分两类：
第一类以adjust开头，决定了键盘弹出之后以何种方式去挤压activity中的元素：
- adjustResize-**挤压**
- adjustPan-把整个Activity往上推
- adjustNothing-浮在Activity之上  

第二类以state开头，这个要关联两个Activity去理解，比如ActivityA正在弹出键盘输入用户名，然后ActivityB里面需要填银行卡，那跳转ActivityB时键盘是隐藏呢还是
继续显示？针对ActivityB而言需要可能有如下几种选择：
- 不管前面的Activity是否显示键盘，我多需要正常弹出键盘-stateAlwaysVisible
- 不管前面的Activity是否显示键盘，我多需要隐藏键盘-stateAlwaysHidden  
- ...  

另stateAlways和state区别：前者Activity获取焦点就会起作用，后者只有进入才起作用（返回到当前Activity不会起作用）  

## 怎么监听键盘键盘弹出？  
很不理解Android系统为什么不提供一种简洁有效的键盘监听api，So市面上所有关于键盘监听的方案几乎多是基于View的变化来做的，
再分解下这个问题：  
- 没有触发onLayoutChange是不能监听的键盘是否的弹出的
- 监听哪个View
- 怎么监听View

### 没有触发onLayout是不能监听的键盘是否的弹出
这句话有两层意思：  
一. 监听键盘只能使用onLayoutChange
二. 基本要结合adjustResize   

如果非得用`adjustNothing`但是还想监听键盘也不是不可能，可以使用PopUpWindow或者直接自己用WindowManager添加一个**傀儡**去做这个事，
因为单独的Window是可以拥有独立的windowSoftInputMode属性的，只是view设置成透明即可。
> 这点可以解决Android全屏时无法使用adjustSize的bug

### 监听哪个View
从方便程度和效率上看使用Activity的content的id最适合不过了，但是有的Activity没有这个id的，这个要注意下

### 怎么监听
这个就没啥好讲的了，可以参考代码






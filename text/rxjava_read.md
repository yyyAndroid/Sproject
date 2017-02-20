##Rxjava到底是什么？
Rxjava在Github主页上的自我介绍是：一个在Java VM上使用__可观测的序列__  来组成异步的，基于事件的程序库
然而，对于初学者来说，这太难看懂了，因为它是一个 总结，而初学者需要一个引言。

###RxJva好在哪里
换句话说，【同样是异步，为什么人们用它，而不用现成的  AsyncTask、Handler//xxx】
一个词：简洁


RxJava的观察者模式
RxJava有四个基本概念：Observable（可观察者，既被观察者）、Observer（观察者）、subscribe(订阅）、事件。Observable和Observer通过subscribe（）
方法实现订阅关系，从而Observable可以在需要的时候发出事件来通知Observer


与传统观察者模式不同RxJava的事件毁掉方法除了普通事件onNext（） （相当于onClick（）/onEvent()）之外，还定义了两个特殊的事件：
onCompleted()和onError();

onComplete():事件队列完结。RxJava不仅把每个事件丹徒处理，还会把它看做一个队列。RxJava规定，当不在有新的onNext()发出时，需要出发onCompleted()
方法作为标志

onError():事件队列异常。事件处理过程中出现异常时，onError()会被出发，同时队列会自动终止，不允许再有事件发出

在一个正确运行的事件序列中，onComlplete()和onError()有且只有一个，并且是事件序列中的最后一个。需要注意的是，onCompleted()和onError()二者也是互相
排斥的，既在队列中调用了其中一个，就不应该在调用另一个。


2.基本实现
    RxJava的基本实现由3点：
    1).创建Observer
    Observer既观察者，它决定事件触发的时候将有怎样的操作的行为。RxJava中的Observer接口的实现方式：

    除了Observer接口之外，RxJava还内置了一个实现Observer的抽象类：Subscriber.Subscriber对Observer接口进行了一些扩展
    ,但他们的基本使用方式是完全一样的


    不仅基本使用方式一样，实质上，在RxJava的subscriber过程中，Observer也是会先被转换成一个Subscriber再使用。所以如果你
    只想使用基本功能，选择Observer和Subscriber是完全一样的。它们的区别对于使用者来说主要有两点：
    1). onStart()：这是Subscriber增加的方法。它会在subscribe刚开始，而时间还未发送之前被调用，可以用于做一些准备工作，例如数据的清零和重置。这是一个
    可选择的方法，默认情况下它的实现为空。需要注意的是，如果对准备工作的线程有要求（例如弹出一个显示进度的对话框，这必须在主线程），onStart()就不适用了，
    因为它总是在subscribe所发生的线程被调用，而不能指定线程。要在指定的线程来做准备工作，可以使用doOnSubscribe()方法，具体可以在后面的稳重看到。

    2.）unSubscribe():这是subscriber所实现的另一个接口Subscription的方法，用于取消订阅。
    在这个方法调用后，Subscriber将不在接受时间。一般在这个方法钱，可以使用isUnsubscribe()先判断一下状态、unsubscibe()这个方法很重要，
    因为在subscribe()之后，Observable会持有Subscriber的引用，这个引用如果不能及时被释放，将有内存泄漏的的风险。所以最好保持一个原则：
    要在不在使用的时候尽快的合适的地方（例如onPauser() onStop()等方法中）电泳unSubscribe()来解除引用关系，以避免内存泄漏的发生。

2.创建Observable
    Observable既被观察者，它决定什么时候触发事件以及触发怎样的事件。RxJava使用create()方法来创建一个Observable，
    并为它定义事件触发规则：

3.Subscribe(订阅)
    1).创建了Observable()和Observer()之后，再用subscriber()方法将他们链接起来，整条链子就可以工作了。代码形式很简单：


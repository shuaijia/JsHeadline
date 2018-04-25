# Headline
仿头条app，MVP模式，Retrofit+RxJava+OkHttp+Glide框架等，持续开发中。。。

其中数据来自[头条Api](https://github.com/iMeiji/Toutiao/wiki/%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1Api%E5%88%86%E6%9E%90)

### 截图

<img width="240" height="466" src="https://raw.githubusercontent.com/kb18519142009/Headline/master/img/a.png"/> <img width="240" height="466" src="https://raw.githubusercontent.com/kb18519142009/Headline/master/img/b.png"/><img width="240" height="466" src="https://raw.githubusercontent.com/kb18519142009/Headline/master/img/c.png"/> <img width="240" height="466" src="https://raw.githubusercontent.com/kb18519142009/Headline/master/img/d.png"/><img width="240" height="466" src="https://raw.githubusercontent.com/kb18519142009/Headline/master/img/e.png"/> <img width="240" height="466" src="https://raw.githubusercontent.com/kb18519142009/Headline/master/img/f.png"/><img width="240" height="466" src="https://raw.githubusercontent.com/kb18519142009/Headline/master/img/g.png"/>

### point

- 基本遵循Material Design设计风格；
- 使用经典MVP模式进行封装；
- 模块化开发，将整体项目分为底层net库、ui库和utils库，base库、功能模块module，主app；
- 网络框架使用Retrofit+RxJava+OkHttp+Glide进行封装；
- 对OkHttp请求头进行处理，添加cookie和保存cookie；
- 对ExoPlayer进行封装，做视频播放器；
- 对EventBus进行封装，使用注解来注册，进行消息发送和处理；
- BaseActivity与BaseFragment封装，配合MVP模式框架；
- 使用自定义注解实现Router方案；
- 使用 7.0 新工具 DiffUtil , 不再无脑 notifyDataSetChanged
- 使用 ItemTouchHelper 实现今日头条的频道排序、频道移动
- 使用AndroidChangeSkin实现应用内换肤



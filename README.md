# Util
收集各种android工具类
目前选自
1.https://www.jianshu.com/p/6784800b5cc9
2.http://blog.csdn.net/chenrushui/article/details/52220074

工具类之加密 :
1.MD5Util  加密
2.AES RSA  加密，包含客户端，服务器端
android端需要导入sun.misc.BASE64Decder.jar

关于计算：
1.ArithUtil  浮点型进行加减乘除运算

关于时间戳：
1.DateUtils 字符串，时间戳之间的转换

常用的正则表达式验证：
1.RegularUtils 验证手机号，邮箱,身份证号等等

关于当前网络状况的判断
1.NetUtils 是否有网，是否链接wifi
2.NetWorkUtil 获取手机网络状态，详细到移动网络类型

软键盘显示，隐藏
1.KeyBoardUtils  需要有Editext的界面

屏幕工具类
1.ScreenUtils 获取屏幕宽高，截图
2.PixelUtil  像素相关，获取Android屏幕宽度，控件宽度，dp跟px互转

SD卡工具类
1.SDCardUtils 关于sd卡的工具类

首选项工具类
1.SPUtils  SharedPreferences存储

AppVersion
1.AppVersionUtil 版本号等

从Assets文件夹中读取.json文件
1.JsonAssetsReaderUtil 从Assets文件夹中读取.json文件 输出String

文件相关
1.FileUtils 文件处理

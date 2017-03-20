一，介绍
阿里百川HotFix是阿里百川旗下全平台App热修复服务方案。产品基于阿里巴巴首创hotpatch技术,提供最细粒度热修复能力。让您无需等待实时修复应用线上问题。

二， 应用场景
阿里百川HotFix提供的热修复能力，让您对应用程序的控制更加自由。
您可以使用HotFix：

紧急修复线上问题。
快速发布新鲜功能

三，方案比较
当前市面的热修复方案有很多，而阿里百川HotFix是其中唯一同时支持Android & iOS端热修复的产品。与众多的开源项目或者半开源项目相比，
我们不仅有AndFix、Wax开源项目团队成员的支持；同时还在其原有基础上对打包、调试工具、补丁版本管理等、安全性上做了升级；
更重要的是，结合最新的研究成果，突破了原有开源项目的诸多限制，我们能做到类修复、资源修复、so文件修复的实时生效。

HotFix总体来说最大的优势在于：
    补丁即时生效，不需要应用重启；
    补丁包同样采用差量技术，生成的PATCH体积小；
    对应用无侵入，几乎无性能损耗；
    傻瓜式接入。

但是HotFix1.0版本也有美中不足， 比如暂时不支持新增类和资源，so文件的修复等（这些限制均会在一月中的2.0版本全部去掉），
但是作为一项定位为线上紧急BUG的热修复的服务来说，能够真正做到BUG即时修复用户无感知，同时保证对应用性能不产生不必要的损耗，在热修复方面不失为一个好的选择。

四， //=== hotfix命令

    参数说明
    -c, -cmd: 值为patch: 打补丁命令 值为help: 查看使用说明
    -s, -src_apk：填写本地的原始APK（有问题的APK）. 必选
    -f, -fixed_apk：已经修复过该问题APK. 必选
    -w, -wp：输出patch的路径, 最后如果打补丁成功会在wp目录下自动创建的hotfix-working目录生成baichuan-hotfix-patch.jar补丁文件. 必选
    -k, -sign_file_url：本地的签名文件的路径，不输入则不做签名. 可选
    -p, -sign_file_pass: 证书文件的密码, 可选
    -a, -sign_alias: 证书的别名. 可选
    -e, -sign_alias_pass: 证书别名的密码. 可选
    -l, -filterClassFilePath：本地的白名单类列表文件的路径，放进去的类不会再计算patch，文件格式: 一行一个类名. 可选

// 使用cmd命令查看帮助(进入到BCFixPatchTools-1.3.0.jar所在的文件夹,测试一下，看是否正确)
java -jar BCFixPatchTools-1.3.0.jar -cmd help

// 生成补丁包(进入到BCFixPatchTools-1.3.0.jar所在的文件夹，把app-shengji-release-old.apk和app-shengji-release-new.apk两个安装包放进去，开始运行命令)
java -jar F:/xiangfa4.1.2/patchtool_demo/BCFixPatchTools-1.3.0.jar -cmd patch -src_apk F:/xiangfa4.1.2/patchtool_demo/app-shengji-release-old.apk -fixed_apk F:/xiangfa4.1.2/patchtool_demo/app-shengji-release-new.apk -wp F:/xiangfa4.1.2/patchtool_demo/patch_out -sign_file_url C:/Users/Administrator/.android/rjssigned.keystore -sign_file_pass c5pi1q4hcy7yt -sign_alias rjssign -sign_alias_pass c5pi1q4hcy7yt -filter_class_file F:/xiangfa4.1.2/patchtool_demo/classFilter.txt



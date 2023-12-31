# qrcode-file-transfer-j

A simple implementation of QRCode file transfer

一种超简易基于二维码的无网络文件传输实现，灵感来源于文献。

## 场景

在客户驻场开发中，一些严格的客户会使用远程桌面虚拟机进行开发，为了保密而进行内外网隔离，没法使用远程桌面拷贝数据到本地。

但是有些情形下，需要从内网的获取一些简易数据文件，如异常的日志，错误数据样本等等。

此时可以选择在远程桌面环境，手敲一份Encode代码，即可将所需传输的文件分割为二维码，并通过截图方式保存这些文件，

再通过Decode代码还原回文件。

## 使用

前提：

- 远程桌面环境
- 其内部有完整的maven私服仓库
    - 所以可用范围仅限制在Java开发的客户

远程桌面环境：

- 创建Maven项目，手敲Encode下代码

- 使用IDE构建工件（即可运行的jar包）
- 将jar和需传输的文件按如下放置：

```
- 目录A
	- source
		- 放需要传输的文件
	- out
	- jar
```

- 在目录A下使用java命令执行jar
- 在out目录下生成已分割好的二维码PNG集合

本地环境：

- 创建Maven项目，拷贝或者直接使用Decode代码
- 使用IDE构建工件（即可运行的jar包）
- 使用工具(如微信)，将二维码按顺序一张张截图保存
- 将jar和需解密的图片按如下放置：

```
- 目录B
	- source
		- 放二维码PNG图片
	- out
	- jar
```

- 在目录B下使用java命令执行jar
- 还原回的内容会通过压缩包的形式放在out目录下

其他：

- 源码可随意按喜好更改，但是二维码参数太大的话，容易识别失败。
- 该实现非常简陋，可考虑写一个自动化Decode的项目（有时间和后面有需求的话）
- 该项目的优点只有一个，就是内网的编码器相较于文献的十分容易手敲

## 文献

[基于二维码的无网络文件传输，通过屏幕和摄像头传输数据-哔哩哔哩](https://b23.tv/ZDsmYqT)

[文献源码-github.com/ganlvtech/qrcode-file-transfer](https://github.com/ganlvtech/qrcode-file-transfer)


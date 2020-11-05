[![Codacy Badge](https://app.codacy.com/project/badge/Grade/e96301a18c0a4e0d934ece462c67e81c)](https://www.codacy.com/gh/power4j/esc-pos-printing/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=power4j/esc-pos-printing&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/power4j/esc-pos-printing.svg?branch=master)](https://travis-ci.org/power4j/esc-pos-printing)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.power4j.kit/esc-pos-printing/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.power4j.kit/esc-pos-printing)
# ESC/POS 凭条打印

> github : https://github.com/power4j/esc-pos-printing
> gitee:  https://gitee.com/power4j/esc-pos-printing



已经实现的功能

- `ESC/POS` 指令编码

## 使用方法

maven 坐标

```xml
<dependency>
  <groupId>com.power4j.kit</groupId>
  <artifactId>esc-pos</artifactId>
  <version>最新版本</version>
</dependency>
```

```java
// json是事先定义好的打印模版
Doc doc = DocParser.parseFromJson(jsonStr);
// hexCmd 为打印指令，16进制格式，一般厂商会提供测试工具，粘贴进去就能打印，也可以通过网络或者蓝牙传输打印指令
String hexCmd = DocProcessor.getCmdEncoder().encodeHex(doc);
```



## 模版文档结构



### 文档

一个模版文档`Doc`由若干行组成,格式如下

```json
{
  "charsetName": "GB2312",
  "opt": {},
  "lines": []
}
```

`charsetName`  字符集

`opt`   默认文本格式

`lines` 数组类型，需要打印的行



### 行

文档以行的方式定义其打印内容,格式如下

```json
{
  "type": "text",
  "data": "hello",
  "opt": {}
}
```



`type` 表示类型，有三种

- `text`    文本
- `bmp`   位图
- `qrc`   二维码
- `feed`   控制指令，走纸
- `cut`   控制指令，切纸

`data`   需要打印的数据，不同的类型有不同的要求

- 文本  、 二维码：传入需要打印的文本
- 位图：图片数据(base64)
- 走纸:  数字，表示走纸的行数
- 切纸: 0 表示部分切纸,1表示全切

`opt`  打印选项，用于设置打印格式等,不同的类型，支持不同的选项，见后面打印格式说明。需要设置格式时传入，否则使用默认格式。



## 控制指令

- 走纸(`Feed`): 控制打印机走纸
- 切纸(`Cut`): 控制打印机切纸，支持全切和部分切纸



## 打印格式

### 通用

通用格式作用于任何的类型的行。 



| 属性名称 | 属性值              | 备注       |
| -------- | ------------------- | ---------- |
| `align`  | `left,center,right` | 左、中、右 |




### 文本


| 属性名称      | 属性值                 | 备注                                              |
| ------------- | ---------------------- | ------------------------------------------------- |
| `fontHeight`  | `1,2,3,4,5,6,7,8`      | 字体高度，以倍数表示                              |
| `fontWidth`   | `1,2,3,4,5,6,7,8`      | 字体宽度，以倍数表示                              |
| `fontType`    | `a,b,c`                | 打印机提供的三种字体                              |
| `underline`   | `none,one-dot,two-dot` | 下划线类型                                        |
| `lineSpacing` | 数字                   | 行距                                              |
| `bold`        | `true,false`           | 加粗                                              |
| `color`       | `white,black`          | 颜色模式，`white`表示白底黑字,`black`表示黑底白字 |

一个文本行的例子

```JSON
{
  "type": "text",
  "data": "凭  条",
  "opt": {
    "fontHeight": "3",
    "fontWidth": "3",
    "bold": "true",
    "align": "center",
    "underline": "none"
  }
}
```

### 二维码

- 二维码模型(`QrModel`): 可选择打印机内置二维码模型
- 纠错等级(`QrEccLevel`): 4种纠错等级
- 大小: 可设置二维码的大小

| 属性名称      | 属性值                 | 备注                                              |
| ------------- | ---------------------- | ------------------------------------------------- |
| `model`  | `m1,m2`      | 类型                              |
| `level`   | `l,m,q,h`      | 纠错等级                              |
| `size`    | 数值               | 二维码的大小                      |



一个二维码行的例子

```JSON
{
    "type": "qrc",
    "data": "12345678",
    "opt": {
        "size": "10",
        "align": "center"
    }
}
```

### 图片

- 模式(`BmpModel`): 位图在打印机缓存中的存储模式


| 属性名称      | 属性值                 | 备注                                              |
| ------------- | ---------------------- | ------------------------------------------------- |
| `model`  | `m0,m1,m32,m33`      | 分别表示：8点单密度、8点双密度、24点单密度、24点双密度        |


一个二维码行的例子

```JSON
{
    "type": "bmp",
    "data": "此处填写base64图片数据",
    "opt": {
        "size": "10",
        "model": "m33"
    }
}
```

## 例子


![print-img](docs/img/print-example.png)

对应的模版如下

```JSON
{
  "charsetName": "GB2312",
  "opt": null,
  "lines": [
    {
      "type": "text",
      "data": "凭  条",
      "opt": {
        "fontHeight": "3",
        "fontWidth": "3",
        "bold": "true",
        "align": "center"
      }
    },
    {
      "type": "feed",
      "data": "1",
      "opt": null
    },
    {
      "type": "text",
      "data": "凭条编号     : 20200101000123",
      "opt": {
        "align": "left"
      }
    },
    {
      "type": "feed",
      "data": "1",
      "opt": null
    },
    {
      "type": "text",
      "data": "申请人 : 20200101000123",
      "opt": {
        "align": "left"
      }
    },
    {
      "type": "feed",
      "data": "1",
      "opt": null
    },
    {
      "type": "text",
      "data": "申请人身份证号码 : ******19910719**46",
      "opt": {
        "align": "left"
      }
    },
    {
      "type": "feed",
      "data": "1",
      "opt": null
    },
    {
      "type": "text",
      "data": "申请业务 : XXXXX",
      "opt": {
        "align": "left"
      }
    },
    {
      "type": "feed",
      "data": "1",
      "opt": null
    },
    {
      "type": "text",
      "data": "申请时间 : 2020-10-30",
      "opt": {
        "align": "left"
      }
    },
    {
      "type": "feed",
      "data": "1",
      "opt": null
    },
    {
      "type": "text",
      "data": "所属机构 : XXXXXXX",
      "opt": {
        "align": "left"
      }
    },
    {
      "type": "feed",
      "data": "2",
      "opt": null
    },
    {
      "type": "text",
      "data": "----------------------------------",
      "opt": {
        "align": "center"
      }
    },
    {
      "type": "feed",
      "data": "1",
      "opt": null
    },
    {
      "type": "text",
      "data": "领取凭条后，请耐心在大厅等候通知，如需查询进度，请在终端机上扫描下方二维码",
      "opt": {
        "align": "left"
      }
    },
    {
      "type": "feed",
      "data": "5",
      "opt": null
    },
    {
      "type": "qrc",
      "data": "12345678",
      "opt": {
        "size": "10",
        "align": "center"
      }
    },
    {
      "type": "feed",
      "data": "10",
      "opt": null
    },
    {
      "type": "cut",
      "data": "0",
      "opt": null
    }
  ]
}
```




## 参考资料

- [ESC/POS Command Reference](https://www.epson-biz.com/modules/ref_escpos/index.php)



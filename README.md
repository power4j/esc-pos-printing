# ESC/POS 凭条打印

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
// hexCmd 为打印指令，16进制格式，一般厂商会提供测试工具，粘贴进去就能打印
String hexCmd = CmdEncoder.encodeHex(doc);
```

## 打印格式

### 通用

设置水平对齐方式(`Alignment`)： 左、中、右 

### 文本

- 颜色模式(`Color`)：白底黑字，黑底白字
- 字体(`FontType`): 可选择打印机内置字体
- 字号(`FontSize`): 可选择打印机内置字号
- 下划线(`Underline`): 无、1点粗、2点粗
- 加粗: 是否加粗

### 二维码

- 二维码模型(`QrModel`): 可选择打印机内置二维码模型
- 纠错等级(`QrEccLevel`): 4种纠错等级
- 大小: 可设置二维码的大小


## 控制指令

- 走纸(`Feed`): 控制打印机走纸
- 切纸(`Cut`): 控制打印机切纸，支持全切和部分切纸


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



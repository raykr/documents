# DrawTables

## 说明
DrawTables（datatables.st.js）是在DataTables.js的基础上，根据项目需求再次封装的表格插件。

实现功能：
* 支持两种远程数据加载，`url`和`action`方式。
* 支持json中定义表格配置，动态生成表格。例如列的数量、列名、默认数据操作按钮等。
* 支持前端添加自定义按钮和事件。
* 默认新增、修改按钮操作，可实现根据选中行的列动态生成表单。

## 使用
由于平台已封装该功能所需要的静态文件，所以只需关心使用方法即可，基本使用方法如下：
```html
<div class="box-body">
    <table id="example-table"></table>
</div>
```
```js
$(function () {
    var table = $('#example-table').DrawTable({
        action: {
            "class": "st.system.manage.enu.FrontAction",
            "configMethod": "createDictmain",
            "dataMethod": "createDictmainData",
            "operateMethod": "operateData"
        }
    });
})
```
以上这种方式即为根据现有平台封装的远程数据请求的写法。由于在前端开发中往往只能调试前端的json文件，所以可以采用以下方法进行开发与调试：
```js
$(function () {
    $('#example-table').DrawTable({
        url: "/front/resources/datagrid.json"
    });
})
```
`**注**`：`action`方式与`url`方式二者只能取其一；DrawTable的返回值是当前表格，可以使用DataTables提供针对表格操作的接口。

## API
### Option（表格渲染配置）

**url**

远程数据访问url，默认采用客户端分页功能，仅作为前端开发与调试用，联调测试和生产环境中请使用action。
```js
$('#example-table').DrawTable({
    url: "/front/resources/datagrid.json"
    //...
});
```

**action**

带星号的可以不写，取默认值

* `class`：向后台请求数据的Action（全包名）
* `*configMethod`：Action中获取表格配置项的方法名
* `*dataMethod`：Action中获取表格数据的方法名
* `*operateMethod`：默认数据操作（新增、修改、删除）功能处理的方法名

使用方法如下：
```js
$('#example-table').DrawTable({
    action: {
        "class": "st.system.manage.enu.FrontAction"
    }
    //...
});
```

**options**

options字段是开放给datatables的表格配置项，可以按照datatables的options填写即可，例如：
```js
$('#example-table').DrawTable({
    //...
    options: {
        pageLength: 4, // 每页显示的行数，默认为10
        serverSide: false, // 默认是服务器端分页，当为false时采用客户端分页
        searching: true // 启用表格右上角的搜索框，只有当客户端分页时可用
        //...
    }
});
```

**confparam**

配置项参数，类型为`object`。表格的渲染是分两步，一是获取表格的配置项数据，而是获取表格本身的数据信息。其中，配置项参数是在表格首次渲染传递一次，之后不会变动，而数据查询所用的参数是每次重绘表格都会变化，所以二者不同，并在此加以区分。注意二者的写法也略有不同。

```js
$('#example-table').DrawTable({
    //...
    confparam: {
        name: "张三",
        age: "18"
    }
});
```

**param**

表格数据参数，类型为`function`。发送给后台url的参数对象。可以用在form查询时，将form的值封装到param中，发送给后台。
```js
$('#example-table').DrawTable({
    //...
    param: function (d) {
        d.deviceType = $('#deviceType').val();
        d.repairePerson = $('#repairePerson').val();
    }
});
```

**toolbar**

前端自定义表格按钮。
```js
$('#example-table').DrawTable({
    //...
    toolbar: [
        {
            id: "btn001",
            text: "测试01",
            click: clickOne
        },{
            id: "btn002",
            text: "测试02",
            click: function () {
                alert("该按钮002的点击事件");
            }
        }
    ]
});
function clickOne() {
    alert("外部js方法");
}
```
由于可添加多个自定义按钮，所以toolbar是数组，内部每个对象就是一个按钮，其中
* `id`：按钮的id
* `text`：按钮的值
* `click`：按钮点击事件

**validate**

form表单验证
```js
$('#example-table').DrawTable({
    //...
    validate: {
        rules: {
            template_id: {required: true},
            exector: {required: true}
        }
    }
});
```

### 表格操作（表格渲染配置）

**表格重新加载数据**
```js
$('#table-id').DataTable().ajax.load();
```


## JSON
表格配置项JSON格式：
```json
{
    "result": true,
    "msg": "",
    "toolbar": [
        {
            "id": "add",
            "title": "添加"
        },
        {
            "id": "modify",
            "title": "修改"
        },
        {
            "id": "delete",
            "title": "删除"
        }
    ],
    "columns": [
        {
            "title": "路代码",
            "visible": false,
            "order": false,
            "width": 50,
            "data": "roadcode",
            "type": "text",
            "render": "return colRender(data,'roadcode','路代码','text','',false)"
        },
        {
            "title": "区",
            "visible": true,
            "order": false,
            "width": 150,
            "data": "distcode",
            "type": "dropdown",
            "render": "return colRender(data,'distcode','区','dropdown','distcode',false)"
        },
        {
            "title": "路拼",
            "visible": true,
            "order": false,
            "width": 100,
            "data": "roadspell",
            "type": "text",
            "render": "return colRender(data,'roadspell','路拼','text','',false)"
        },
        {
            "title": "路名",
            "visible": true,
            "order": false,
            "width": 200,
            "data": "roadname",
            "type": "text",
            "render": "return colRender(data,'roadname','路名','text','',false)"
        },
        {
            "title": "市",
            "visible": true,
            "order": false,
            "width": 150,
            "data": "citycode",
            "type": "dropdown",
            "render": "return colRender(data,'citycode','市','dropdown','citycode',false)"
        }
    ]
}
```
表格数据JSON格式：
```json
{
    "draw": "1",
    "result": true,
    "msg": "",
    "recordsTotal": "1520",
    "recordsFiltered": "1520",
    "data": [
        {
            "citycode": "1",
            "roadspell": "HN",
            "rnum": "1",
            "distcode": "11",
            "roadname": "湖南",
            "roadcode": "2"
        },
        {
            "citycode": "1",
            "roadspell": "SQ",
            "rnum": "2",
            "distcode": "12",
            "roadname": "上清",
            "roadcode": "3"
        }
    ]
}
```
枚举值JSON格式：
```json
{
    "result": true,
    "msg": "",
    "enuname": [
        {
            "id": "11",
            "name": "市南"
        },
        {
            "id": "12",
            "name": "市北"
        },
        {
            "id": "78",
            "name": "棘洪滩街道"
        }
    ]
}
```


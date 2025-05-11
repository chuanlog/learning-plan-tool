# 接口文档


**简介**:接口文档


**HOST**:localhost:8123


**Version**:2.0


**接口路径**:/v2/api-docs?group=默认分组


[TOC]



# 后端错误响应示例
```javascript
{
  "code": 0,
  "msg": "未知错误",
  "data": null
}
```

**说明**:报错有关的信息会在msg字段带回，前端只需要用弹窗提示用户




# 测试接口


## 测试接口（与业务无关联）


**接口地址**:`/api/test`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«string»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||string||
|msg||string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


# 用户接口


## 用户登录


**接口地址**:`/api/user/login`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "password": "123456",
  "username": "admin"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|userDTO|body|true|UserDTO|UserDTO|
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;username|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«UserLoginVO»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||UserLoginVO|UserLoginVO|
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;token|token|string||
|&emsp;&emsp;username|用户名|string||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": {
    "id": 1,
    "username": "admin",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0NjY3NDA0MH0.Vu-q9v9ETzvCFEWpROVkQBu6daVS4Bz6SLuoK4RwDurG36GYYPhVg4Q9jA0m0T3v3MEBqa4pGMke8TKjpgTvlg"
  }
}
```


## 用户注册


**接口地址**:`/api/user/register`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "password": "123456",
  "username": "cyc"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|userDTO|body|true|UserDTO|UserDTO|
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;username|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": null
}
```


# 课程接口


## 添加课程


**接口地址**:`/api/course`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "courseName": "高等数学4",
  "courseType": 0,
  "credits": 3,
  "id": 0,
  "preRequisiteCourseIds": [1,2,3],
  "totalHours": 88
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|courseDTO|courseDTO|body|true|CourseDTO|CourseDTO|
|&emsp;&emsp;courseName|||false|string||
|&emsp;&emsp;courseType|||false|integer(int32)||
|&emsp;&emsp;credits|||false|integer(int32)||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;preRequisiteCourseIds|||false|array|integer(int64)|
|&emsp;&emsp;totalHours|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": null
}
```


## 修改课程数据


**接口地址**:`/api/course`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "courseName": "高等数学3",
  "courseType": 0,
  "credits": 2,
  "id": 3,
  "preRequisiteCourseIds": [1,2],
  "totalHours": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|courseDTO|courseDTO|body|true|CourseDTO|CourseDTO|
|&emsp;&emsp;courseName|||false|string||
|&emsp;&emsp;courseType|||false|integer(int32)||
|&emsp;&emsp;credits|||false|integer(int32)||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;preRequisiteCourseIds|||false|array|integer(int64)|
|&emsp;&emsp;totalHours|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": null
}
```


## 删除课程


**接口地址**:`/api/course/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": null
}
```


## 获取用户所有课程


**接口地址**:`/api/course/{userId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId|userId|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«CourseVO»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||array|CourseVO|
|&emsp;&emsp;courseName||string||
|&emsp;&emsp;courseType||integer(int32)||
|&emsp;&emsp;credits||integer(int32)||
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;preRequisiteCourseIds||array|integer(int64)|
|&emsp;&emsp;totalHours||integer(int32)||
|&emsp;&emsp;userId||integer(int64)||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": [
    {
      "id": 1,
      "courseName": "高等数学1",
      "credits": 7,
      "totalHours": 72,
      "courseType": 0,
      "userId": 1,
      "preRequisiteCourseIds": []
    },
    {
      "id": 2,
      "courseName": "高等数学2",
      "credits": 7,
      "totalHours": 72,
      "courseType": 0,
      "userId": 1,
      "preRequisiteCourseIds": [
        1
      ]
    },
    {
      "id": 3,
      "courseName": "高等数学3",
      "credits": 2,
      "totalHours": 0,
      "courseType": 0,
      "userId": 1,
      "preRequisiteCourseIds": [
        1,
        2
      ]
    }
  ]
}
```
# 培养计划接口


## 获取培养计划


**接口地址**:`/api/plan/planninng`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "creditsOfCommonElectiveCourses": 2,
  "creditsOfProfessionalElectiveCourses": 3,
  "userId": 6
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|planQueryDTO|planQueryDTO|body|true|PlanQueryDTO|PlanQueryDTO|
|&emsp;&emsp;creditsOfCommonElectiveCourses|||false|integer(int32)||
|&emsp;&emsp;creditsOfProfessionalElectiveCourses|||false|integer(int32)||
|&emsp;&emsp;userId|||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«LearningPlan»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||LearningPlan|LearningPlan|
|&emsp;&emsp;allSemesters||array|Semester|
|&emsp;&emsp;&emsp;&emsp;commonElectiveCourseCredits||integer||
|&emsp;&emsp;&emsp;&emsp;courses||array|Course|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;courseName||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;courseType||integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;credits||integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;totalHours||integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userId||integer||
|&emsp;&emsp;&emsp;&emsp;majorBaseCourseCredits||integer||
|&emsp;&emsp;&emsp;&emsp;majorElectiveCourseCredits||integer||
|&emsp;&emsp;&emsp;&emsp;otherCourseCredits||integer||
|&emsp;&emsp;&emsp;&emsp;publicBaseCourseCredits||integer||
|&emsp;&emsp;&emsp;&emsp;totalCredits||integer||
|&emsp;&emsp;&emsp;&emsp;totalHours||integer||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": {
    "allSemesters": [
      {
        "totalCredits": 3,
        "totalHours": 54,
        "publicBaseCourseCredits": 0,
        "majorBaseCourseCredits": 0,
        "majorElectiveCourseCredits": 0,
        "commonElectiveCourseCredits": 3,
        "otherCourseCredits": 0,
        "courses": [
          {
            "id": 15,
            "courseName": "体育课1",
            "credits": 1,
            "totalHours": 36,
            "courseType": 6,
            "userId": 6
          },
          {
            "id": 12,
            "courseName": "机器学习",
            "credits": 3,
            "totalHours": 54,
            "courseType": 3,
            "userId": 6
          }
        ]
      },
      {
        "totalCredits": 3,
        "totalHours": 54,
        "publicBaseCourseCredits": 0,
        "majorBaseCourseCredits": 0,
        "majorElectiveCourseCredits": 0,
        "commonElectiveCourseCredits": 3,
        "otherCourseCredits": 0,
        "courses": [
          {
            "id": 16,
            "courseName": "体育课2",
            "credits": 1,
            "totalHours": 36,
            "courseType": 6,
            "userId": 6
          },
          {
            "id": 12,
            "courseName": "机器学习",
            "credits": 3,
            "totalHours": 54,
            "courseType": 3,
            "userId": 6
          }
        ]
      },
      {
        "totalCredits": 0,
        "totalHours": 0,
        "publicBaseCourseCredits": 0,
        "majorBaseCourseCredits": 0,
        "majorElectiveCourseCredits": 0,
        "commonElectiveCourseCredits": 0,
        "otherCourseCredits": 0,
        "courses": []
      },
      {
        "totalCredits": 5,
        "totalHours": 90,
        "publicBaseCourseCredits": 0,
        "majorBaseCourseCredits": 5,
        "majorElectiveCourseCredits": 0,
        "commonElectiveCourseCredits": 0,
        "otherCourseCredits": 0,
        "courses": [
          {
            "id": 8,
            "courseName": "高等数学",
            "credits": 5,
            "totalHours": 90,
            "courseType": 1,
            "userId": 6
          }
        ]
      },
      {
        "totalCredits": 4,
        "totalHours": 72,
        "publicBaseCourseCredits": 0,
        "majorBaseCourseCredits": 4,
        "majorElectiveCourseCredits": 0,
        "commonElectiveCourseCredits": 0,
        "otherCourseCredits": 0,
        "courses": [
          {
            "id": 9,
            "courseName": "线性代数",
            "credits": 4,
            "totalHours": 72,
            "courseType": 1,
            "userId": 6
          }
        ]
      },
      {
        "totalCredits": 3,
        "totalHours": 54,
        "publicBaseCourseCredits": 0,
        "majorBaseCourseCredits": 0,
        "majorElectiveCourseCredits": 3,
        "commonElectiveCourseCredits": 0,
        "otherCourseCredits": 0,
        "courses": [
          {
            "id": 10,
            "courseName": "数据结构",
            "credits": 3,
            "totalHours": 54,
            "courseType": 2,
            "userId": 6
          }
        ]
      },
      {
        "totalCredits": 3,
        "totalHours": 54,
        "publicBaseCourseCredits": 0,
        "majorBaseCourseCredits": 0,
        "majorElectiveCourseCredits": 3,
        "commonElectiveCourseCredits": 0,
        "otherCourseCredits": 0,
        "courses": [
          {
            "id": 11,
            "courseName": "算法设计",
            "credits": 3,
            "totalHours": 54,
            "courseType": 2,
            "userId": 6
          }
        ]
      },
      {
        "totalCredits": 0,
        "totalHours": 0,
        "publicBaseCourseCredits": 0,
        "majorBaseCourseCredits": 0,
        "majorElectiveCourseCredits": 0,
        "commonElectiveCourseCredits": 0,
        "otherCourseCredits": 0,
        "courses": [
          {
            "id": 13,
            "courseName": "人工智能",
            "credits": 2,
            "totalHours": 36,
            "courseType": 4,
            "userId": 6
          }
        ]
      }
    ]
  }
}
```
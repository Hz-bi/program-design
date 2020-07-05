# 2020 Summer 五班程序综合设计
## 团队项目日程安排
------
**7.2     听课后总结确定大致规划，会议讨论程序设计具体流程与方向**  

**7.3     初步调试运行程序，统一配置各成员IDE环境及依赖库，测试源码bug并修复**  

**7.4	 项目正式立项，建立github公开仓库，分配任务完成对应需求**  

**7.5     第二阶段任务按时完成，确定第三阶段方向与人员安排。**

------

#### 1、源码启动须知   
1.将jxl.jar文件复制到jre\lib\ext中或通过IDE设置加载jar包  
2.导入ch5文件夹作为工程文件夹  
3.全局替换"ch5."为""  
4.更改所有资源文件(如.xls、.jpg、.png)的路径为工程相对路径或者绝对路径  
5.确认全部代码没有语法错误后(一般上面改完就没有了)，运行AppWindow.java  
   (AppTest.java为无窗口界面启动入口,用于测试试题 AppWindow.java为GUI启动入口,为主要入口)  
注意：以上步骤仅在IDEA 2020.1环境中测试正常 如果使用Netbeans或者Eclipse 请自行参考修改  

>**上述步骤仓库内工程已经完成 IDEA可直接打开运行   将第二个program-design-master文件夹导入工程    
>运行AppWindow.java即可运行测试**

#### 2、github操作流程  

上传或更新文件时，根据以下步骤操作，点击upload files

![Nxkplj.png](https://s1.ax1x.com/2020/07/04/Nxkplj.png)

进入上传页面，填写对应的更新简要和具体描述（下面示例为，提交程序设计.docx）

<img src="https://s1.ax1x.com/2020/07/04/NxkQn1.png" alt="NxkQn1.png" style="zoom:50%;" />

同时上传成功之后可在commits看到自己的提交

<img src="https://s1.ax1x.com/2020/07/04/NxkhBq.png" alt="NxkhBq.png" style="zoom:50%;" />

<img src="https://s1.ax1x.com/2020/07/04/NxkIEV.png" alt="NxkIEV.png" style="zoom:50%;" />

#### 3、任务大致流程   

整体分为三步：

1. 修改现有代码明显bug
2. 根据书5.7要求完善代码
3. GUI修改和功能添加

#### 4、任务安排  

7.4—7.5（第二步）（根据书5.7要求完善代码），人员分工如下：

1. hh、xx  --- 需求①
2. tws        --- 需求②
3. jsh         --- 需求③
4. hzz        --- 需求④
5. fjh         --- 需求⑤

7.5—7.7（第三步），人员分工如下：

1. hh、xx    ---增添java与交通基础题库
2. hzz           ---设计UML类图
3. jsh            ---使用美化jar包优化swing界面
4. fjh             ---完善程序初始界面与数据库
5. tws、jsh  ---制作ppt
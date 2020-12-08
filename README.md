# 基于spring cloud的bbs
项目名称：bbs-project

服务器地址：121.196.110.249

项目仓库：https://github.com/yc-project-81/bbs-project.git

配置仓库：https://github.com/731871903/bbs-configs
*****
代码规范：

测试lwc分支
 1. 代码命名不能以下划线或者美元符号开头或者结尾
 2. 代码命名不能以中文拼音或者中文拼音与英文混合方式
 3. 类名使用UpperCamCamelCase风格，但DO、PO、DTO、VO、BO等除外
 4. 方法名、参数名、变量名统一使用lowerCamelCase，必须遵守驼峰命名
 5. 常量名全部大写，单词间用下划线隔开
 6. 抽象类必须以Abstract或者Base开头，异常类必须以Exception结尾，测试          
    类以测试的类的名称开头Test结尾
 7. 类型与中括号紧挨相连标示数组
 8. POJO类中布尔类型变量不要加is前缀
 9. 包名统一小写，点分隔符有且有一个自然语义单词
 10. 避免在父子类和不同代码块中采用相同变量名
 11. 避免不规范的缩写命名
 12. 在对元素命名时用完整单词组合表达其意
 13. 常量和变量命名时，表示类型放在词尾，如：idList、TERMINATED_TREAD_COUNT
 14. 接口、类、方法、模块使用设计模式，命名时要体现具体模式
 15. 接口类中的方法和属性不要加任何修饰符，并加上有效的javadoc。
 16. 接口和实现类的命名规则：
     1、对于service和dao类，实现类必须用Impl结尾；
     2、如果是形容能力的接口名称，取对应的形容词为接口名 AbstractTranslator实现 Translatable接口
 17. 枚举类名加Enum后缀，枚举成员名称全大写，单词间用下划线隔开
 18. 各层命名规范：
     A) Service/DAO层命名规约
        1.获取单个对象的方法用get做前缀
        2.获取多个对象的方法用list做前缀，如：listObjects
        3.获取统计值的方法用count做前缀
        4.插入方法用save/insert做前缀
        5.删除方法用delete/remove做前缀
        6.修改方法用update做前缀
     B）领域模型命名规范
        1.数据对象：xxxDO, xxx为数据库表名
        2.数据传输对象：xxxDTO,xxx为业务模型相关名称
        3.展示对象：xxxVO，xxx一般为网页名称
        4.POJO是对DO、DTO、VO、BO的统称，禁止xxxPOJO
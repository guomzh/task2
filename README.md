## 这是到qunar实习之前写的一些代码，用于巩固java开发最基础的操作，内容包括io(guava),字符串处理(正则表达式),排序，ssm框架，文件上传删除  
### 一、日志分析：从本题对应的附件中找到 access.log 文件，并编程根据文件内容统计以下数据：
    1. 请求总量；
    2. 请求最频繁的10个 HTTP 接口，及其相应的请求数量；
    3. POST 和 GET 请求量分别为多少；
    4. URI 格式均为 /AAA/BBB 或者 /AAA/BBB/CCC 格式，按 AAA 分类，输出各个类别下 URI 都有哪些。
### 二、有效代码行数统计：从本题对应的附件中找到 StringUtils.java 文件，将其复制到工程的 classpath 下，编程统计附件中的StringUtils.java文件的有效代码行数（一个数字）到一个新文件 validLineCount.txt 中。请注意，
               1) 有效不包括空行、注释；
               2) 考虑代码里有多行注释的情况；
               3) 不用考虑代码和注释混合在一行的情况。
### 三、文本解密：从本题对应的附件中找到 sdxl_prop.txt 和 sdxl_template.txt。根据sdxl_prop.txt中内容替换掉sdxl_template.txt 里$function(index)形式文字，将其还原成一本完整小说，写到文件sdxl.txt中，输出在classpath下。
           其中function有4种类型，替换规则如下：
           1) natureOrder 自然排序，即文本中排列顺序
           2) indexOrder 索引排序，文本中每行第一个数字为索引
           3) charOrder 文本排序，java的字符排序
           4) charOrderDESC 文本倒序，java的字符倒序
#### 五、Java模拟Linux命令处理和管道：请使用 Java 语言实现一个基本的 shell 模拟器。
                          
                          linux下有很多对文本进行操作的命令，比如cat filename 可以将文件的所有内容输出到控制台上。grep keyword filename可以将文件内容中包含keyword的行内容输出到控制台上。wc -l filename可以统计filename文件中的行数。
                          
                           |是代表管道的意思，管道左边命令的结果作为管道右边命令的输入，比如cat filename | grep exception | wc -l，可以用来统计一个文件中exception出现的行数。
                          
                          请实现一个功能，可以解析一个linux命令（只包含上面三个命令和上面提到的参数以及和管道一起完成的组合，其它情况不考虑），并将结果输出到控制台上，比如下面这几个例子：
                          
                          cat xx.txt
                          cat xx.txt | grep xml
                          wc -l xx.txt
                          cat xx.txt | grep xml | wc -l
                          
                          请注意程序对于未来加入其他命令的可扩展性和对于大规模输入的内存开销。
### 六、综合 ContentCounter：请搭建一个服务用以统计一篇文章当中的总字符数(包括标点符号)、汉字数、英文字符数、标点符号数。
                        
                        输入文件应由用户上传，后端请使用 Spring MVC + Spring + Mybatis。

# Captcha
基于 Servlet 的简单验证码图片生成
1、需要修改pom.xml文件，添加Servlet依赖
2、新建 Captcha 类
3、新建类 CaptchaServlet类，创建captcha 对象，调用文件流输出方法，指定禁止图像缓存，获取到图片
4、新建LoginServlet类 用于模拟登陆，实现验证码验证
5、修改 web.xml 文件，配置 Servlet 以及首页
6、新建一个 HTML 页面，调用创建好的 servlet 方法

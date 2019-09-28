package cn.edu.ustc.xk.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot10TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender; // 这个类是负责帮我们发送邮件的，我们只需要自动注入即可

    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        // 邮件设置
        message.setSubject("这是邮件主题");
        message.setText("这是邮件内容");
        message.setTo("kexu@mail.ustc.edu.cn");
        message.setFrom("1322097136@qq.com");

        mailSender.send(message);
    }

    @Test
    public void test02() throws Exception{
        // 创建一个复杂邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // 将multipart参数设置成true之后，我们才能上传文件
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("今晚开会");
        //因为这里面含有标签，所以我们需要将第二个参数设置为true才行
        helper.setText("<b style='color:red'>请大家做好准备</b>", true);
        helper.setTo("kexu@mail.ustc.edu.cn");
        helper.setFrom("1322097136@qq.com");

        // 上传文件，第一个参数是我们设置的文件名，第二个参数则是文件的路径
        helper.addAttachment("idea操作指南", new File("E:\\idea快捷键.docx"));
        helper.addAttachment("就业", new File("E:\\就业推荐表.doc"));

        mailSender.send(mimeMessage);
    }

}


package com.emailservice.service;


import com.emailservice.entity.Email;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements  EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;
    @Override
    public String sendEmail(Email email) {
        try{


            MimeMessage mimeMessage=javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);

            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(email.getTo());
            mimeMessageHelper.setCc(email.getCc());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(email.getBody());

//            MultipartFile[] file= email.getFile().toArray(new MultipartFile[0]);
//
//            for(int i=0;i<file.length;i++){
//
//                mimeMessageHelper.addAttachment(
//                        file[i].getOriginalFilename(),
//                        new ByteArrayResource(file[i].getBytes())
//                );
//            }

            javaMailSender.send(mimeMessage);

            return "Successfully send mail";

        }
        catch (Exception e){
            throw new RuntimeException();

        }
    }
}

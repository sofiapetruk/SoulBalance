package br.com.fiap.SoulBalance.consumers;

import br.com.fiap.SoulBalance.dto.EmailRequestDto;
import br.com.fiap.SoulBalance.entity.EmailEntity;
import br.com.fiap.SoulBalance.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${rabbitmq.queue.transaction}")
    public void listen(@Payload EmailRequestDto filter) {
        EmailEntity emailEntity = new EmailEntity();

        BeanUtils.copyProperties(filter, emailEntity);

        emailService.sendEmail(emailEntity);

        System.out.println("Email Status: " + emailEntity.getStatusEmail().toString());
    }
}

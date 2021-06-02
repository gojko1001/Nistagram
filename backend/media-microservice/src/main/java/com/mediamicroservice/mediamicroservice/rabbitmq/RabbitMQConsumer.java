package com.mediamicroservice.mediamicroservice.rabbitmq;

import com.mediamicroservice.mediamicroservice.domain.Post;
import com.mediamicroservice.mediamicroservice.dto.CreateCommentDto;
import com.mediamicroservice.mediamicroservice.dto.ImageBytesDto;
import com.mediamicroservice.mediamicroservice.service.interfaces.ICommentService;
import com.mediamicroservice.mediamicroservice.service.interfaces.IPostService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitMQConsumer {
    // TODO: verovatno obrisati
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IPostService postService;

    // TODO: verovatno obrisati
    @Value("${media.rabbitmq.exchange}")
    private String exchange;

    // TODO: verovatno obrisati
    @Value("${media.rabbitmq.routingkey}")
    private String routingkey;

    /*@RabbitListener(queues = "nistagram.queue")
    public String recievedMessage(String text) {
        System.out.println("Recieved Messge From RabbitMQ: " + text);
        return "yes";
    }*/

    @RabbitListener(queues = "createcomment.queue")
    public Object createCommentOnPost(CreateCommentDto createCommentDto) {
        return commentService.createCommentOnPost(createCommentDto);
    }

    /*@RabbitListener(queues = "userposts.queue")
    public List<ImageBytesDto> getImagesByUsername(String username) {
        List<Post> userPosts = postService.getUserImages(username);
        return postService.getImagesFiles(userPosts);
    }*/
}

package vn.vti.dtn2504.usermanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.vti.dtn2504.usermanager.client.NotificationClient;
import vn.vti.dtn2504.usermanager.client.payload.request.SendNotificationRequest;
import vn.vti.dtn2504.usermanager.dto.request.CreateAccountRequest;
import vn.vti.dtn2504.usermanager.dto.response.CreateAccountResponse;
import vn.vti.dtn2504.usermanager.dto.response.UserInfoResponse;
import vn.vti.dtn2504.usermanager.entity.User;
import vn.vti.dtn2504.usermanager.repository.UserRepository;
import vn.vti.dtn2504.usermanager.service.UserService;
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final NotificationClient notificationClient;
    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.notification.routingKey}")
    private String routingKey;
    @Value("${queue.notification.queue}")
    private String queueName;
    @Value("${queue.notification.exchange")
    private String exchangeName;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {
        log.info("Create account request: {}", request);
        User user =new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        User userSaved = userRepository.save(user);
        CreateAccountResponse response = new CreateAccountResponse();
        response.setUsername(request.getUsername());
        response.setName(request.getName());
        response.setEmail(request.getEmail());
        response.setAddress(request.getAddress());

        SendNotificationRequest sendNotificationRequest = new SendNotificationRequest();
        sendNotificationRequest.setMessage("tao tai khoan thnah cong roi nhe");
        sendNotificationRequest.setTitle("Welcome mail");
        sendNotificationRequest.setSendTo(userSaved.getEmail());

        rabbitTemplate.convertAndSend(exchangeName, routingKey, sendNotificationRequest);

        return response;
    }

    @Override
    public UserInfoResponse getUserByUsername(String username) {
        log.info("Get user by username: {}", username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        
        UserInfoResponse response = new UserInfoResponse();
        response.setUsername(user.getUsername());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setAddress(user.getAddress());
        return response;
    }
}

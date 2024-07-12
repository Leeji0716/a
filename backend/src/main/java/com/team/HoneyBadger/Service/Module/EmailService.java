package com.team.HoneyBadger.Service.Module;

import com.team.HoneyBadger.Entity.Email;
import com.team.HoneyBadger.Entity.EmailReceiver;
import com.team.HoneyBadger.Entity.SiteUser;
import com.team.HoneyBadger.Repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailRepository emailRepository;

    public Email save(String title, SiteUser sender) {
        return emailRepository.save(Email.builder().title(title).sender(sender).build());
    }

    public Email update(Email email, String content) {
        email.setContent(content);
        return emailRepository.save(email);
    }

    public Email getEmail(Long emailId) {
        return emailRepository.findById(emailId).orElseThrow(() -> new RuntimeException("Email not found with id: " + emailId));
    }

    public void findByUsernameDelete(Email email, String username) {
        List<EmailReceiver> emailReceiverList = email.getReceiverList();
        for (EmailReceiver emailReceiver : emailReceiverList) {
            if (emailReceiver.getReceiver().getUsername().equals(username)) {
                emailReceiverList.remove(emailReceiver);
                break;
            }
        }
    }
}
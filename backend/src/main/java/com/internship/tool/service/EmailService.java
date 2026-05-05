package com.internship.tool.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Profile("!test") // ❗ disables this during tests
public class EmailService {

    @Async
    public void sendDailyReminder(String toEmail, String userName) {
        log.info("Mock Email sent to {}", toEmail);
    }

    @Async
    public void sendDeadlineAlert(String toEmail, String userName, String deadline) {
        log.info("Mock Deadline Email sent to {}", toEmail);
    }
}
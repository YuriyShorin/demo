package ru.tbank.seminar1.solid.srp;

/**
 * ХОРОШО: {@code RegistrationService} только координирует рабочий процесс. Каждую
 * зону ответственности (валидация, персистентность, уведомление) он делегирует
 * отдельному классу, так что каждый класс сохраняет единственную ответственность.
 */
public class GoodRegistrationService {

    private final UserValidator validator;
    private final UserRepository repository;
    private final EmailService emailService;

    public GoodRegistrationService(UserValidator validator,
                                   UserRepository repository,
                                   EmailService emailService) {
        this.validator = validator;
        this.repository = repository;
        this.emailService = emailService;
    }

    public void register(User user) {
        validator.validate(user);
        repository.save(user);
        emailService.sendWelcome(user);
    }
}
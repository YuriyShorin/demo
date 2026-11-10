package ru.tbank.seminar1.solid.srp;

import java.util.ArrayList;
import java.util.List;

/**
 * ПЛОХО: нарушен принцип единственной ответственности (SRP).
 *
 * <p>{@code UserService} совмещает в одном классе ТРИ разные зоны ответственности:
 * <ol>
 *   <li>бизнес-правила / валидацию пользователя,</li>
 *   <li>персистентность (сохранение в «базу данных»),</li>
 *   <li>уведомления (отправку email).</li>
 * </ol>
 *
 * <p>Чем это вредно:
 * <ul>
 *   <li>изменение слоя персистентности (например, переход на другую СУБД) заставляет менять и код email;</li>
 *   <li>логику валидации нельзя переиспользовать, не потянув за собой зависимости БД и email;</li>
 *   <li>у класса три причины для изменения, поэтому любой мелкий фикс рискует сломать две другие зоны.</li>
 * </ul>
 */
public class BadUserService {

    private final List<User> storage = new ArrayList<>();

    /** Сохраняет пользователя И отправляет приветственное письмо одним методом. */
    public void register(User user) {
        if (!user.email().contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        storage.add(user);
        System.out.println("Sending welcome email to " + user.email());
    }

    /** Зона персистентности, утёкшая в сервис. */
    public List<User> findAll() {
        return storage;
    }

    /** Зона уведомлений, утёкшая в сервис. */
    public void sendPromo(User user) {
        System.out.println("Sending promo to " + user.email());
    }
}
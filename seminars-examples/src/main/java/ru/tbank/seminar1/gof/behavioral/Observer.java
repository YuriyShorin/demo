package ru.tbank.seminar1.gof.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * Поведенческий паттерн Observer — устанавливает зависимость «один-ко-многим»:
 * когда субъект меняет состояние, все наблюдатели автоматически уведомляются.
 */
public class Observer {

    /** Наблюдатель. */
    public interface Channel {
        void update(String news);
    }

    public static class NewsChannel implements Channel {
        private final String name;

        public NewsChannel(String name) {
            this.name = name;
        }

        public void update(String news) {
            System.out.println(name + " received: " + news);
        }
    }

    /** Субъект, рассылающий уведомления. */
    public static class NewsAgency {
        private final List<Channel> channels = new ArrayList<>();

        public void subscribe(Channel channel) {
            channels.add(channel);
        }

        public void unsubscribe(Channel channel) {
            channels.remove(channel);
        }

        public void publish(String news) {
            System.out.println("Agency publishes: " + news);
            for (Channel channel : channels) {
                channel.update(news);
            }
        }
    }

    public static void demo() {
        System.out.println("== Observer ==");
        NewsAgency agency = new NewsAgency();
        NewsChannel cnn = new NewsChannel("CNN");
        NewsChannel bbc = new NewsChannel("BBC");
        agency.subscribe(cnn);
        agency.subscribe(bbc);
        agency.publish("Breaking news!");
        agency.unsubscribe(cnn);
        agency.publish("Another story");
    }
}
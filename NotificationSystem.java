import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

abstract class NotificationChannel {
    public abstract void send(String recipient, String message);
}

class EmailNotification extends NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending Email to " + recipient + ": " + message);
    }
}

class SMSNotification extends NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}

class PushNotification extends NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending Push Notification to " + recipient + ": " + message);
    }
}

class NotificationManager {
    private NotificationChannel emailChannel;
    private NotificationChannel smsChannel;
    private NotificationChannel pushChannel;

    public NotificationManager() {
        this.emailChannel = new EmailNotification();
        this.smsChannel = new SMSNotification();
        this.pushChannel = new PushNotification();
    }

    public void notify(String channelType, List<String> recipients, String message) {
        NotificationChannel selectedChannel = null;
        if ("email".equalsIgnoreCase(channelType)) {
            selectedChannel = emailChannel;
        } else if ("sms".equalsIgnoreCase(channelType)) {
            selectedChannel = smsChannel;
        } else if ("push".equalsIgnoreCase(channelType)) {
            selectedChannel = pushChannel;
        } else {
            System.out.println("Notification channel '" + channelType + "' not found.");
            return;
        }
        for (String recipient : recipients) {
            selectedChannel.send(recipient, message);
        }
    }
}

public class NotificationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NotificationManager manager = new NotificationManager();

        System.out.println("Select notification type (email, sms, push): ");
        String channelType = scanner.nextLine();

        System.out.println("Enter your message: ");
        String message = scanner.nextLine();

        System.out.println("Enter the number of recipients: ");
        int numRecipients = Integer.parseInt(scanner.nextLine());
        List<String> recipients = new ArrayList<>();

        System.out.println("Enter recipient(s): ");
        for (int i = 0; i < numRecipients; i++) {
            String recipient = scanner.nextLine();
            recipients.add(recipient);
        }
        manager.notify(channelType, recipients, message);
        scanner.close();
    }
}

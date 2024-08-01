package banking_system;

public class NotificationService {
    public void sendNotification(String message, String recipientEmail) {
        // Implement email sending logic (using JavaMail API or any other email service)
        System.out.println("Sending notification to " + recipientEmail + ": " + message);
    }
}

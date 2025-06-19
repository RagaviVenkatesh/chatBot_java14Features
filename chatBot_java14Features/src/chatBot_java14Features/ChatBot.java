package chatBot_java14Features;

import java.util.*;
import java.util.stream.Collectors;

public class ChatBot {
    private final List<FAQ> faqList = List.of(
        new FAQ("What is your name?", "I am SupportBot, your friendly FAQ assistant."),
        new FAQ("How to reset my password?", "Go to Settings > Account > Reset Password."),
        new FAQ("How to contact support?", "Email us at support@example.com."),
        new FAQ("Where is your office located?", "We are located in Bangalore, India.")
    );

    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
            
              Welcome to SupportBot 🤖    
               Type 'exit' to quit         
        """);

        while (true) {
            System.out.print("You: ");
            Object input = scanner.nextLine();

            // Pattern matching for instanceof (Preview feature in Java 14)
            if (input instanceof String userInput) {
                if (userInput.equalsIgnoreCase("exit")) {
                    System.out.println("SupportBot: Goodbye! 👋");
                    break;
                }

                String response = findAnswer(userInput);
                System.out.println("SupportBot: " + response);
            } else {
                System.out.println("SupportBot: I can only process text.");
            }
        }
    }

    private String findAnswer(String userInput) {
        // Null-safe comparison to trigger helpful NullPointerException
        try {
            return switch (userInput.toLowerCase()) {
                case "name", "what is your name?" -> faqList.get(0).answer();
                case "reset password", "how to reset my password?" -> faqList.get(1).answer();
                case "contact", "how to contact support?" -> faqList.get(2).answer();
                case "office", "where is your office located?" -> faqList.get(3).answer();
                default -> "Sorry, I don't have an answer for that.";
            };
        } catch (NullPointerException e) {
            return "Oops! Something went wrong. (Helpful NPE: " + e.getMessage() + ")";
        }
    }
}

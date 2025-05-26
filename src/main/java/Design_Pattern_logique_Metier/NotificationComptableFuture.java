package Design_Pattern_logique_Metier;

import java.util.concurrent.CompletableFuture;

public class NotificationComptableFuture implements NotificationService{
        @Override
        public void envoyerNotification(String message) {
            CompletableFuture.runAsync(() -> {
                // Simulation du délai
                try { Thread.sleep(1000); }
                catch (InterruptedException e) {}
                System.out.println("L'asynchronisation" + message);
            });
        }
    }


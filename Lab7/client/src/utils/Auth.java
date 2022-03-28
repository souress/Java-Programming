package utils;

import commands.utils.CommandReceiver;
import main.Main;

import java.io.IOException;

public class Auth {
    public static void authenticate(CommandReceiver commandReceiver) throws InterruptedException, IOException, ClassNotFoundException {
        String actionOpportunities = "Введите sign_up для регистрации нового пользователя или sign_in для аутентификации пользователя";
        System.out.println(actionOpportunities);
        boolean actionPerformed = false;

        while (!actionPerformed) {
            String action;
            do {
                action = Main.scanner.nextLine();
            } while (action.equals(""));
            if (action.equals("sign_up")) {
                commandReceiver.register();
                actionPerformed = true;
            } else if (action.equals("sign_in")) {
                commandReceiver.signIn();
                actionPerformed = true;
            } else System.out.println("Команда не распознана.");
        }
    }
}

package com.toxicstoxm.Application;

import com.toxicstoxm.Config.YAJSIExampleSettingsBundle;
import com.toxicstoxm.Main;
import com.toxicstoxm.YAJSI.api.settings.YAJSISettingsManager;

import java.util.Scanner;

import static com.toxicstoxm.Config.YAJSIExampleSettingsBundle.*;

public class ExampleApp {

    // the SettingsManager is used to register new configurations
    // loading and saving are also handled by this class
    private YAJSISettingsManager settingsManager;

    // initialize function
    public void init() {

        // build a new SettingsManager
        // Auto update will try to automatically elevate all config files to the newest version if necessary
        // Setting a log implementation will enable the settings manager to display info of what's happening
        // if no log impl. is provided the log will be stored internally and can later be accessed by calling settingsManager.getMessages();
        // this ensures compatability and prevents some errors
        // the ConfigFile record holds the path to the actual config file and a URL to the default config in the internal resources folder
        // alongside the ConfigFile record the settings bundle class is provided
        settingsManager = YAJSISettingsManager.builder()
                .setAutoUpdate(true)
                .setLoggingImplementation(System.out::println)
                .buildWithConfigFile(
                        new YAJSISettingsManager.ConfigFile(
                                Main.projectDir + "/YAJSI-Example-config.yaml",
                                Main.class.getClassLoader().getResource("YAJSI-Example-config.yaml")
                        ),
                        YAJSIExampleSettingsBundle.class
                );

    }

    public void run() {
        // to do anything with a setting, simply get its instance and then call the getter or setter functions
        System.out.println(GreetingMessage.getInstance().get());
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (input.isBlank()) {
            System.out.print("Would you like to change this message? (y,n): ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("y")) {
                input = "";
                while (input.isBlank()) {
                    System.out.print("Please enter a new message: ");
                    input = scanner.nextLine();
                }
                // saving the new message, true specifies that the new value should be saved to the config
                // per default the settings are only saved for this session
                GreetingMessage.getInstance().set(input, true);
            } else if (input.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Invalid option. Try again.");
                input = "";
            }

        }
    }

    public void shutdown() {
        settingsManager.save();
    }
}

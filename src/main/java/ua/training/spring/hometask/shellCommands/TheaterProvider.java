package ua.training.spring.hometask.shellCommands;

import org.springframework.shell.plugin.support.DefaultPromptProvider;

public class TheaterProvider extends DefaultPromptProvider {

    @Override
    public String getPrompt() {
        return "theater";
    }

    @Override
    public String getProviderName() {
        return "Theater Manager";
    }
}

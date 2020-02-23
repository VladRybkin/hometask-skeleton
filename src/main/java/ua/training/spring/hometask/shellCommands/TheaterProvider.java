package ua.training.spring.hometask.shellCommands;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TheaterProvider extends DefaultPromptProvider {

    @Override
    public String getPrompt() {
        return "theater-shell";
    }

    @Override
    public String getProviderName() {
        return "Theatre prompt";
    }
}

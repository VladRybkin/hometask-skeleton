package ua.training.spring.hometask.shellCommands;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.BannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TheatreShellBannerProvider implements BannerProvider {

    @Override
    public String getBanner() {
        return "======================================="
                + OsUtils.LINE_SEPARATOR
                + "          Movie Theater Shell          "
                + OsUtils.LINE_SEPARATOR
                + "======================================="
                + OsUtils.LINE_SEPARATOR
                + "Version:"
                + this.getVersion();
    }

    @Override
    public String getVersion() {
        return "1.2";
    }

    @Override
    public String getWelcomeMessage() {
        return "Welcome to Theatre manager";
    }

    @Override
    public String getProviderName() {
        return "Theatre provider";
    }
}


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
        StringBuilder banner = new StringBuilder();
        banner.append("=======================================")
                .append(OsUtils.LINE_SEPARATOR);
        banner.append("          Movie Theater Shell          ")
                .append(OsUtils.LINE_SEPARATOR);
        banner.append("=======================================")
                .append(OsUtils.LINE_SEPARATOR);
        banner.append("Version:")
                .append(this.getVersion());
        return banner.toString();
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


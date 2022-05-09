package fi.dy.masa.malilib.gui.tab;

import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.client.gui.screen.Screen;
import fi.dy.masa.malilib.gui.BaseScreen;
import fi.dy.masa.malilib.gui.BaseTabbedScreen;
import fi.dy.masa.malilib.gui.widget.button.ButtonActionListener;
import fi.dy.masa.malilib.util.StringUtils;
import fi.dy.masa.malilib.util.data.ModInfo;

public class BaseScreenTab implements ScreenTab
{
    protected final String name;
    protected final String translationKey;
    protected final Function<Screen, BaseScreen> screenFactory;
    protected final Function<BaseTabbedScreen, ButtonActionListener> listenerFactory;
    protected final Predicate<Screen> screenChecker;
    @Nullable protected String hoverTextTranslationKey;

    public BaseScreenTab(ModInfo modInfo, String name, Predicate<Screen> screenChecker,
                         Function<Screen, BaseScreen> screenFactory)
    {
        this(name, modInfo.getModId() + ".screen.tab." + name, screenChecker, screenFactory);
    }

    public BaseScreenTab(String name, String translationKey, Predicate<Screen> screenChecker,
                         Function<Screen, BaseScreen> screenFactory)
    {
        this.name = name;
        this.translationKey = translationKey;
        this.screenChecker = screenChecker;
        this.screenFactory = screenFactory;
        this.listenerFactory = (scr) -> (mBtn, btn) -> this.openTab(scr);
    }

    public BaseScreenTab(String name, String translationKey, Predicate<Screen> screenChecker,
                         Function<Screen, BaseScreen> screenFactory,
                         Function<BaseTabbedScreen, ButtonActionListener> listenerFactory)
    {
        this.name = name;
        this.translationKey = translationKey;
        this.screenChecker = screenChecker;
        this.screenFactory = screenFactory;
        this.listenerFactory = listenerFactory;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public String getDisplayName()
    {
        return StringUtils.translate(this.translationKey);
    }

    @Nullable
    @Override
    public String getHoverText()
    {
        return this.hoverTextTranslationKey;
    }

    public BaseScreenTab setHoverText(@Nullable String hoverTextTranslationKey)
    {
        this.hoverTextTranslationKey = hoverTextTranslationKey;
        return this;
    }

    @Override
    public boolean canUseCurrentScreen(@Nullable Screen currentScreen)
    {
        return this.screenChecker.test(currentScreen);
    }

    @Override
    public BaseScreen createScreen(Screen currentScreen)
    {
        return this.screenFactory.apply(currentScreen);
    }

    @Override
    public ButtonActionListener getButtonActionListener(final BaseTabbedScreen currentScreen)
    {
        return this.listenerFactory.apply(currentScreen);
    }

    /**
     * Switches the active tab to this tab, or opens a new screen if the current screen
     * is not suitable for this tab.
     */
    public boolean openTab(@Nullable BaseTabbedScreen currentScreen)
    {
        if (currentScreen != null && this.canUseCurrentScreen(currentScreen))
        {
            currentScreen.switchToTab(this);
        }
        else
        {
            this.createAndOpenScreen(currentScreen);
        }

        return true;
    }
}

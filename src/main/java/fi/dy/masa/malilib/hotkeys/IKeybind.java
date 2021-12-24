package fi.dy.masa.malilib.hotkeys;

import java.util.List;
import org.jetbrains.annotations.Nullable;
import fi.dy.masa.malilib.config.IConfigResettable;
import fi.dy.masa.malilib.config.IStringRepresentable;

public interface IKeybind extends IConfigResettable, IStringRepresentable
{
    boolean isValid();

    /**
     * Returns true if the keybind was pressed down during the current game tick.
     */
    boolean isPressed();

    /**
     * Returns true if the keybind is currently being held down.
     */
    boolean isKeybindHeld();

    /**
     * Checks and updated the pressed status, and fires the callback, if one is set.
     * Returns true if further processing of the just pressed key should be cancelled.
     * This return value can be determined by the callback, if one has been set.
     * Without a callback, the return value will be false to not cancel further processing.
     * @return
     */
    boolean updateIsPressed();

    KeybindSettings getSettings();

    /**
     * Set the settings for this keybind.
     * @param settings
     */
    void setSettings(KeybindSettings settings);

    void clearKeys();

    void addKey(int keyCode);

    void removeKey(int keyCode);

    /**
     * Check if this keybind is only a single key, matching the given key code.
     * This is mainly meant for checking equality against vanilla keybinds.
     * @param keyCode
     * @return
     */
    boolean matches(int keyCode);

    boolean overlaps(IKeybind other);

    void tick();

    String getKeysDisplayString();

    List<Integer> getKeys();

    void setCallback(@Nullable IHotkeyCallback callback);

    boolean areSettingsModified();

    void resetSettingsToDefaults();
}

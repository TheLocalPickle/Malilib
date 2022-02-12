package fi.dy.masa.malilib.gui.widget;

import javax.annotation.Nullable;
import fi.dy.masa.malilib.gui.icon.Icon;
import fi.dy.masa.malilib.gui.util.ScreenContext;

public class IconWidget extends InteractableWidget
{
    protected boolean doHighlight;
    protected boolean enabled;

    public IconWidget(@Nullable Icon icon)
    {
        super(icon.getWidth(), icon.getHeight());

        this.setIcon(icon);
    }

    @Override
    public void setIcon(@Nullable Icon icon)
    {
        super.setIcon(icon);

        this.updateWidth();
        this.updateHeight();
    }

    public IconWidget setUseEnabledVariant(boolean enabled)
    {
        this.enabled = enabled;
        return this;
    }

    public IconWidget setDoHighlight(boolean doHighlight)
    {
        this.doHighlight = doHighlight;
        return this;
    }

    @Override
    public void updateWidth()
    {
        Icon icon = this.getIcon();

        if (icon != null)
        {
            int width = icon.getWidth();

            if (this.getBackgroundRenderer().getNormalSettings().isEnabled())
            {
                int bw = this.getBorderRenderer().getNormalSettings().getActiveBorderWidth();
                width += this.padding.getLeft() + this.padding.getRight() + bw * 2;
            }

            this.setWidth(width);
        }
        else
        {
            this.setWidth(0);
        }
    }

    @Override
    public void updateHeight()
    {
        Icon icon = this.getIcon();

        if (icon != null)
        {
            int height = icon.getHeight();

            if (this.getBackgroundRenderer().getNormalSettings().isEnabled())
            {
                int bw = this.getBorderRenderer().getNormalSettings().getActiveBorderWidth();
                height += this.padding.getTop() + this.padding.getBottom() + bw * 2;
            }

            this.setHeight(height);
        }
        else
        {
            this.setHeight(0);
        }
    }

    @Override
    public void renderAt(int x, int y, float z, ScreenContext ctx)
    {
        boolean hovered = this.doHighlight && this.isHoveredForRender(ctx);

        this.renderWidgetBackgroundAndBorder(x, y, z, ctx);
        this.renderText(x, y, z, hovered, ctx);

        if (this.getBackgroundRenderer().getNormalSettings().isEnabled())
        {
            int bw = this.getBorderRenderer().getNormalSettings().getActiveBorderWidth();
            x += this.padding.getLeft() + bw;
            y += this.padding.getTop() + bw;
        }

        this.renderIcon(x, y, z, this.enabled, hovered, ctx);
    }
}

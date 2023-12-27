package malilib.util.game.wrap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class RegistryUtils
{
    public static Block getBlockByIdStr(String name)
    {
        try
        {
            return Block.REGISTRY.getObject(new ResourceLocation(name));
        }
        catch (Exception e)
        {
            return Blocks.AIR;
        }
    }

    public static Block getBlockById(ResourceLocation id)
    {
        Block block = Block.REGISTRY.getObject(id);
        return block != null ? block : Blocks.AIR;
    }

    @Nullable
    public static ResourceLocation getBlockId(Block block)
    {
        return Block.REGISTRY.getNameForObject(block);
    }

    @Nullable
    public static ResourceLocation getBlockId(IBlockState state)
    {
        return Block.REGISTRY.getNameForObject(state.getBlock());
    }

    public static String getBlockIdStr(Block block)
    {
        ResourceLocation id = Block.REGISTRY.getNameForObject(block);
        return id != null ? id.toString() : "?";
    }

    public static String getBlockIdStr(IBlockState state)
    {
        ResourceLocation id = Block.REGISTRY.getNameForObject(state.getBlock());
        return id != null ? id.toString() : "?";
    }

    public static Collection<ResourceLocation> getRegisteredBlockIds()
    {
        return Block.REGISTRY.getKeys();
    }

    public static List<Block> getSortedBlockList()
    {
        List<Block> blocks = new ArrayList<>();

        for (Object block : Block.REGISTRY)
        {
            blocks.add((Block) block);
        }

        blocks.sort(Comparator.comparing(RegistryUtils::getBlockIdStr));

        return blocks;
    }

    public static Item getItemByIdStr(String name)
    {
        try
        {
            return Item.REGISTRY.getObject(new ResourceLocation(name));
        }
        catch (Exception e)
        {
            return Items.AIR;
        }
    }

    public static Item getItemById(ResourceLocation id)
    {
        Item item = Item.REGISTRY.getObject(id);
        return item != null ? item : Items.AIR;
    }

    @Nullable
    public static ResourceLocation getItemId(Item item)
    {
        return Item.REGISTRY.getNameForObject(item);
    }

    public static String getItemIdStr(Item item)
    {
        ResourceLocation id = Item.REGISTRY.getNameForObject(item);
        return id != null ? id.toString() : "?";
    }

    public static Collection<ResourceLocation> getRegisteredItemIds()
    {
        return Item.REGISTRY.getKeys();
    }

    public static List<Item> getSortedItemList()
    {
        List<Item> items = new ArrayList<>();

        for (Object item : Item.REGISTRY)
        {
            items.add((Item) item);
        }

        items.sort(Comparator.comparing(RegistryUtils::getItemIdStr));

        return items;
    }
}

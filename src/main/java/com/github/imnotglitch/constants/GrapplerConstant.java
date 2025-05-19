package com.github.imnotglitch.constants;

import com.github.imnotglitch.util.item.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

public class GrapplerConstant {
    public static final ItemStackBuilder GRAPPLER = new ItemStackBuilder(Material.FISHING_ROD)
            .displayName("§aGrappler")
            .addNbt("grappler", "grappler")
            .setUnbreakable(true)
            .itemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);


}

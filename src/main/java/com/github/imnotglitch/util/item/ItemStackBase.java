package com.github.imnotglitch.util.item;

import com.github.imnotglitch.util.nbt.NBTItemBuilder;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public interface ItemStackBase<T extends ItemStackBuilder> {

    T displayName(String name);

    T lore(List<String> lore);

    T addLore(String... lines);

    T clearLore();

    T type(Material material);

    Material type();

    T loreWithPlaceholder(List<String> lore, ImmutableMap<String, String> placeholders);

    T lore(String... lines);

    T addLoreIf(boolean condition, String... lines);

    T amount(int amount);

    T durability(int durability);

    T data(int data);

    T skullOwner(String name);

    T allFlags();

    T itemFlags(ItemFlag... flags);

    T glow(boolean state);

    T glow();

    T enchantment(Enchantment enchant, int level);

    T removeEnchantment(Enchantment enchant);

    T setNbt(NBTTagCompound nbt);

    NBTItemBuilder nbtBuilder();

    T addNbt(String key, String value);

    T markAntiDupeId();

    T patterns(List<Pattern> patterns);

    T effect(PotionEffect potionEffect, boolean overwrite);

    T color(Color color);

    T color(DyeColor color);

    T clearColor();

    T setUnbreakable(boolean unbreakable);

    List<String> getLore();

    String displayName();

    NBTTagCompound getNbtCompound();

    String getNbt(String key);

    boolean hasNBT(String key);

    int getNBTInteger(String nbtKey);

    boolean getNBTBoolean(String nbtKey);

    ItemStack build();
}
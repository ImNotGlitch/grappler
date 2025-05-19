package com.github.imnotglitch.util.item;

import com.github.imnotglitch.util.nbt.NBTItemBuilder;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Atomics;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ItemStackBuilder implements ItemStackBase<ItemStackBuilder> {

    protected ItemStack itemStack;
    protected ItemMeta itemMeta;

    protected NBTTagCompound nbt;

    public ItemStackBuilder() {}


    public ItemStackBuilder(Material material) {
        final ItemStack itemStack = new ItemStack(material);

        this.nbt = new NBTTagCompound();
        this.itemMeta = itemStack.getItemMeta();
        this.itemStack = itemStack;
    }

    public ItemStackBuilder(ItemStack itemStack) {
        itemMeta = itemStack.getItemMeta();

        final net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        nbt = (nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());

        nmsItem.setTag(nbt);

        this.itemStack = CraftItemStack.asBukkitCopy(nmsItem);
    }

    public static ItemStackBuilder of(ItemStack itemStack) {
        return new ItemStackBuilder(itemStack);
    }

    public ItemStackBuilder copy(ItemStack itemStack) {
        return new ItemStackBuilder(itemStack);
    }

    @Override
    public ItemStackBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    @Override
    public ItemStackBuilder durability(int durability) {
        itemStack.setDurability((short) durability);
        return this;
    }

    @Override
    public ItemStackBuilder data(int data) {
        itemStack.setData(new MaterialData(itemStack.getType(), (byte) data));
        return this;
    }

    @Override
    public ItemStackBuilder skullOwner(String name) {
        if (itemStack.getType().equals(Material.SKULL_ITEM) && itemStack.getDurability() == 3) {
            final SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
            skullMeta.setOwner(name);

            itemStack.setItemMeta(itemStack.getItemMeta());
        }

        return this;
    }

    @Override
    public ItemStackBuilder glow(boolean state) {
        return state ? glow() : this;
    }

    @Override
    public String displayName() {
        return itemMeta.getDisplayName();
    }

    @Override
    public ItemStackBuilder displayName(String name) {
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        return this;
    }

    @Override
    public ItemStackBuilder loreWithPlaceholder(List<String> lore, ImmutableMap<String, String> placeholders) {
        final AtomicReference<List<String>> atomicReference = Atomics.newReference(lore);

        placeholders.forEach((key, placeholder) -> atomicReference.getAndUpdate(get -> get.stream()
                .map(line -> line.replace(key, placeholder).replace("&", "§"))
                .collect(Collectors.toList())));

        itemMeta.setLore(atomicReference.get());

        return this;
    }

    @Override
    public ItemStackBuilder allFlags() {
        itemMeta.addItemFlags(ItemFlag.values());
        return this;
    }

    @Override
    public ItemStackBuilder itemFlags(ItemFlag... flags) {
        itemMeta.addItemFlags(flags);
        return this;
    }

    @Override
    public ItemStackBuilder glow() {
        nbt.set("ench", new NBTTagList());
        return this;
    }

    @Override
    public ItemStackBuilder enchantment(Enchantment enchant, int level) {
        itemMeta.addEnchant(enchant, level, true);
        return this;
    }

    @Override
    public ItemStackBuilder removeEnchantment(Enchantment enchant) {
        itemMeta.removeEnchant(enchant);
        return this;
    }

    @Override
    public ItemStackBuilder setNbt(NBTTagCompound nbt) {
        this.nbt = nbt;
        return this;
    }

    @Override
    public NBTItemBuilder nbtBuilder() {
        return new NBTItemBuilder(nbt, this);
    }

    @Override
    public ItemStackBuilder addNbt(String key, String value) {
        nbt.setString(key, value);
        return this;
    }

    @Override
    public ItemStackBuilder markAntiDupeId() {
        nbt.setBoolean("anti-dupe", true);
        return this;
    }

    @Override
    public ItemStackBuilder patterns(List<Pattern> patterns) {
        if (itemStack.getType() == Material.BANNER) {
            final BannerMeta bannerMeta = (BannerMeta) itemMeta;
            bannerMeta.setPatterns(patterns);

            itemMeta = bannerMeta;
            return this;
        }

        return this;
    }

    @Override
    public ItemStackBuilder effect(PotionEffect potionEffect, boolean overwrite) {
        if (itemMeta instanceof PotionMeta) {
            final PotionMeta potionMeta = (PotionMeta) itemMeta;
            potionMeta.addCustomEffect(potionEffect, overwrite);

            return this;
        }

        return this;
    }

    @Override
    public ItemStackBuilder color(Color color) {
        if (itemStack.getType() == Material.LEATHER_HELMET ||
                itemStack.getType() == Material.LEATHER_CHESTPLATE ||
                itemStack.getType() == Material.LEATHER_LEGGINGS ||
                itemStack.getType() == Material.LEATHER_BOOTS) {
            final LeatherArmorMeta armorMeta = (LeatherArmorMeta) itemMeta;
            armorMeta.setColor(color);
        }

        return this;
    }

    @Override
    public ItemStackBuilder color(DyeColor color) {
        if (itemStack.getType() == Material.BANNER) {
            final BannerMeta bannerMeta = (BannerMeta) itemMeta;
            bannerMeta.setBaseColor(color);

            itemMeta = bannerMeta;
            return this;
        }

        return this;
    }

    @Override
    public ItemStackBuilder clearColor() {
        if (itemStack.getType() == Material.LEATHER_HELMET ||
                itemStack.getType() == Material.LEATHER_CHESTPLATE ||
                itemStack.getType() == Material.LEATHER_LEGGINGS ||
                itemStack.getType() == Material.LEATHER_BOOTS) {
            final LeatherArmorMeta armorMeta = (LeatherArmorMeta) itemMeta;
            armorMeta.setColor(null);
        }

        if (itemStack.getType() == Material.BANNER) {
            final BannerMeta bannerMeta = (BannerMeta) itemMeta;
            bannerMeta.setBaseColor(null);
        }

        return this;
    }

    @Override
    public List<String> getLore() {
        return itemMeta.getLore();
    }

    @Override
    public ItemStackBuilder lore(List<String> lore) {
        List<String> currentLore = itemMeta.getLore();
        if (currentLore == null) {
            currentLore = new ArrayList<>();
        }

        lore.replaceAll(s -> s.replace("&", "§"));
        currentLore.addAll(lore);

        itemMeta.setLore(currentLore);
        return this;
    }

    @Override
    public ItemStackBuilder lore(String... lines) {
        List<String> lore = itemMeta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }

        lore.addAll(Arrays.stream(lines)
                .map(line -> line.replace("&", "§"))
                .collect(Collectors.toList()));

        itemMeta.setLore(lore);
        return this;
    }

    public ItemStackBuilder addLoreIf(boolean condition, String... lines) {
        if (condition) {
            return lore(lines);
        }

        return this;
    }

    @Override
    public ItemStackBuilder addLore(String... lines) {
        List<String> lore = itemMeta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }

        lore.addAll(List.of(lines));
        itemMeta.setLore(lore);
        return this;
    }

    @Override
    public ItemStackBuilder clearLore() {
        this.itemMeta.setLore(Collections.emptyList());
        return this;
    }

    @Override
    public ItemStackBuilder type(Material material) {
        this.itemStack.setType(material);

        return this;
    }

    @Override
    public Material type() {
        return build().getType();
    }

    @Override
    public NBTTagCompound getNbtCompound() {
        return nbt;
    }

    @Override
    public String getNbt(String key) {
        return nbt.getString(key);
    }

    @Override
    public boolean hasNBT(String key) {
        return nbt.hasKey(key);
    }

    @Override
    public int getNBTInteger(String nbtKey) {
        return nbt.getInt(nbtKey);
    }

    @Override
    public boolean getNBTBoolean(String nbtKey) {
        return nbt.getBoolean(nbtKey);
    }

    @Override
    public ItemStackBuilder setUnbreakable(boolean unbreakable) {
        itemMeta.spigot().setUnbreakable(unbreakable);
        return this;
    }

    @Override
    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);

        return addFinalAtributes();
    }

    protected ItemStack addFinalAtributes() {
        final net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        nmsItem.setTag(nbt);

        final ItemStack finalItem = CraftItemStack.asBukkitCopy(nmsItem);
        final ItemMeta finalMeta = finalItem.getItemMeta();

        finalMeta.setDisplayName(itemMeta.getDisplayName());
        finalMeta.setLore(itemMeta.getLore());
        finalMeta.spigot().setUnbreakable(itemMeta.spigot().isUnbreakable());

        itemMeta.getEnchants().forEach((enchant, level) -> finalMeta.addEnchant(enchant, level, true));
        itemMeta.getItemFlags().forEach(finalMeta::addItemFlags);

        finalItem.setItemMeta(finalMeta);

        return finalItem;
    }
}
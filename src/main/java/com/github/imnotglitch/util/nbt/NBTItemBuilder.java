package com.github.imnotglitch.util.nbt;

import com.github.imnotglitch.util.item.ItemStackBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

@RequiredArgsConstructor
public class NBTItemBuilder {

    @Getter
    protected final NBTTagCompound nbt;
    protected final ItemStackBuilder itemStackBuilder;

    public boolean containsTag(String key) {
        return nbt.hasKey(key);
    }

    public NBTItemBuilder setString(String key, String value) {
        nbt.setString(key, value);
        return this;
    }

    public NBTItemBuilder setLong(String key, long value) {
        nbt.setLong(key, value);
        return this;
    }

    public NBTItemBuilder setInt(String key, int value) {
        nbt.setInt(key, value);
        return this;
    }

    public NBTItemBuilder setByte(String key, byte value) {
        nbt.setByte(key, value);
        return this;
    }

    public NBTItemBuilder setShort(String key, short value) {
        nbt.setShort(key, value);
        return this;
    }

    public NBTItemBuilder setFloat(String key, float value) {
        nbt.setFloat(key, value);
        return this;
    }

    public NBTItemBuilder setDouble(String key, double value) {
        nbt.setDouble(key, value);
        return this;
    }

    public NBTItemBuilder setByteArray(String key, byte[] value) {
        nbt.setByteArray(key, value);
        return this;
    }

    public NBTItemBuilder setIntArray(String key, int[] value) {
        nbt.setIntArray(key, value);
        return this;
    }

    public NBTItemBuilder setBoolean(String key, boolean value) {
        nbt.setBoolean(key, value);
        return this;
    }

    public NBTItemBuilder setCompound(String key, NBTTagCompound value) {
        nbt.set(key, value);
        return this;
    }

    public String getString(String key) {
        return nbt.getString(key);
    }

    public long getLong(String key) {
        return nbt.getLong(key);
    }

    public Integer getInt(String key) {
        return nbt.getInt(key);
    }

    public byte getByte(String key) {
        return nbt.getByte(key);
    }

    public short getShort(String key) {
        return nbt.getShort(key);
    }

    public float getFloat(String key) {
        return nbt.getFloat(key);
    }

    public double getDouble(String key) {
        return nbt.getDouble(key);
    }

    public byte[] getByteArray(String key) {
        return nbt.getByteArray(key);
    }

    public int[] getIntArray(String key) {
        return nbt.getIntArray(key);
    }

    public boolean getBoolean(String key) {
        return nbt.getBoolean(key);
    }

    public NBTTagCompound getCompound(String key) {
        return nbt.getCompound(key);
    }

    public ItemStackBuilder apply() {
        itemStackBuilder.setNbt(nbt);
        return itemStackBuilder;
    }
}

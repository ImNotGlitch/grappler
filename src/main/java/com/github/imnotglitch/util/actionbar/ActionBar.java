package com.github.imnotglitch.util.actionbar;

import com.google.gson.JsonObject;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {
    public static void sendToPlayer(Player player, String text) {
        JsonObject json = new JsonObject();
        json.addProperty("text", text);
        PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(json.toString()), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}

package com.github.imnotglitch;

import com.github.imnotglitch.commands.CustomCommand;
import com.github.imnotglitch.commands.impl.GiveCommand;
import com.github.imnotglitch.events.GrapplerEvent;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class Grappler extends JavaPlugin {


    @Override
    public void onEnable() {
        registerCommands(new GiveCommand());

        PluginManager pluginManager = Bukkit.getPluginManager();


        pluginManager.registerEvents(new GrapplerEvent(), this);
    }


    @SneakyThrows
    private void registerCommands(CustomCommand... customCommands) {
        final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        commandMapField.setAccessible(true);

        final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());

        for (CustomCommand customCommand : customCommands)
            commandMap.register(customCommand.getName(), customCommand);
    }

}
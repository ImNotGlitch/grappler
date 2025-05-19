package com.github.imnotglitch.commands.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.imnotglitch.commands.CustomCommand;

import java.util.ArrayList;
import java.util.List;

import static com.github.imnotglitch.constants.GrapplerConstant.GRAPPLER;

public class GiveCommand extends CustomCommand {

    public GiveCommand() {
        super("grappler", "classic.build", true, "construtor");
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {
        final Player player = (Player) commandSender;

        player.getInventory().addItem(GRAPPLER.build());
        player.sendMessage("§aVocê recebeu um grappler.");
    }


}

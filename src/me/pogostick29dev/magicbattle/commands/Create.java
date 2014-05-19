package me.pogostick29dev.magicbattle.commands;

import me.pogostick29dev.magicbattle.ArenaManager;
import me.pogostick29dev.magicbattle.MessageManager;
import me.pogostick29dev.magicbattle.MessageManager.MessageType;
import me.pogostick29dev.magicbattle.SettingsManager;

import org.bukkit.entity.Player;

public class Create extends MagicCommand {

	public void onCommand(Player p, String[] args) {
		int id = ArenaManager.getInstance().getArenas().size() + 1;

		SettingsManager.getArenas().createConfigurationSection("arenas." + id);
		SettingsManager.getArenas().set("arenas." + id + ".numPlayers", 10);

		MessageManager.getInstance().msg(p, MessageType.GOOD, "Created Arena " + id + "!");

		ArenaManager.getInstance().setupArenas();
	}
	
	public Create() {
		super("Create an arena, ", "", "c");
	}
}
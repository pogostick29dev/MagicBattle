package me.pogostick29dev.magicbattle;

import me.pogostick29dev.magicbattle.listeners.BlockBreak;
import me.pogostick29dev.magicbattle.listeners.PlayerDeath;
import me.pogostick29dev.magicbattle.listeners.PlayerInteract;
import me.pogostick29dev.magicbattle.listeners.PlayerLeave;
import me.pogostick29dev.magicbattle.listeners.PlayerLoseHunger;
import me.pogostick29dev.magicbattle.listeners.SignManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicBattle extends JavaPlugin {

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new SignManager(), this);
		
		ArenaManager.getInstance().setupArenas();
		
		CommandManager cm = new CommandManager();
		cm.setup();
		getCommand("magicbattle").setExecutor(cm);
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new PlayerInteract(), this);
		pm.registerEvents(new PlayerLeave(), this);
		pm.registerEvents(new PlayerLoseHunger(), this);
	}
	
	public static Plugin getPlugin() {
		return Bukkit.getServer().getPluginManager().getPlugin("MagicBattle");
	}
}
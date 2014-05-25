package me.pogostick29dev.magicbattle;

import me.pogostick29dev.magicbattle.listeners.BlockBreak;
import me.pogostick29dev.magicbattle.listeners.PlayerDeath;
import me.pogostick29dev.magicbattle.listeners.PlayerInteract;
import me.pogostick29dev.magicbattle.listeners.PlayerLeave;
import me.pogostick29dev.magicbattle.listeners.PlayerLoseHunger;
import me.pogostick29dev.magicbattle.listeners.SignManager;
import net.gravitydevelopment.updater.Updater;
import net.gravitydevelopment.updater.Updater.UpdateType;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicBattle extends JavaPlugin {

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new SignManager(), this);
		
		ArenaManager.getInstance().setupArenas();
		
		getCommand("magicbattle").setExecutor(new CommandManager());
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new PlayerInteract(), this);
		pm.registerEvents(new PlayerLeave(), this);
		pm.registerEvents(new PlayerLoseHunger(), this);
		
		Updater updater = new Updater(this, 80029, getFile(), UpdateType.DEFAULT, true);
	}
	
	public static Plugin getPlugin() {
		return Bukkit.getServer().getPluginManager().getPlugin("MagicBattle");
	}
}
package me.pogostick29dev.magicbattle;

import java.util.Arrays;

import me.pogostick29dev.magicbattle.MessageManager.MessageType;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum Wand {

	FIRE("Fire", ChatColor.RED, new WandRunnable() {
		public void run(PlayerInteractEvent e) {
			Fireball fb = e.getPlayer().launchProjectile(Fireball.class);
			fb.setIsIncendiary(false);
			fb.setYield(0F);
		}
	}),
	
	POISON("Poison", ChatColor.DARK_PURPLE, new WandRunnable() {
		public void run(PlayerInteractEvent e) {
			for (Entity en : e.getPlayer().getNearbyEntities(10, 10, 10)) {
				if (en instanceof Player) {
					((Player) en).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 5, 1));
					MessageManager.getInstance().msg((Player) en, MessageType.INFO, ChatColor.DARK_PURPLE + "You have been poisoned by " + e.getPlayer().getName() + "!");
					MessageManager.getInstance().msg(e.getPlayer(), MessageType.INFO, ChatColor.DARK_PURPLE + "You have poisoned " + ((Player) en).getName() + "!");
				}
			}
		}
	});
	
	private String name;
	private ChatColor color;
	private WandRunnable run;
	
	Wand(String name, ChatColor color, WandRunnable run) {
		this.name = name;
		this.color = color;
		this.run = run;
	}
	
	public String getName() {
		return name;
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public String getFullName() {
		return color + name;
	}
	
	public void run(PlayerInteractEvent e) {
		run.run(e);
	}
	
	public ItemStack createItemStack() {
		ItemStack i = new ItemStack(Material.STICK, 1);
		
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(getFullName());
		im.setLore(Arrays.asList("A magic wand!"));
		
		i.setItemMeta(im);
		
		return i;
	}
	
	public static Wand forName(String name) {
		for (Wand w : Wand.values()) {
			if (w.getName().equalsIgnoreCase(name)) return w;
		}
		
		return null;
	}
}

abstract class WandRunnable { public abstract void run(PlayerInteractEvent e); }
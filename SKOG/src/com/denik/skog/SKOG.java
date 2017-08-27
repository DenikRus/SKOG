package com.denik.skog;

import java.io.File;
import java.util.*;
import java.util.logging.*;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.*;

public class SKOG extends JavaPlugin implements CommandExecutor {
	
	public Logger log;
	
	public SKOG() {
		this.log = Logger.getLogger("Minecraft");
	}

	public void onEnable() {
		getServer().getPluginManager().registerEvents((Listener)new Handler(this), (Plugin)this);
		log.info("[SKOG] Plugin Enabled!");
		File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            log.info("Creating new config file...");
            getConfig().options().copyDefaults(true);
            this.getConfig().set("Worlds", this.addWorlds());
            saveDefaultConfig();     
		}      
	}
	public List<String> addWorlds() {
		List<String> worldL = new ArrayList<String>();
		for (World w : this.getServer().getWorlds()) {
			worldL.add(w.getName());
		}
		return worldL;
	}
	public void onDisable() {
		log.info("[SKOG] Plugin Disabled!");
	}
	 public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		 if (label.equalsIgnoreCase("skog")) {
			 if (args.length == 0) {
				 sender.sendMessage(ChatColor.BLACK + "[SKOG]"+ ChatColor.YELLOW + " /skog reload" + ChatColor.GREEN + "  Ïåðåçàãðóæàåò êîíôèã");
				 return true;
			 }
		 }
		 if (!sender.hasPermission("skog.reload")) {
			 sender.sendMessage(ChatColor.RED + "Ó âàñ íåò ïðàâ");
			 return true;
		 }
		 if (args[0].equalsIgnoreCase("reload")) {
			 Player p = null;
			 if (sender instanceof Player) {
				 p = (Player) sender;				 				 
		 this.reloadConfig();
		 sender.sendMessage(ChatColor.GREEN + "[SKOG] Конфиг перезагружен");
	     log.info("[SKOG] Config reloaded!");
	     return true;
			 }
		 }
		return true;
	 }
}
	 
	 

	 


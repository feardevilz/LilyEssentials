package com.bobacadodl.lilyessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bobacadodl.lilyessentials.LilyEssentials;

public class ShoutCommand implements CommandExecutor
{
	
	private LilyEssentials plugin;
	
	public ShoutCommand(LilyEssentials plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		
		if(!sender.hasPermission("lilyessentials.general.shout"))
		{
			return false;
		}

		if(args.length <= 0)
		{
			sender.sendMessage(plugin.prefix + ChatColor.RED + "Proper Usage: " + ChatColor.YELLOW + "/shout [message]");
			return true;
		}

		String message = plugin.wordsToString(0,args);

		// we're going to use display name here -feardevilz
		if(sender instanceof Player) 
		{ 
			plugin.request("lilyessentials.shout", ((Player)sender).getDisplayName() + "\0" 
					+ message + "\0" 
					+ plugin.getUsername());
		}
		else 
		{
			plugin.request("lilyessentials.shout", sender.getName() + "\0" 
					+ message + "\0" 
					+ plugin.getUsername());
		}

		return true;
	}
}

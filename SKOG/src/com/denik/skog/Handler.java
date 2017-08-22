package com.denik.skog;

import java.util.*;

import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.plugin.*;

public class Handler implements Listener{

	   private SKOG skog;
	    private final BlockFace[] faces;
	    
		public Handler(SKOG skog) {
			this.skog = skog;
	        this.faces = new BlockFace[] { BlockFace.SELF, BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
		}
		@EventHandler
	    public void onFromTo(BlockFromToEvent event) {
	        int id = event.getBlock().getTypeId();
	        if (id >= 8 && id <= 11) {
	            final Block b = event.getToBlock();
	            final int toid = b.getTypeId();
	            if (toid == 0 && this.generatesCobble(id, b)) {
	                List<String> worlds = (List<String>)this.skog.getConfig().getStringList("Worlds");
	                if (worlds.contains(event.getBlock().getLocation().getWorld().getName())) {
	                	 Random pick = new Random();
	                     int chance = 0;
	                     for (int counter = 1; counter <= 1; ++counter) {
	                         chance = 1 + pick.nextInt(100);
	                     }
	                    final double coal = this.skog.getConfig().getDouble("Chance.Coal");
	                    final double iron = this.skog.getConfig().getDouble("Chance.Iron");
	                    final double gold = this.skog.getConfig().getDouble("Chance.Gold");
	                    final double redstone = this.skog.getConfig().getDouble("Chance.Redstone");
	                    final double lapis = this.skog.getConfig().getDouble("Chance.Lapis");
	                    final double emerald = this.skog.getConfig().getDouble("Chance.Emerald");
	                    final double diamond = this.skog.getConfig().getDouble("Chance.Diamond");
	                    if (chance > 0 && chance <= coal) {
	                        b.setType(Material.COAL_ORE);
	                    }
	                    if (chance > coal && chance <= iron) {
	                        b.setType(Material.IRON_ORE);
	                    }
	                    if (chance > iron && chance <= gold) {
	                        b.setType(Material.GOLD_ORE);
	                    }
	                    if (chance > gold && chance <= redstone) {
	                        b.setType(Material.REDSTONE_ORE);
	                    }
	                    if (chance > redstone && chance <= lapis) {
	                        b.setType(Material.LAPIS_ORE);
	                    }
	                    if (chance > lapis && chance <= emerald) {
	                        b.setType(Material.EMERALD_ORE);
	                    }
	                    if (chance > emerald && chance <= diamond) {
	                        b.setType(Material.DIAMOND_ORE);
	                    }
	                    if (chance > diamond && chance <= 100) {
	                        b.setType(Material.COBBLESTONE);
	                    }
	                }
	            }
	        }
	    }
	    
	    public boolean generatesCobble(final int id, final Block b) {
	        final int mirrorID1 = (id == 8 || id == 9) ? 10 : 8;
	        final int mirrorID2 = (id == 8 || id == 9) ? 11 : 9;
	        BlockFace[] faces;
	        for (int length = (faces = this.faces).length, i = 0; i < length; ++i) {
	            final BlockFace face = faces[i];
	            final Block r = b.getRelative(face, 1);
	            if (r.getTypeId() == mirrorID1 || r.getTypeId() == mirrorID2) {
	                return true;
	            }
	        }
	        return false;
	    }
	}
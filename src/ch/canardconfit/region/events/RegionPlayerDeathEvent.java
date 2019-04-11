package ch.canardconfit.region.events;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import ch.canardconfit.region.tools.Region;

/**
 * Event when a player death in a region
 * 
 * @author CanardConfit
 * @version 1.0.0
 */
public class RegionPlayerDeathEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();

	/**
	 * player death in a region
	 */
	private Player player;
	
	/**
	 * region that the player death
	 */
	private Region region;

	/**
	 * Experience of the player
	 */
	private int exp;

	/**
	 * Drops of the player
	 */
	private List<ItemStack> drops;
	
	/**
	 * Constructor of event
	 * 
	 * @param region Region of event
	 * @param p Player of event
	 * @param i Experience of player
	 * @param list drops of player
	 */
	public RegionPlayerDeathEvent(Region region, Player p, int i, List<ItemStack> list) {
		this.player = p;
		this.region = region;
		this.exp = i;
		this.drops = list;
	}

	/**
	 * Get player
	 * 
	 * @return Player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Get region
	 * 
	 * @return Region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * Get Experience
	 * 
	 * @return Experience
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * Get Drops
	 * 
	 * @return Drops
	 */
	public List<ItemStack> getDrops() {
		return drops;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
}

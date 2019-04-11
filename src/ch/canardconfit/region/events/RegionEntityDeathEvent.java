package ch.canardconfit.region.events;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import ch.canardconfit.region.tools.Region;

/**
 * Event when a entity death in a region
 * 
 * @author CanardConfit
 * @version 1.0.0
 */
public class RegionEntityDeathEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();

	/**
	 * entity death in a region
	 */
	private Entity entity;

	/**
	 * region that the entity death in
	 */
	private Region region;
	/**
	 * Experience of the entity
	 */
	private int exp;
	/**
	 * Drops of the entity
	 */
	private List<ItemStack> drops;
	
	/**
	 * Constructor of event
	 * 
	 * @param region Region of event
	 * @param e Entity of event
	 * @param i Experience of entity
	 * @param list drops of entity
	 */
	public RegionEntityDeathEvent(Region region, Entity e, int i, List<ItemStack> list) {
		this.entity = e;
		this.region = region;
		this.exp = i;
		this.drops = list;
	}

	/**
	 * Get entity
	 * 
	 * @return Entity
	 */
	public Entity getEntity() {
		return entity;
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

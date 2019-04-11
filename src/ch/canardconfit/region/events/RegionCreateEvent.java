package ch.canardconfit.region.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import ch.canardconfit.region.tools.Region;

/**
 * Event when a region is create
 * 
 * @author CanardConfit
 * @version 1.0.0
 */
public class RegionCreateEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	/**
	 * plugin that creates the region
	 */
	private Plugin creator;
	/**
	 * Created region
	 */
	private Region region;

	/**
	 * Constructor of event
	 * 
	 * @param creator Plugin of event
	 * @param region Region of event
	 */
	public RegionCreateEvent(Plugin creator, Region region) {
		this.creator = creator;
		this.region = region;
	}

	/**
	 * Get plugin
	 * 
	 * @return Plugin
	 */
	public Plugin getCreator() {
		return creator;
	}
	
	/**
	 * Get Region Created
	 * 
	 * @return Region
	 */
	public Region getCreatedRegion() {
		return region;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
}

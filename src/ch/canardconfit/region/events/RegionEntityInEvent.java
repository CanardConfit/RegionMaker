package ch.canardconfit.region.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.sun.xml.internal.ws.api.Cancelable;

import ch.canardconfit.region.tools.Region;

/**
 * Event when a entity is in a region
 * 
 * @author CanardConfit
 * @version 1.0.0
 */
public class RegionEntityInEvent extends Event implements Cancelable {

	private static final HandlerList handlers = new HandlerList();

	/**
	 * entity is in a region
	 */
	private Entity entity;

	/**
	 * region that the entity is in
	 */
	private Region region;
	
	/**
	 * If the event is cancelled
	 */
	private boolean isCancelled;
	
	/**
	 * Constructor of event
	 * 
	 * @param region Region of event
	 * @param entity Entity of event
	 */
	public RegionEntityInEvent(Region region, Entity entity) {
		this.isCancelled = false;
		this.entity = entity;
		this.region = region;
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

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * if event is cancelled
	 * 
	 * @return isCancelled
	 */
	public boolean isCancelled() {
        return isCancelled;
    }

	/**
	 * Set event cancelation
	 * 
	 * @param cancelled boolean, true to cancel
	 */
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

	@Override
	public void cancel(boolean cancelled) {
        this.isCancelled = cancelled;
	}
}

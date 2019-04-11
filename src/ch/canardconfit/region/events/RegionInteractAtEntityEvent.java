package ch.canardconfit.region.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;

import com.sun.xml.internal.ws.api.Cancelable;

import ch.canardconfit.region.tools.Region;

/**
 * Event when a player interact with entity in a region
 * 
 * @author CanardConfit
 * @version 1.0.0
 */
public class RegionInteractAtEntityEvent extends Event implements Cancelable {

	private static final HandlerList handlers = new HandlerList();

	/**
	 * player interact in a region
	 */
	private Player player;
	
	/**
	 * region that the player interact
	 */
	private Region region;
	
	/**
	 * vector of clicked position
	 */
	private Vector clickedPosition;
	
	/**
	 * entity interacted
	 */
	private Entity entity;
	
	/**
	 * If the event is cancelled
	 */
	private boolean isCancelled;
	
	/**
	 * Constructor of event
	 * 
	 * @param reg Region of event
	 * @param player Player of event
	 * @param vector Vector of clicked position
	 * @param entity Entity of event
	 */
	public RegionInteractAtEntityEvent(Region reg, Player player, Vector vector, Entity entity) {
		this.isCancelled = false;
		this.region = reg;
		this.player = player;
		this.clickedPosition = vector;
		this.entity = entity;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * Get entity interacted
	 * 
	 * @return Entity
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * Get Vector of clicked position
	 * 
	 * @return Vector
	 */
	public Vector getClickedPosition() {
		return clickedPosition;
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

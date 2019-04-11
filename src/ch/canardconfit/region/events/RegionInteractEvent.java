package ch.canardconfit.region.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import com.sun.xml.internal.ws.api.Cancelable;

import ch.canardconfit.region.tools.Region;

/**
 * Event when a player interact with something in a region
 * 
 * @author CanardConfit
 * @version 1.0.0
 */
public class RegionInteractEvent extends Event implements Cancelable {

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
	 * Action of player
	 */
	private Action action;

	/**
	 * Block clicked
	 */
	private Block clickedBlock;

	/**
	 * item of player
	 */
	private ItemStack item;

	/**
	 * Face of block
	 */
	private BlockFace blockFace;

	/**
	 * Material of block
	 */
	private Material material;
	
	/**
	 * If the event is cancelled
	 */
	private boolean isCancelled;

	/**
	 * Constructor of event
	 * 
	 * @param reg Region of event
	 * @param action Action of Player
	 * @param clickedBlock Block clicked by Player
	 * @param player Player of event
	 * @param item iten of Player
	 * @param blockFace Face of block clicked
	 * @param material Material of Block
	 */
	public RegionInteractEvent(Region reg, Action action, Block clickedBlock, Player player, ItemStack item,
			BlockFace blockFace, Material material) {
		this.isCancelled = false;
		this.region = reg;
		this.action = action;
		this.clickedBlock = clickedBlock;
		this.player = player;
		this.item = item;
		this.blockFace = blockFace;
		this.material = material;
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
	 * Get material of block
	 * 
	 * @return Material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Get face of block
	 * 
	 * @return blockface
	 */
	public BlockFace getBlockFace() {
		return blockFace;
	}

	/**
	 * Get item of player
	 * 
	 * @return item
	 */
	public ItemStack getItem() {
		return item;
	}

	/**
	 * Get clicked block
	 * 
	 * @return Block
	 */
	public Block getClickedBlock() {
		return clickedBlock;
	}

	/**
	 * Get Action of player
	 * 
	 * @return Action
	 */
	public Action getAction() {
		return action;
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

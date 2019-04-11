package ch.canardconfit.region.manager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import ch.canardconfit.region.events.RegionCreateEvent;
import ch.canardconfit.region.events.RegionDeleteEvent;
import ch.canardconfit.region.tools.Region;

/**
 * <b>Class that manages all actions on the regions</b>
 * 
 * @author CanardConfit
 * @version 1.0.0
 */
public class RegionManager {
	
	/**
	 * List of regions
	 */
	private ArrayList<Region> regionList = new ArrayList<>();
	
	
	/**
	 * Register a new region
	 * 
	 * @param creator the plugin that created the region
	 * @param loc1 an x, y, z coordinate of one of the edges of the region
	 * @param loc2 an x, y, z coordinate of the opposite of loc1
	 * @param name the name of the region
	 * @param inVertical boolean to know if "y" is taken into account in the region
	 * 
	 * @see Region
	 * 
	 * @return a new instance of region
	 */
	public Region registerRegion(Plugin creator, Location loc1, Location loc2, String name, boolean inVertical) {
		Region region = new Region(loc1, loc2, name, inVertical);
		regionList.add(region);
		
		RegionCreateEvent event = new RegionCreateEvent(creator, region);
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		return region;
	}
	
	/**
	 * Unregister a region (delete)
	 * 
	 * @param destructor the plugin that deleted the region
	 * @param region the instance of the region has deleted
	 * 
	 * @see Region
	 * 
	 * @return true if the deletion has succeeded
	 */
	public boolean unregisterRegion(Plugin destructor, Region region) {
		RegionDeleteEvent event = new RegionDeleteEvent(destructor, region);
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		return regionList.remove(region);
	}
	
	/**
	 * Get region by name
	 * 
	 * @param name Name of the region you want to recover
	 * 
	 * @see Region
	 * 
	 * @return the instance of region if region name exists
	 */
	public Region getRegion(String name) {
		for (Region reg : regionList) {
			if (reg.getRegionName().equals(name)) {
				return reg;
			}
		}
		return null;
	}

	/**
	 * Get region by coordinate
	 * 
	 * @param loc coordinate in a region you want to recover
	 * 
	 * @see Region
	 * 
	 * @return the instance of region if coordinate is in a region
	 */
	public Region getRegion(Location loc) {
		for (Region r : regionList) {
			if (isInRegion(r, loc)) {
				return r;
			}
		}
		return null;
	}

	/**
	 * Get region by block
	 * 
	 * @param loc locaation of block in region
	 * 
	 * @see Region
	 * 
	 * @return instance of region if block is in region
	 */
	public Region getRegionBlock(Location loc) {
		for (Region r : regionList) {
			if (isInRegionBlock(r, loc)) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 * If coordinate is in a region
	 * 
	 * @param region region where we think the coordinate is
	 * @param loc coordinated to verify
	 * 
	 * @return true if the coordinate is in the region
	 */
	public boolean isInRegion(Region region, Location loc) {

		/*Get values x,y,z of loc*/
		double xp = loc.getX();
		double yp = loc.getY();
		double zp = loc.getZ();

		//Get coords of region
		Location[] regionLocs = region.getLocs();

		/*Get values x of region*/
		double x1 = regionLocs[0].getX();
		double x2 = regionLocs[1].getX();

		/*Get values z of region*/
		double z1 = regionLocs[0].getZ();
		double z2 = regionLocs[1].getZ();
		
		//Verify if coordinate is in region
		if (z1 < zp && z2+1 > zp && x2 < xp && x1+1 > xp
		 || z1+1 > zp && z2 < zp && x2 < xp && x1+1 > xp
		 || z1+1 > zp && z2 < zp && x2+1 > xp && x1 < xp
		 || z1 < zp && z2+1 > zp && x2+1 > xp && x1 < xp) {

			//if region takes into account "y"
			if (region.inVertical()) {

				/*Get values y of region*/
				double y1 = regionLocs[0].getY();
				double y2 = regionLocs[1].getY();
				
				//Verify if coordinate is in y region
				if (y1 < yp && y2 > yp
				 || y1 > yp && y2 < yp) {
					
					//return true if coordinate is in region
					return true;
				}
			}
		}
		
		//Return false if coordinate isn't in region
		return false;
	}
	
	/**
	 * If coordinate block is in a region
	 * 
	 * @param region region where we think the coordinate is
	 * @param loc coordinated of block to verify
	 * 
	 * @return true if the coordinate is in the region
	 */
	public boolean isInRegionBlock(Region region, Location loc) {

		/*Get values x,y,z of loc*/
		double xp = loc.getX();
		double yp = loc.getY();
		double zp = loc.getZ();

		//Get coords of region
		Location[] regionLocs = region.getLocs();

		/*Get values x of region*/
		double x1 = regionLocs[0].getX();
		double x2 = regionLocs[1].getX();

		/*Get values z of region*/
		double z1 = regionLocs[0].getZ();
		double z2 = regionLocs[1].getZ();

		//Verify if coordinate is in region
		if (z1 <= zp && z2 >= zp && x2 <= xp && x1 >= xp
		 || z1 >= zp && z2 <= zp && x2 <= xp && x1 >= xp
		 || z1 >= zp && z2 <= zp && x2 >= xp && x1 <= xp
		 || z1 <= zp && z2 >= zp && x2 >= xp && x1 <= xp) {

			//if region takes into account "y"
			if (region.inVertical()) {

				/*Get values y of region*/
				double y1 = regionLocs[0].getY();
				double y2 = regionLocs[1].getY();

				//Verify if coordinate is in y region
				if (y1 < yp && y2 > yp
				 || y1 > yp && y2 < yp) {
					
					//return true if coordinate is in region
					return true;
				}
			}
		}
		
		//Return false if coordinate isn't in region
		return false;
	}
}

<img src="http://javadocs.canardconfit.ch/RegionMaker/images/RegionMaker-logo.png" width="140" style="float:left; margin:0px 10px 0px 0px;"/><h1>RegionMaker</h1>
A spigot library that allows you to create, delete, retrieve regions and events related to the regions. It was created to make it easier for developers to create and manage regions on a minecraft spigot server.

**Installation on a server**
------------------------
The installation is very simple, like a normal spigot plugin it goes into the "plugins" folder of your spigot server. Once done, you can restart your server and the plugin is installed on your server !

**Use in another plugin**
-----------------------------------
You must first include this library in the "build path" of your project, once done, you can use the library!

## Create a region

``RegionManager.registerRegion(org.bukkit.plugin.Plugin creator, org.bukkit.Location loc1, org.bukkit.Location loc2, java.lang.String name, boolean inVertical)``

- **creator** corresponds to your main class (`public Main extends JavaPlugin`).
- **loc1** corresponds to any coordinate in a corner of the region.
- **loc2** is the opposite of loc1.
- **name** corresponds to the name of the region.
- **inVertical** corresponds to a boolean that indicates whether the region takes vertical coordinates into account.

**Example :**
```Java
//Create Region
Region region = RegionMaker.getInstance().getRegionManager().registerRegion(this, new Location(Bukkit.getWorld("world"), 0, 10, 0), new Location(Bukkit.getWorld("world"), 0, 0, 0), "exemple", false);
```
## Delete a region

``RegionManager.unregisterRegion(org.bukkit.plugin.Plugin destructor, Region region) ``

- **destructor** corresponds to your main class (`public Main extends JavaPlugin`).
- **region** corresponds to the instance of the region to be deleted.

**Example :**
```Java
//Create Region
Region region = RegionMaker.getInstance().getRegionManager().registerRegion(this, new Location(Bukkit.getWorld("world"), 0, 10, 0), new Location(Bukkit.getWorld("world"), 0, 0, 0), "exemple", false);

//Delete Region created
RegionMaker.getInstace().getRegionManager().unregisterRegion(this, region);
```
## Recover a region

1. ``getRegion(org.bukkit.Location loc)``
2. ``getRegion(java.lang.String name)``
3. ``getRegionBlock(org.bukkit.Location loc)``

- 1 : **loc** corresponds to the coordinates that are in a region.
- 2 : **name** corresponds to the name of the region to recover.
- 3 : **loc** corresponds to the coordinates of the block that is in a region.

**Example :**
```Java
//Create Region
RegionMaker.getInstance().getRegionManager().registerRegion(this, new Location(Bukkit.getWorld("world"), 0, 10, 0), new Location(Bukkit.getWorld("world"), 0, 0, 0), "exemple", false);

//Get region by the name
Region region = RegionMaker.getInstace().getRegionManager().getRegion("exemple");
```
## Event management

To recover an event in a region, nothing more simple, it is necessary to look for the event which you want recovered in this list:
- **RegionCreateEvent** : Event when creating a region.
- **RegionDeleteEvent** : Event when deleting a region.
- **RegionPlayerEnterEvent** : Event when a player enters a region.
- **RegionEntityEnterEvent** : Event when an entity enters a region.
- **RegionPlayerLeaveEvent** : Event when a player leaves a region.
- **RegionEntityLeaveEvent** : Event when an entity comes out of a region.
- **RegionPlayerInEvent** : Event when a player stays in a region.
- **RegionEntityInEvent** : Event when an entity stays in a region.
- **RegionBlockPlaceEvent** : Event when a block is placed in a region.
- **RegionBlockBreakEvent** : Event when we break something in a region.
- **RegionInteractEvent** : Event when we interact with something in a region.
- **RegionInteractAtEntityEvent** : Event when player interact with an entity in a region.
- **RegionPlayerChatEvent** : Event when a player send message in a region.
- **RegionPlayerDamageEvent** : Event when a player takes damage in a region.
- **RegionEntityDamageEvent** : Event when an entity takes damage in a region.
- **RegionPlayerDeathEvent** : Event when a player dies in a region.
- **RegionEntityDeathEvent** : Event when an entity dies in a region.

Then use this event as if it was an event spigot.

**Example :**
```java
@EventHandler
public void onRegionCreate(RegionCreateEvent event) {
	//run your code here
}
```

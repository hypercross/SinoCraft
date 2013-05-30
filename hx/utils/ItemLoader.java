package hx.utils;


import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
public class ItemLoader
{
    int itemID;
    private Item theItem;
    private String name;
    private HyperMod mod;
    //	//accessor
    //============================================================================

    public int id()
    {
        return itemID;
    }

    public Item item()
    {
        return theItem;
    }

    //constructor
    //============================================================================

    public ItemLoader(HyperMod mod, String name)
    {
        this.mod = mod;
        this.name = name;
    }

    //steps
    //============================================================================

    public void preInit(Configuration config)
    {
        itemID = config.getItem(name, mod.availableItemId()).getInt();
        if(HyperMod.USED_ID.contains(itemID))
        {
        	for(ItemLoader il : mod.itemLoaders.values())
        	{
        		if(il.itemID == itemID)
        		{
        			il.itemID = mod.availableItemId();
        			HyperMod.USED_ID.add(il.itemID);
        			break;
        		}
        	}
        }else
        	HyperMod.USED_ID.add(itemID);
        FMLLog.getLogger().log(Level.FINEST, "Using {0} for item {1}", new Object[]{itemID, name});
    }

    public void load()
    {
        try
        {
        	if(itemID <= 0)
        	{
        		FMLLog.getLogger().log(Level.FINE, "Item {0}disabled through config.", name);
        		return;
        	}
        	
        	String pathName = mod.getClass().getName();
        	pathName = pathName.substring(0,pathName.lastIndexOf("."));
            Class itemClass = Class.forName(pathName + "." + "Item" + name);
            theItem = (Item) itemClass.getConstructor(int.class).newInstance(itemID);
            if(theItem.getUnlocalizedName().equals("item.null"))
                theItem.setUnlocalizedName(name.toLowerCase());
            try
            {
                for(Field f : itemClass.getFields())
                    if(f.getAnnotation(Instance.class) != null)
                        f.set(theItem, theItem);
            }catch(SecurityException | IllegalArgumentException | IllegalAccessException e)
            {
                //never mind then
            }
            
            String dispName = "";

            for (String word : name.split("(?<!^)(?=[A-Z])"))
            {
                dispName += word + " ";
            }

            dispName = dispName.substring(0, dispName.length() - 1);
            LanguageRegistry.addName(theItem, dispName);
            FMLLog.getLogger().log(Level.FINEST, "Item {0} registered.", name);
        }
        catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            FMLLog.getLogger().log(Level.SEVERE, "Item {0} class NOT FOUND!", name);
        }
    }

	public void registerRenderer() {
		if(itemID <= 0)return;
		
		if(FMLCommonHandler.instance().getSide().isServer())return;
		String pathname = mod.getClass().getName();
    	pathname = pathname.substring(0,pathname.lastIndexOf("."));
		try{
			Class itemRender = Class.forName(pathname + "." + "Item" + name + "Renderer");
			MinecraftForgeClient.registerItemRenderer(this.item().itemID, (IItemRenderer) itemRender.newInstance());
		}catch(ClassNotFoundException | InstantiationException | IllegalAccessException e)
		{
			FMLLog.getLogger().log(Level.FINE, "item renderer skipped for {0}", name);
		}
	}
}

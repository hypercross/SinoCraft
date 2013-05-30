package sinocraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

public class SCCreativeTab extends CreativeTabs
{
	public SCCreativeTab()
	{
		super("sinocraft");
	}
	
	@SideOnly(Side.CLIENT)
        @Override
	public int getTabIconItemIndex()
	{
            return ItemReedLeaves.instance.itemID;
	}
}

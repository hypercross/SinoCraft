package SinoCraft;

import SinoCraft.Blocks.BlockPeony;
import SinoCraft.Proxy.ServerProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraft.block.Block;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;

@Mod(modid = "SinoCraft", name = "SinoCraft", version = "1.0.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class Start
{
	@Instance("SinoCraft")
	public static Start instance;
	
	@SidedProxy(clientSide = "SinoCraft.Proxy.ClientProxy", serverSide = "SinoCraft.Proxy.ServerProxy")
	public static ServerProxy proxy;
	
	@Init
	public void init(FMLInitializationEvent e)
	{
		proxy.Sino();
		
		int BlockPeonyId = 501;
		Block BlockPeony = new BlockPeony(BlockPeonyId);
		ModLoader.registerBlock(BlockPeony);
		ModLoader.addName(BlockPeony, "Äµµ¤");
	}
}

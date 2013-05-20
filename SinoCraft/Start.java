package SinoCraft;

import SinoCraft.proxy.ServerProxy;
import SinoCraft.blocks.BlockChrysanthemum;
import SinoCraft.blocks.BlockPeony;
import SinoCraft.blocks.BlockPrunusMume;
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
	
	@SidedProxy(clientSide = "SinoCraft.proxy.ClientProxy", serverSide = "SinoCraft.proxy.ServerProxy")
	public static ServerProxy proxy;
	
	@Init
	public void init(FMLInitializationEvent e)
	{
		proxy.Sino();
		
		int blockPeonyId = 501;
		Block blockPeony = new BlockPeony(blockPeonyId);
		ModLoader.registerBlock(blockPeony);
		ModLoader.addName(blockPeony, "Äµµ¤");
		
		int blockChrysanthemumId = 502;
		Block blockChrysanthemum = new BlockChrysanthemum(blockChrysanthemumId);
		ModLoader.registerBlock(blockChrysanthemum);
		ModLoader.addName(blockChrysanthemum, "¾Õ»¨");
		
		int BlockPrunusMumeId = 503;
		Block BlockPrunusMume = new BlockPrunusMume(BlockPrunusMumeId);
		ModLoader.registerBlock(BlockPrunusMume);
		ModLoader.addName(BlockPrunusMume, "Ã·»¨Ö¦");
	}
}

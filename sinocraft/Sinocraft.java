/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sinocraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import hx.utils.HyperMod;
/**
 *
 * @author Hyper
 */
@Mod(modid = "SinoCraft", name = "SinoCraft", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Sinocraft extends HyperMod{
    
    public Sinocraft()
    {
        BLOCK_BASE_ID = 3670;
        ITEM_BASE_ID  = 9740;
        
        this.addBlocks("Azalea", "Chrysanthemum", "Peony");
        this.addBlocks("PrunusMumeBranch", "Reed");
        this.addItems("ReedLeaves");
    }
    
    public static SCCreativeTab tab = new SCCreativeTab();
    
    @Instance("SinoCraft")
    public static Sinocraft instance;
    
    @Init
    @Override
    public void load(FMLInitializationEvent event)
    {
        super.load(event);
    }
    
    @PreInit
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
    }
}

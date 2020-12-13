package net.mcreator.beginning.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;

import net.mcreator.beginning.item.YellowstoneIngotItem;
import net.mcreator.beginning.item.BeginiumIngotItem;
import net.mcreator.beginning.block.YellowstoneBlock;
import net.mcreator.beginning.block.ScrapBlock;
import net.mcreator.beginning.block.ConvertismBlock;
import net.mcreator.beginning.block.BeginiumBlock;
import net.mcreator.beginning.BeginningModElements;

import java.util.Map;
import java.util.HashMap;

@BeginningModElements.ModElement.Tag
public class WhenBlockDroppedIntoConvertismProcedure extends BeginningModElements.ModElement {
	public WhenBlockDroppedIntoConvertismProcedure(BeginningModElements instance) {
		super(instance, 55);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure WhenBlockDroppedIntoConvertism!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure WhenBlockDroppedIntoConvertism!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure WhenBlockDroppedIntoConvertism!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure WhenBlockDroppedIntoConvertism!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure WhenBlockDroppedIntoConvertism!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((itemstack).getItem() == new ItemStack(BeginiumBlock.block, (int) (1)).getItem())
				&& (/* @BlockState */(world.getFluidState(new BlockPos((int) x, (int) y, (int) z)).getBlockState())
						.getBlock() == ConvertismBlock.block.getDefaultState().getBlock()))) {
			((itemstack)).shrink((int) 1);
			if (!world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, (y + 1), z, new ItemStack(BeginiumIngotItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((itemstack).getItem() == new ItemStack(YellowstoneBlock.block, (int) (1)).getItem())
				&& (/* @BlockState */(world.getFluidState(new BlockPos((int) x, (int) y, (int) z)).getBlockState())
						.getBlock() == ConvertismBlock.block.getDefaultState().getBlock()))) {
			((itemstack)).shrink((int) 1);
			if (!world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, (y + 1), z, new ItemStack(YellowstoneIngotItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((itemstack).getItem() == new ItemStack(ScrapBlock.block, (int) (1)).getItem())
				&& (/* @BlockState */(world.getFluidState(new BlockPos((int) x, (int) y, (int) z)).getBlockState())
						.getBlock() == ConvertismBlock.block.getDefaultState().getBlock()))) {
			((itemstack)).shrink((int) 1);
			if (!world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, (y + 1), z, new ItemStack(BeginiumIngotItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		}
	}

	@SubscribeEvent
	public void onGemDropped(ItemTossEvent event) {
		PlayerEntity entity = event.getPlayer();
		double i = entity.getPosX();
		double j = entity.getPosY();
		double k = entity.getPosZ();
		World world = entity.world;
		ItemStack itemstack = event.getEntityItem().getItem();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("itemstack", itemstack);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}

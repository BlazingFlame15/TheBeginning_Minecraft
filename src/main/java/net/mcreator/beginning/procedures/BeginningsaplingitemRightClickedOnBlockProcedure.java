package net.mcreator.beginning.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.beginning.item.BeginningsaplingitemItem;
import net.mcreator.beginning.block.BeginningsaplingBlock;
import net.mcreator.beginning.block.BeginningGrassBlock;
import net.mcreator.beginning.block.BeginningDirtBlock;
import net.mcreator.beginning.BeginningModElements;

import java.util.Map;

@BeginningModElements.ModElement.Tag
public class BeginningsaplingitemRightClickedOnBlockProcedure extends BeginningModElements.ModElement {
	public BeginningsaplingitemRightClickedOnBlockProcedure(BeginningModElements instance) {
		super(instance, 16);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure BeginningsaplingitemRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure BeginningsaplingitemRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure BeginningsaplingitemRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure BeginningsaplingitemRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure BeginningsaplingitemRightClickedOnBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean canSaplingBePlaced = false;
		if ((((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.CAVE_AIR.getDefaultState()
						.getBlock()))) {
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BeginningGrassBlock.block.getDefaultState()
					.getBlock())) {
				canSaplingBePlaced = (boolean) (true);
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.GRASS_BLOCK.getDefaultState()
					.getBlock())) {
				canSaplingBePlaced = (boolean) (true);
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.MYCELIUM.getDefaultState().getBlock())) {
				canSaplingBePlaced = (boolean) (true);
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.DIRT.getDefaultState().getBlock())) {
				canSaplingBePlaced = (boolean) (true);
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.PODZOL.getDefaultState().getBlock())) {
				canSaplingBePlaced = (boolean) (true);
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.COARSE_DIRT.getDefaultState()
					.getBlock())) {
				canSaplingBePlaced = (boolean) (true);
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BeginningDirtBlock.block.getDefaultState()
					.getBlock())) {
				canSaplingBePlaced = (boolean) (true);
			} else {
				canSaplingBePlaced = (boolean) (false);
			}
			if (((canSaplingBePlaced) == (true))) {
				if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(BeginningsaplingitemItem.block, (int) (1)).getItem())) {
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
					}
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), BeginningsaplingBlock.block.getDefaultState(), 3);
					if (entity instanceof PlayerEntity) {
						ItemStack _stktoremove = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
						((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
					}
					if (!world.getWorld().isRemote) {
						world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.place")),
								SoundCategory.NEUTRAL, (float) 1, (float) 0.8);
					} else {
						world.getWorld().playSound(x, y, z,
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.place")),
								SoundCategory.NEUTRAL, (float) 1, (float) 0.8, false);
					}
				}
			}
		}
	}
}

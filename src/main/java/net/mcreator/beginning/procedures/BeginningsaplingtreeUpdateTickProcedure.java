package net.mcreator.beginning.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.block.Blocks;

import net.mcreator.beginning.BeginningModElements;

import java.util.Map;

@BeginningModElements.ModElement.Tag
public class BeginningsaplingtreeUpdateTickProcedure extends BeginningModElements.ModElement {
	public BeginningsaplingtreeUpdateTickProcedure(BeginningModElements instance) {
		super(instance, 12);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure BeginningsaplingtreeUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure BeginningsaplingtreeUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure BeginningsaplingtreeUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure BeginningsaplingtreeUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double diamonds = 0;
		diamonds = (double) (Math.random() * 3);
		if (((diamonds) == 0)) {
			if ((Math.random() < 0.01)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("beginning", "beginning_tree_medium"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) x, (int) y, (int) z),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			}
		} else if (((diamonds) == 1)) {
			if ((Math.random() < 0.01)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("beginning", "beginning_tree_big"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) x, (int) y, (int) z),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			}
		} else if (((diamonds) == 2)) {
			if ((Math.random() < 0.01)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("beginning", "beginning_tree_small"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) x, (int) y, (int) z),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			}
		} else if (((diamonds) == 3)) {
			if ((Math.random() < 0.01)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("beginning", "beginning_tree_tall"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) x, (int) y, (int) z),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			}
		}
	}
}

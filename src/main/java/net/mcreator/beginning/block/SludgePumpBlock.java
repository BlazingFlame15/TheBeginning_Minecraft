
package net.mcreator.beginning.block;

import net.minecraft.block.material.Material;

@BeginningModElements.ModElement.Tag
public class SludgePumpBlock extends BeginningModElements.ModElement {

	@ObjectHolder("beginning:sludge_pump")
	public static final Block block = null;

	@ObjectHolder("beginning:sludge_pump")
	public static final TileEntityType<CustomTileEntity> tileEntityType = null;

	public SludgePumpBlock(BeginningModElements instance) {
		super(instance, 48);

		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(BeginningItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@SubscribeEvent
	public void registerTileEntity(RegistryEvent.Register<TileEntityType<?>> event) {
		event.getRegistry().register(TileEntityType.Builder.create(CustomTileEntity::new, block).build(null).setRegistryName("sludge_pump"));
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 10f).lightValue(0));

			setRegistryName("sludge_pump");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

		@Override
		public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand,
				BlockRayTraceResult hit) {
			super.onBlockActivated(state, world, pos, entity, hand, hit);

			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			if (entity instanceof ServerPlayerEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) entity, new INamedContainerProvider() {
					@Override
					public ITextComponent getDisplayName() {
						return new StringTextComponent("Sludge Pump");
					}

					@Override
					public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
						return new SludgepumpfuelGui.GuiContainerMod(id, inventory,
								new PacketBuffer(Unpooled.buffer()).writeBlockPos(new BlockPos(x, y, z)));
					}
				}, new BlockPos(x, y, z));
			}

			return ActionResultType.SUCCESS;
		}

		@Override
		public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			return tileEntity instanceof INamedContainerProvider ? (INamedContainerProvider) tileEntity : null;
		}

		@Override
		public boolean hasTileEntity(BlockState state) {
			return true;
		}

		@Override
		public TileEntity createTileEntity(BlockState state, IBlockReader world) {
			return new CustomTileEntity();
		}

		@Override
		public boolean eventReceived(BlockState state, World world, BlockPos pos, int eventID, int eventParam) {
			super.eventReceived(state, world, pos, eventID, eventParam);
			TileEntity tileentity = world.getTileEntity(pos);
			return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
		}

	}

	public static class CustomTileEntity extends LockableLootTileEntity implements ISidedInventory {

		private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);

		protected CustomTileEntity() {
			super(tileEntityType);
		}

		@Override
		public void read(CompoundNBT compound) {
			super.read(compound);

			if (!this.checkLootAndRead(compound)) {
				this.stacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
			}

			ItemStackHelper.loadAllItems(compound, this.stacks);

			if (compound.get("fluidTank") != null)
				CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.readNBT(fluidTank, null, compound.get("fluidTank"));
		}

		@Override
		public CompoundNBT write(CompoundNBT compound) {
			super.write(compound);

			if (!this.checkLootAndWrite(compound)) {
				ItemStackHelper.saveAllItems(compound, this.stacks);
			}

			compound.put("fluidTank", CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.writeNBT(fluidTank, null));

			return compound;
		}

		@Override
		public SUpdateTileEntityPacket getUpdatePacket() {
			return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
		}

		@Override
		public CompoundNBT getUpdateTag() {
			return this.write(new CompoundNBT());
		}

		@Override
		public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
			this.read(pkt.getNbtCompound());
		}

		@Override
		public int getSizeInventory() {
			return stacks.size();
		}

		@Override
		public boolean isEmpty() {
			for (ItemStack itemstack : this.stacks)
				if (!itemstack.isEmpty())
					return false;
			return true;
		}

		@Override
		public ITextComponent getDefaultName() {
			return new StringTextComponent("sludge_pump");
		}

		@Override
		public int getInventoryStackLimit() {
			return 64;
		}

		@Override
		public Container createMenu(int id, PlayerInventory player) {
			return new SludgepumpfuelGui.GuiContainerMod(id, player, new PacketBuffer(Unpooled.buffer()).writeBlockPos(this.getPos()));
		}

		@Override
		public ITextComponent getDisplayName() {
			return new StringTextComponent("Sludge Pump");
		}

		@Override
		protected NonNullList<ItemStack> getItems() {
			return this.stacks;
		}

		@Override
		protected void setItems(NonNullList<ItemStack> stacks) {
			this.stacks = stacks;
		}

		@Override
		public boolean isItemValidForSlot(int index, ItemStack stack) {
			return true;
		}

		@Override
		public int[] getSlotsForFace(Direction side) {
			return IntStream.range(0, this.getSizeInventory()).toArray();
		}

		@Override
		public boolean canInsertItem(int index, ItemStack stack, @Nullable Direction direction) {
			return this.isItemValidForSlot(index, stack);
		}

		@Override
		public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
			return true;
		}

		private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

		private final FluidTank fluidTank = new FluidTank(8000, fs -> {
			if (fs.getFluid() == SludgeBlock.flowing)
				return true;
			if (fs.getFluid() == SludgeBlock.still)
				return true;

			return false;
		}) {
			@Override
			protected void onContentsChanged() {
				super.onContentsChanged();
				markDirty();
				world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
			}
		};

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
			if (!this.removed && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				return handlers[facing.ordinal()].cast();

			if (!this.removed && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
				return LazyOptional.of(() -> fluidTank).cast();

			return super.getCapability(capability, facing);
		}

		@Override
		public void remove() {
			super.remove();
			for (LazyOptional<? extends IItemHandler> handler : handlers)
				handler.invalidate();
		}

	}

}

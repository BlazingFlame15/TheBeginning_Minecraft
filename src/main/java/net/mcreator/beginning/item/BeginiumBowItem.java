
package net.mcreator.beginning.item;

@BeginningModElements.ModElement.Tag
public class BeginiumBowItem extends BeginningModElements.ModElement {

	@ObjectHolder("beginning:beginium_bow")
	public static final Item block = null;

	@ObjectHolder("beginning:entitybulletbeginium_bow")
	public static final EntityType arrow = null;

	public BeginiumBowItem(BeginningModElements instance) {
		super(instance, 64);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemRanged());
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("entitybulletbeginium_bow").setRegistryName("entitybulletbeginium_bow"));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void init(FMLCommonSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(arrow, renderManager -> new CustomRender(renderManager));
	}

	public static class ItemRanged extends Item {

		public ItemRanged() {
			super(new Item.Properties().group(ItemGroup.COMBAT).maxDamage(100));

			setRegistryName("beginium_bow");
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			entity.setActiveHand(hand);
			return new ActionResult(ActionResultType.SUCCESS, entity.getHeldItem(hand));
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.BOW;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 72000;
		}

		@Override
		public void onPlayerStoppedUsing(ItemStack itemstack, World world, LivingEntity entityLiving, int timeLeft) {
			if (!world.isRemote && entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();
				if (true) {

					ArrowCustomEntity entityarrow = shoot(world, entity, random, 1f, 5, 5);

					itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));

					entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;

				}
			}
		}

	}

	@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
	public static class ArrowCustomEntity extends AbstractArrowEntity implements IRendersAsItem {

		public ArrowCustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			super(arrow, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, World world) {
			super(type, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, double x, double y, double z, World world) {
			super(type, x, y, z, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, LivingEntity entity, World world) {
			super(type, entity, world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack getItem() {
			return new ItemStack(BeginiumArrowItem.block, (int) (1));
		}

		@Override
		protected ItemStack getArrowStack() {
			return null;
		}

		@Override
		protected void arrowHit(LivingEntity entity) {
			super.arrowHit(entity);
			entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1);
		}

		@Override
		public void tick() {
			super.tick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			World world = this.world;
			Entity entity = this.getShooter();
			if (this.inGround) {
				this.remove();
			}
		}

	}

	public static class CustomRender extends EntityRenderer<ArrowCustomEntity> {
		private static final ResourceLocation texture = new ResourceLocation("beginning:textures/0000beginiumblue.png");

		public CustomRender(EntityRendererManager renderManager) {
			super(renderManager);
		}

		@Override
		public void render(ArrowCustomEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
				int packedLightIn) {
			IVertexBuilder vb = bufferIn.getBuffer(RenderType.getEntityCutout(this.getEntityTexture(entityIn)));
			matrixStackIn.push();
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90));
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90 + MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
			EntityModel model = new Modelbeginiumarrow();
			model.render(matrixStackIn, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
			matrixStackIn.pop();

			super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		}

		@Override
		public ResourceLocation getEntityTexture(ArrowCustomEntity entity) {
			return texture;
		}
	}

	// Made with Blockbench 3.7.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports

	public static class Modelbeginiumarrow extends EntityModel<Entity> {
		private final ModelRenderer feather;
		private final ModelRenderer tip;
		private final ModelRenderer bb_main;

		public Modelbeginiumarrow() {
			textureWidth = 16;
			textureHeight = 16;

			feather = new ModelRenderer(this);
			feather.setRotationPoint(0.0F, 23.0F, 0.0F);
			feather.setTextureOffset(12, 11).addBox(1.0F, -3.0F, 1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
			feather.setTextureOffset(12, 11).addBox(1.0F, -3.0F, -2.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
			feather.setTextureOffset(12, 11).addBox(-2.0F, -3.0F, -2.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
			feather.setTextureOffset(12, 11).addBox(-2.0F, -3.0F, 1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
			feather.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

			tip = new ModelRenderer(this);
			tip.setRotationPoint(1.0F, 12.0F, -1.0F);
			tip.setTextureOffset(0, 12).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			tip.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, 2.0F, 2.0F, -1.0F, 1.0F, 0.0F, false);
			tip.setTextureOffset(0, 14).addBox(1.0F, 0.0F, 0.0F, -1.0F, -1.0F, 2.0F, 0.0F, false);
			tip.setTextureOffset(0, 12).addBox(-3.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			tip.setTextureOffset(0, 13).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			tip.setTextureOffset(1, 13).addBox(-4.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			tip.setTextureOffset(0, 13).addBox(1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			tip.setTextureOffset(1, 14).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			tip.setTextureOffset(3, 13).addBox(-2.0F, 0.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.setTextureOffset(0, 0).addBox(-1.0F, -12.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);
		}

		@Override
		public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			// previously the render function, render code was moved to a method below
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			feather.render(matrixStack, buffer, packedLight, packedOverlay);
			tip.render(matrixStack, buffer, packedLight, packedOverlay);
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}

	public static ArrowCustomEntity shoot(World world, LivingEntity entity, Random random, float power, double damage, int knockback) {
		ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, world);
		entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setIsCritical(false);
		entityarrow.setDamage(damage);
		entityarrow.setKnockbackStrength(knockback);
		world.addEntity(entityarrow);

		double x = entity.getPosX();
		double y = entity.getPosY();
		double z = entity.getPosZ();
		world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")),
				SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));

		return entityarrow;
	}

	public static ArrowCustomEntity shoot(LivingEntity entity, LivingEntity target) {
		ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, entity.world);
		double d0 = target.getPosY() + (double) target.getEyeHeight() - 1.1;
		double d1 = target.getPosX() - entity.getPosX();
		double d3 = target.getPosZ() - entity.getPosZ();
		entityarrow.shoot(d1, d0 - entityarrow.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1f * 2, 12.0F);

		entityarrow.setSilent(true);
		entityarrow.setDamage(5);
		entityarrow.setKnockbackStrength(5);
		entityarrow.setIsCritical(false);
		entity.world.addEntity(entityarrow);

		double x = entity.getPosX();
		double y = entity.getPosY();
		double z = entity.getPosZ();
		entity.world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")),
				SoundCategory.PLAYERS, 1, 1f / (new Random().nextFloat() * 0.5f + 1));

		return entityarrow;
	}

}

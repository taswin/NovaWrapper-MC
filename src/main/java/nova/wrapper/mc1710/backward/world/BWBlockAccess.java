package nova.wrapper.mc1710.backward.world;

import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import nova.core.block.Block;
import nova.core.block.BlockAccess;
import nova.core.util.transform.Vector3i;
import nova.wrapper.mc1710.backward.block.BWBlock;
import nova.wrapper.mc1710.forward.block.FWBlock;

import java.util.Optional;

/**
 * @author Calclavia
 */
public class BWBlockAccess implements BlockAccess {
	public final net.minecraft.world.IBlockAccess access;

	public BWBlockAccess(IBlockAccess access) {
		this.access = access;
	}

	@Override
	public Optional<Block> getBlock(Vector3i position) {
		net.minecraft.block.Block mcBlock = access.getBlock(position.x, position.y, position.z);
		if (mcBlock == null || mcBlock == Blocks.air) {
			return Optional.empty();
		} else if (mcBlock instanceof FWBlock) {
			return Optional.of(((FWBlock) mcBlock).getBlockInstance(this, position));
		} else {
			return Optional.of(new BWBlock(mcBlock));
		}
	}

	@Override
	public boolean setBlock(Vector3i position, Block block) {
		return false;
	}

	@Override
	public boolean removeBlock(Vector3i position) {
		return false;
	}
}

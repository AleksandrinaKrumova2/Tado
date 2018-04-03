package com.tado.android.times;

import android.support.annotation.NonNull;
import com.tado.android.rest.model.Block;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Util;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class TimesBlockUtils {

    static class C12081 implements Comparator<Block> {
        C12081() {
        }

        public int compare(Block lhs, Block rhs) {
            return lhs.getStartSeconds() > rhs.getStartSeconds() ? 1 : -1;
        }
    }

    public static List<Block> updateModel(@NonNull List<Block> resultingBlockList, @NonNull List<Block> baseBlockList, @NonNull Block editBlock, @NonNull String mId) {
        Util.deepTimeBlockCopy(resultingBlockList, baseBlockList);
        boolean rightNeighbour = false;
        boolean leftNeighbour = false;
        int size = resultingBlockList.size();
        int i = 0;
        while (i < size) {
            Block block = (Block) resultingBlockList.get(i);
            int endBlock = block.getEndSeconds();
            int editEndBlock = editBlock.getEndSeconds();
            if (endBlock == 0) {
                endBlock = 86400;
            }
            if (editEndBlock == 0) {
                editEndBlock = 86400;
            }
            if (i + 1 < size && true && size > 1 && ((Block) resultingBlockList.get(i + 1)).getId().equals(mId)) {
                leftNeighbour = true;
            }
            if (block.getId().equals(mId)) {
                block.setStartSeconds(editBlock.getStartSeconds());
                block.setEndSeconds(editBlock.getEndSeconds());
                block.setInEditMode(true);
                block.setGeolocationOverride(editBlock.isGeolocationOverride());
                block.setSetting(editBlock.getSetting());
                rightNeighbour = true;
                leftNeighbour = false;
            } else {
                if (isBetween(editBlock.getStartSeconds(), editEndBlock, block.getStartSeconds()) && isBetween(editBlock.getStartSeconds(), editEndBlock, endBlock)) {
                    block.setDeleteCandidate(true);
                } else if ((endBlock > editBlock.getStartSeconds() && block.getStartSeconds() < editBlock.getStartSeconds()) || leftNeighbour) {
                    block.setEndSeconds(editBlock.getStartSeconds());
                } else if ((block.getStartSeconds() < editEndBlock && endBlock > editEndBlock) || rightNeighbour) {
                    block.setStartSeconds(editBlock.getEndSeconds());
                } else if (block.getStartSeconds() >= editBlock.getStartSeconds() || endBlock > editBlock.getStartSeconds()) {
                    if (block.getStartSeconds() >= editEndBlock) {
                    }
                }
                rightNeighbour = false;
            }
            i++;
        }
        checkForInvalidBlocks(resultingBlockList, mId);
        return findAndFillGaps(resultingBlockList, baseBlockList, mId);
    }

    public static List<Block> checkForInvalidBlocks(@NonNull List<Block> blockList, @NonNull String mId) {
        Iterator<Block> blockIterator = blockList.iterator();
        int i = 0;
        while (blockIterator.hasNext()) {
            Block block = (Block) blockIterator.next();
            int endSeconds = block.getEndSeconds();
            if (block.isDeleteCandidate()) {
                blockIterator.remove();
            }
            if (endSeconds == 0) {
                endSeconds = 86400;
            }
            if (endSeconds - block.getStartSeconds() < Constants.TIMES_BLOCK_MIN_LENGTH_IN_SECONDS) {
                if (i + 1 < blockList.size()) {
                    if (i == 0) {
                        ((Block) blockList.get(next(blockList, i))).setStartSeconds(block.getStartSeconds());
                    } else if (((Block) blockList.get(i + 1)).getId().equals(mId)) {
                        ((Block) blockList.get(i - 1)).setEndSeconds(block.getEndSeconds());
                    } else {
                        ((Block) blockList.get(next(blockList, i))).setStartSeconds(block.getStartSeconds());
                    }
                } else if (i == blockList.size() - 1) {
                    ((Block) blockList.get(previous(blockList, i))).setEndSeconds(block.getEndSeconds());
                }
                blockIterator.remove();
            }
            i++;
        }
        return blockList;
    }

    public static int next(@NonNull List<Block> list, int position) {
        int next = position;
        int i = position + 1;
        while (i < list.size()) {
            if (i < list.size() && !((Block) list.get(i)).isDeleteCandidate()) {
                return i;
            }
            i++;
        }
        return next;
    }

    public static int previous(@NonNull List<Block> list, int position) {
        int prev = position;
        for (int j = position - 1; j >= 0; j--) {
            if (!((Block) list.get(j)).isDeleteCandidate()) {
                return j;
            }
        }
        return prev;
    }

    private static boolean isBetween(int start, int end, int middle) {
        return middle >= start && middle <= end;
    }

    public static void sortBlockList(@NonNull List<Block> blocks) {
        Collections.sort(blocks, new C12081());
    }

    public static List<Block> findAndFillGaps(@NonNull List<Block> blocks, @NonNull List<Block> baseBlocks, @NonNull String editId) {
        int size = blocks.size();
        int lastPosition = size - 1;
        Block newBlockToFillTheGap = null;
        sortBlockList(blocks);
        int position = 0;
        while (position < size) {
            int nextPosition = position + 1;
            if (position == 0 && ((Block) blocks.get(position)).getStartSeconds() != 0) {
                ((Block) blocks.get(position)).setStartSeconds(0);
            } else if (position == lastPosition && ((Block) blocks.get(position)).getEndSeconds() != 86400) {
                newBlockToFillTheGap = createBlock(true, baseBlocks, editId);
                newBlockToFillTheGap.setStartSeconds(((Block) blocks.get(position)).getEndSeconds());
                newBlockToFillTheGap.setEndSeconds(86400);
            } else if (!(position == lastPosition || ((Block) blocks.get(position)).getEndSeconds() == ((Block) blocks.get(nextPosition)).getStartSeconds())) {
                newBlockToFillTheGap = createBlock(false, baseBlocks, editId);
                newBlockToFillTheGap.setStartSeconds(((Block) blocks.get(position)).getEndSeconds());
                newBlockToFillTheGap.setEndSeconds(((Block) blocks.get(nextPosition)).getStartSeconds());
            }
            position++;
        }
        if (newBlockToFillTheGap != null) {
            blocks.add(newBlockToFillTheGap);
        }
        return blocks;
    }

    private static Block createBlock(boolean isLastBlock, List<Block> blocks, String editId) {
        Block block = new Block();
        Block blockToBeSplitted = new Block();
        for (Block b : blocks) {
            if (!isLastBlock || b.getEndSeconds() != 86400 || b.getId().equalsIgnoreCase(editId)) {
                if (!isLastBlock && b.getStartSeconds() == 0 && !b.getId().equalsIgnoreCase(editId)) {
                    blockToBeSplitted = b;
                    break;
                }
            } else {
                blockToBeSplitted = b;
                break;
            }
        }
        block.setGeolocationOverride(blockToBeSplitted.isGeolocationOverride());
        block.setDayType(blockToBeSplitted.getDayType());
        block.setDeleteCandidate(false);
        block.setWeekdaysType(blockToBeSplitted.getWeekdaysType());
        block.setSetting(blockToBeSplitted.getSetting());
        block.setInEditMode(false);
        block.setId(String.format("%d", new Object[]{Integer.valueOf(blocks.size() + 1)}));
        return block;
    }
}

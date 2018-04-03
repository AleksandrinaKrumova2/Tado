package com.tado.android.times.view.interfaces;

import com.tado.android.times.view.BlockView;

public interface ITimeBlocksView {
    void addBlock(BlockView blockView);

    void clear();

    boolean isAddModeEnabled();

    boolean isZoomedIn();

    void setAddMode(boolean z);

    BlockView split(BlockView blockView);
}

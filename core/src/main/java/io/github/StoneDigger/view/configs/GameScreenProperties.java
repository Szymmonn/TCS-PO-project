package io.github.StoneDigger.view.configs;

public class GameScreenProperties {
    public final float blockSize;
    public final int blocksInViewWidth;
    public final int blocksInViewHeight;
    public final float hudSize;
    public final String backgroundColor; // black

    public GameScreenProperties(
        float blockSize,
        int blocksInViewWidth,
        int blocksInViewHeight,
        float hudSize,
        String backgroundColor
    ) {
        this.blockSize = blockSize;
        this.blocksInViewWidth = blocksInViewWidth;
        this.blocksInViewHeight = blocksInViewHeight;
        this.hudSize = hudSize;
        this.backgroundColor = backgroundColor;
    }
}

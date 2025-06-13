package io.github.StoneDigger.view.configs;

public class LoserScreenProperties {
    public final float worldWidth;
    public final float worldHeight;
    public final int lostFontSize;
    public final String lostFontBorderColor;
    public final String lostFontColor;
    public final float lostLabelWidth;
    public final float lostLabelHeight;
    public final float lostLabelPositionX;
    public final float lostLabelPositionY;
    public final int backFontSize;
    public final String backFontBorderColor;
    public final String backFontColor;
    public final float backLabelWidth;
    public final float backLabelHeight;
    public final float backLabelPositionX;
    public final float backLabelPositionY;


    public LoserScreenProperties(float worldWidth,
                                 float worldHeight,
                                 int lostFontSize,
                                 String lostFontBorderColor,
                                 String lostFontColor,
                                 float lostLabelWidth,
                                 float lostLabelHeight,
                                 float lostLabelPositionX,
                                 float lostLabelPositionY,
                                 int backFontSize,
                                 String backFontBorderColor,
                                 String backFontColor,
                                 float backLabelWidth,
                                 float backLabelHeight,
                                 float backLabelPositionX,
                                 float backLabelPositionY) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.lostFontSize = lostFontSize;
        this.lostFontBorderColor = lostFontBorderColor;
        this.lostFontColor = lostFontColor;
        this.lostLabelWidth = lostLabelWidth;
        this.lostLabelHeight = lostLabelHeight;
        this.lostLabelPositionX = lostLabelPositionX;
        this.lostLabelPositionY = lostLabelPositionY;
        this.backFontSize = backFontSize;
        this.backFontBorderColor = backFontBorderColor;
        this.backFontColor = backFontColor;
        this.backLabelWidth = backLabelWidth;
        this.backLabelHeight = backLabelHeight;
        this.backLabelPositionX = backLabelPositionX;
        this.backLabelPositionY = backLabelPositionY;
    }
}

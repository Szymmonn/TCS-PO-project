package io.github.StoneDigger.view.configs;

public class HudViewProperties {
    public final String hudBgColor;
    public final int fontSize;
    public final String fontBorderColor;
    public final String fontColor;
    public final float fontScaleX;
    public final float fontScaleY;

    public final float settingsButtonPositionXOffset;
    public final float settingsButtonWidth;
    public final float settingsButtonHeight;

    public final float diamondTablePad;
    public final float diamondImageSize;
    public final float diamondCollectedLabelSize;

    public final float hpTableWidthOffset;
    public final float hpImageSize;
    public final float hpImagePad;

    public final float levelLabelPositionXMultiplier;
    public final float levelLabelPositionYPad;
    public final float leveLabelWidth;
    public final float levelLabelHeight;

    public final float timeLabelPositionXMultiplier;
    public final float timeLabelPad;
    public final float timeLabelWidth;
    public final float timeLabelHeight;

    public final float labelTopPad;

    HudViewProperties(
        String hudBgColor,
        int fontSize,
        String fontBorderColor,
        String fontColor,
        float fontScaleX,
        float fontScaleY,
        float settingsButtonPositionXOffset,
        float settingsButtonWidth,
        float settingsButtonHeight,
        float diamondTablePad,
        float diamondImageSize,
        float diamondCollectedLabelSize,
        float hpTableWidthOffset,
        float hpImageSize,
        float hpImagePad,
        float levelLabelPositionXMultiplier,
        float levelLabelPositionYPad,
        float leveLabelWidth,
        float levelLabelHeight,
        float timeLabelPositionXMultiplier,
        float timeLabelPad,
        float timeLabelWidth,
        float timeLabelHeight,
        float labelTopPad
    ) {
        this.hudBgColor = hudBgColor;
        this.fontSize = fontSize;
        this.fontBorderColor = fontBorderColor;
        this.fontColor = fontColor;
        this.fontScaleX = fontScaleX;
        this.fontScaleY = fontScaleY;
        this.settingsButtonPositionXOffset = settingsButtonPositionXOffset;
        this.settingsButtonWidth = settingsButtonWidth;
        this.settingsButtonHeight = settingsButtonHeight;
        this.diamondTablePad = diamondTablePad;
        this.diamondImageSize = diamondImageSize;
        this.diamondCollectedLabelSize = diamondCollectedLabelSize;
        this.hpTableWidthOffset = hpTableWidthOffset;
        this.hpImageSize = hpImageSize;
        this.hpImagePad = hpImagePad;
        this.levelLabelPositionXMultiplier = levelLabelPositionXMultiplier;
        this.levelLabelPositionYPad = levelLabelPositionYPad;
        this.leveLabelWidth = leveLabelWidth;
        this.levelLabelHeight = levelLabelHeight;
        this.timeLabelPositionXMultiplier = timeLabelPositionXMultiplier;
        this.timeLabelPad = timeLabelPad;
        this.timeLabelWidth = timeLabelWidth;
        this.timeLabelHeight = timeLabelHeight;
        this.labelTopPad = labelTopPad;
    }
}

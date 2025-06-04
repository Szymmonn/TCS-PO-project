package io.github.StoneDigger.view.configs;


// older language setup // does not support records :(
public class MenuScreenProperties {
    public final float worldWidth;
    public final float worldHeight;

    public final float fontSize;
    public final String fontColor; // white
    public final String fontBorderColor; // black
    public final float fontBorderWidth;
    public final String fontShadowColor; // #00000066
    public final float fontShadowOffsetX;
    public final float fontShadowOffsetY;
    public final String fontCharacterOptimization;

    public final String buttonStyleBackgroundColor; // ##00008B
    public final String buttonStyleFontColor1; // #B22222FF
    public final String buttonStyleFontColor2; // #808080FF

    public final float buttonWidth;
    public final float buttonHeight;

    public final float tableTitleStandardBottomPad;
    public final float tableStandardRandomBottomPad;
    public final float tableRandomEnterBottomPad;

    public final String backgroundColor; // #76D8FFFF

    public MenuScreenProperties(
        float worldWidth,
        float worldHeight,
        float fontSize,
        String fontColor,
        String fontBorderColor,
        float fontBorderWidth,
        String fontShadowColor,
        float fontShadowOffsetX,
        float fontShadowOffsetY,
        String fontCharacterOptimization,
        String buttonStyleBackgroundColor,
        String buttonStyleFontColor1,
        String buttonStyleFontColor2,
        float buttonWidth,
        float buttonHeight,
        float tableTitleStandardBottomPad,
        float tableStandardRandomBottomPad,
        float tableRandomEnterBottomPad,
        String backgroundColor
        ) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.fontBorderColor = fontBorderColor;
        this.fontBorderWidth = fontBorderWidth;
        this.fontShadowColor = fontShadowColor;
        this.fontShadowOffsetX = fontShadowOffsetX;
        this.fontShadowOffsetY = fontShadowOffsetY;
        this.fontCharacterOptimization = fontCharacterOptimization;
        this.buttonStyleBackgroundColor = buttonStyleBackgroundColor;
        this.buttonStyleFontColor1 = buttonStyleFontColor1;
        this.buttonStyleFontColor2 = buttonStyleFontColor2;
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;
        this.tableTitleStandardBottomPad = tableTitleStandardBottomPad;
        this.tableStandardRandomBottomPad = tableStandardRandomBottomPad;
        this.tableRandomEnterBottomPad = tableRandomEnterBottomPad;
        this.backgroundColor = backgroundColor;
    }
}

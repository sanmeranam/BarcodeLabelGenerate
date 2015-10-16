package com.san;

/**
 *
 * @author M1028704
 */
public class GenerateConfig {

    public float scale = 1.0f;
    public BarcodeType.TYPES type = BarcodeType.TYPES.Code128;
    public String barcodeText = "";
    public boolean isAddText = true;
    public int fontSize = 10;
    public int barHeight = 2;
    public int barWidth = 2;

    public boolean isBorder;
    public int borderWidth = 1;

    public String aboveText = "";
    public String aboveTextFont = "Arial";
    public boolean isAboveText;
    public int aboveTextFontSize = 10;

    public String belowLeftText = "";
    public String belowLeftTextFont = "Arial";
    public boolean isBelowLeftText;
    public int belowLeftTextFontSize = 10;

    public String belowRightText = "";
    public String belowRightTextFont = "Arial";
    public boolean isBelowRightText;
    public int belowRightTextFontSize = 10;

    @Override
    public String toString() {
        return "GenerateConfig{" + "type=" + type + ", barcodeText=" + barcodeText + ", isAddText=" + isAddText + ", fontSize=" + fontSize + ", isBorder=" + isBorder + ", borderWidth=" + borderWidth + ", aboveText=" + aboveText + ", isAboveText=" + isAboveText + ", aboveTextFontSize=" + aboveTextFontSize + ", belowLeftText=" + belowLeftText + ", isBelowLeftText=" + isBelowLeftText + ", belowLeftTextFontSize=" + belowLeftTextFontSize + ", belowRightText=" + belowRightText + ", isBelowRightText=" + isBelowRightText + ", belowRightTextFontSize=" + belowRightTextFontSize + '}';
    }

}

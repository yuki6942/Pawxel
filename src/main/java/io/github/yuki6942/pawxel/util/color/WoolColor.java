package io.github.yuki6942.pawxel.util.color;


import lombok.Getter;

@Getter
public enum WoolColor {
    WHITE(""),
    ORANGE("Orange "),
    MAGENTA("Magenta "),
    LIGHT_BLUE("Light Blue "),
    YELLOW("Yellow "),
    LIME("Lime "),
    PINK("Pink "),
    GRAY("Gray "),
    LIGHT_GRAY("Light Gray "),
    CYAN("Cyan "),
    PURPLE("Purple "),
    BLUE("Blue "),
    BROWN("Brown "),
    GREEN("Green "),
    RED("Red "),
    BLACK("Black ");

    private final String displayName;

    WoolColor(String displayName) {
        this.displayName = displayName;
    }

    public static WoolColor fromMeta(int meta) {
        return values()[~meta & 15];
    }
}

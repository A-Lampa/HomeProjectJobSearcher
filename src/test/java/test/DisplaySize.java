package test;

import org.openqa.selenium.Dimension;

public enum DisplaySize {
    VGA(640, 480),
    SVGA(800, 600),
    XGA(1024, 768),
    WXGA_H(1280, 720),
    WXGA(1280, 800),
    WSXGA(1440, 900),
    WSXGAplus(1680, 1050),
    FHD(1920, 1080),
    WUXGA(1920, 1200);

    private final int width;
    private final int height;

    DisplaySize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Dimension getDimension(){
        return new Dimension(width, height);
    }
}

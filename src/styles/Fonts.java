package styles;

import java.awt.Font;

public class Fonts
{
    
    public static void loadFonts()
    {
        gfx.Fonts.addFont("BUTTON", new Font("Segoe Print", Font.BOLD, 28));
        gfx.Fonts.addFont("STANDARD", new Font("Times New Roman", Font.PLAIN, 22));
        gfx.Fonts.addFont("TITLE_LOADING", new Font("Trebuchet MS", Font.PLAIN, 32));
    }
    
}
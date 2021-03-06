package app;

import states.StateIntro;
import styles.Colours;
import styles.Fonts;

public class Launch
{
    
    public static void main(String[] args)
    {
        String name = "Fruitdroid";
        String author = "Jamie Purchase";
        String version = "0.1.0";
        String path = "C:/Users/Jamie/Documents/NetBeansProjects/Games/Fruitdroid/src/res/";
        Colours.loadColours();
        Fonts.loadFonts();
        new Engine(name, author, version, path, new StateIntro()).start(false);
    }
    
}
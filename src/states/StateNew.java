package states;

import app.Display;
import app.Engine;
import blocks.app.ProgressBar;
import debug.ObjectData;
import gfx.Colour;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StateNew extends State
{
    //
    
    public StateNew()
    {
        //
    }

    public void inputKeyPress(String key)
    {
        //
    }

    public void inputKeyRelease(String key)
    {
        //
    }

    public void inputKeyType(String key)
    {
        Engine.setState(new StateGame());
    }

    public void inputMouseClickL(MouseEvent e)
    {
        Engine.setState(new StateGame());
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderTitle(g);
        this.renderOptions(g);
    }
    
    private void renderOptions(Graphics g)
    {
        // TEMP
    }
    
    private void renderTitle(Graphics g)
    {
        // Background
        g.setColor(Colour.getColour("BLACK"));
        g.fillRect(0, 0, 1366, 768);
        
        // Logo
        g.drawImage(Drawing.getImage("logo/title2.png"), 333, 140, null);
    }

    public void tick()
    {
        //
    }
    
}
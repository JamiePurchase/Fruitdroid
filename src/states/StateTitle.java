package states;

import app.Display;
import app.Engine;
import blocks.Block;
import blocks.BlockGroup;
import debug.ObjectData;
import gfx.Colour;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import blocks.button.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StateTitle extends State
{
    private BlockGroup optionGroup;
    
    public StateTitle()
    {
        this.optionGroup = new BlockGroup("OPTIONS");
        this.optionGroup.addBlock("B1", new Button("B1", 683, 340, "NEW GAME", "BUTTON", "GREEN_1", "GREEN_3", "GREEN_2", "GREEN_3"));
        this.optionGroup.addBlock("B2", new Button("B2", 683, 420, "EXIT GAME", "BUTTON", "GREEN_1", "GREEN_3", "GREEN_2", "GREEN_3"));
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
        //
    }

    public void inputMouseClickL(MouseEvent e)
    {
        String activate = this.optionGroup.inputClick(e);
        if(activate != null) {this.optionClick(activate);}
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
    
    private void optionClick(String ref)
    {
        if(ref.equals("B1"))
        {
            Engine.setState(new StateNew());
        }
    }
    
    public void render(Graphics g)
    {
        this.renderTitle(g);
        this.renderOptions(g);
    }
    
    private void renderOptions(Graphics g)
    {
        this.optionGroup.render(g);
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
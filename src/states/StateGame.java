package states;

import app.Display;
import app.Engine;
import blocks.app.ProgressBar;
import debug.ObjectData;
import game.Fruit;
import game.Grid;
import gfx.Colour;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import maths.Maths;
import styles.Colours;

public class StateGame extends State
{
    private Grid fruitGrid;
    private int clockNow;
    private int scoreNow;
    
    public StateGame()
    {
        this.fruitGrid = new Grid();
        this.clockNow = 0;
        this.scoreNow = 0;
    }
    
    private String getScoreAsString()
    {
        return "SCORE: " + this.scoreNow;
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
        this.fruitGrid.inputClick(e);
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
        this.renderBackground(g);
        this.renderGame(g);
        this.renderScore(g);
    }
    
    private void renderBackground(Graphics g)
    {
        g.setColor(Colour.getColour("BLACK"));
        g.fillRect(0, 0, 1366, 768);
    }
    
    private void renderGame(Graphics g)
    {
        this.fruitGrid.render(g);
    }
    
    private void renderScore(Graphics g)
    {
        g.setColor(Colour.getColour("GREEN_1"));
        g.setFont(Fonts.getFont("STANDARD"));
        g.drawString(this.getScoreAsString(), 1200, 25);
    }
    
    public void tick()
    {
        //
    }

    private void tickClock()
    {
        this.clockNow ++;
    }
    
}
package game;

import app.Engine;
import gfx.Colour;
import gfx.Drawing;
import gfx.Fonts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Fruit
{
    private Grid grid;
    private GridNode node;
    private FruitType type;
    private boolean glow;
    private boolean connect;
    
    // TEMP
    private boolean activation;
    
    public Fruit(Grid grid, GridNode node, FruitType type)
    {
        this.grid = grid;
        this.node = node;
        this.type = type;
        this.glow = false;
        this.connect = false;
    }
    
    public void activate()
    {
        this.type = FruitType.NULL;
    }
    
    public void connect()
    {
        // Add this fruit to the connect array
        this.grid.connectAdd(this);
        
        // Mark this fruit as connect
        this.connect = true;
        
        // Search for adjacent fruit of the same type
        this.connectAdjacent();
    }
    
    public void connectAdjacent()
    {
        System.out.println("Searching adjacent fruit at (" + this.getPosX() + "," + this.getPosY() + ")...");
        for(int x = 0; x < this.getAdjacent().size(); x++)
        {
            if(this.getAdjacent().get(x).isType(this.getType()) && this.getAdjacent().get(x).isConnected() == false)
            {
                System.out.println("Fruut at (" + this.getAdjacent().get(x).getPosX() + "," + this.getAdjacent().get(x).getPosY() + ") is type " + this.getAdjacent().get(x).getType().toString());
                this.grid.connectAdd(this.getAdjacent().get(x));
                this.getAdjacent().get(x).connect();
            }
        }
    }
    
    public ArrayList<Fruit> getAdjacent()
    {
        ArrayList<Fruit> adjacent = new ArrayList();
        if(this.getPosX() > 0) {adjacent.add(this.grid.getFruitAt(this.getPosX() - 1, this.getPosY()));}
        if(this.getPosX() < this.grid.getCountX()) {adjacent.add(this.grid.getFruitAt(this.getPosX() + 1, this.getPosY()));}
        if(this.getPosY() > 0) {adjacent.add(this.grid.getFruitAt(this.getPosX(), this.getPosY() - 1));}
        if(this.getPosY() < this.grid.getCountY()) {adjacent.add(this.grid.getFruitAt(this.getPosX(), this.getPosY() + 1));}
        return adjacent;
    }
    
    private int getDrawX()
    {
        return (this.node.getPosX() * 100) + this.grid.getOffsetX();
    }
    
    private int getDrawY()
    {
        return (this.node.getPosY() * 100) + this.grid.getOffsetY();
    }
    
    private BufferedImage getImage()
    {
        return Engine.getClipboard().getImage(type.toString());
    }
    
    private int getPosX()
    {
        return this.node.getPosX();
    }
    
    private int getPosY()
    {
        return this.node.getPosY();
    }
    
    public FruitType getType()
    {
        return this.type;
    }
    
    public boolean isConnected()
    {
        return this.connect;
    }
    
    public boolean isHover()
    {
        return this.node.getHover(Engine.getMousePoint());
    }
    
    public boolean isType(FruitType check)
    {
        if(this.type.toString().equals(check.toString())) {return true;}
        return false;
    }
    
    public void render(Graphics g, boolean cursor, boolean select)
    {
        // If cursor is enabled
        if(cursor)
        {
            // Selected fruit
            if(select) {this.renderSelect(g);}

            // Hovering cursor
            else if(this.isHover()) {this.renderHover(g);}
        }
        this.renderFruit(g);
        
        // TEMP
        if(this.isConnected()) {this.renderEdge(g, "RED");}
        
        // TEMP
        if(this.activation)
        {
            g.setColor(Color.WHITE);
            g.setFont(Fonts.getFont("STANDARD"));
            g.drawString("ACTIVATED", this.getDrawX(), this.getDrawY());
        }
    }
    
    private void renderEdge(Graphics g, String color)
    {
        g.setColor(Colour.getColour(color));
        g.fillRect((this.getPosX() * 100) + this.grid.getOffsetX(), (this.getPosY() * 100) + this.grid.getOffsetY(), 20, 2);
        g.fillRect((this.getPosX() * 100) + this.grid.getOffsetX(), (this.getPosY() * 100) + this.grid.getOffsetY(), 2, 20);
        g.fillRect((this.getPosX() * 100) + 80 + this.grid.getOffsetX(), (this.getPosY() * 100) + this.grid.getOffsetY(), 20, 2);
        g.fillRect((this.getPosX() * 100) + 99 + this.grid.getOffsetX(), (this.getPosY() * 100) + this.grid.getOffsetY(), 2, 20);
        g.fillRect((this.getPosX() * 100) + this.grid.getOffsetX(), (this.getPosY() * 100) + 99 + this.grid.getOffsetY(), 20, 2);
        g.fillRect((this.getPosX() * 100) + this.grid.getOffsetX(), (this.getPosY() * 100) + 80 + this.grid.getOffsetY(), 2, 20);
        g.fillRect((this.getPosX() * 100) + 80 + this.grid.getOffsetX(), (this.getPosY() * 100) + 99 + this.grid.getOffsetY(), 20, 2);
        g.fillRect((this.getPosX() * 100) + 99 + this.grid.getOffsetX(), (this.getPosY() * 100) + 80 + this.grid.getOffsetY(), 2, 20);
    }
    
    public void renderFruit(Graphics g)
    {
        if(!this.type.toString().equals("NULL")) {g.drawImage(this.getImage(), this.getDrawX(), this.getDrawY(), null);}
    }
    
    public void renderHover(Graphics g)
    {
        g.drawImage(Drawing.getImage("fruit/temp.png"), this.getDrawX() - 100, this.getDrawY() - 100, null);
        this.renderEdge(g, "GREEN_1");
    }
    
    public void renderSelect(Graphics g)
    {
        this.renderEdge(g, "GREEN_3");
    }
    
    public void setConnected(boolean value)
    {
        this.connect = value;
    }
    
    public void setType(FruitType type)
    {
        this.type = type;
    }
    
    public void swap(GridNode node)
    {
        FruitType newType = this.grid.getFruitAt(node).getType();
        this.grid.getFruitAt(node).setType(this.getType());
        this.setType(newType);
    }
    
}
package game;

import java.awt.Point;
import java.awt.Rectangle;

public class GridNode
{
    private Grid grid;
    private int posX, posY;
    
    public GridNode(Grid grid, int posX, int posY)
    {
        this.grid = grid;
        this.posX = posX;
        this.posY = posY;
    }
    
    public boolean getHover(Point point)
    {
        return new Rectangle((this.posX * 100) + this.grid.getOffsetX(), (this.posY * 100) + this.grid.getOffsetY(), 100, 100).contains(point);
    }
    
    public int getPosX()
    {
        return this.posX;
    }
    
    public int getPosY()
    {
        return this.posY;
    }
    
    public boolean isAdjacent(int x, int y)
    {
        if(x >= this.posX - 1 && x <= this.posX + 1 && y >= this.posY - 1 && y <= this.posY + 1) {return true;}
        return false;
    }
    
    public boolean isAt(int x, int y)
    {
        if(this.posX == x && this.posY == y) {return true;}
        return false;
    }
    
}
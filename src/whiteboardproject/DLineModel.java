
package whiteboardproject;

import java.awt.*;
        
public class DLineModel extends DShapeModel
{
    private Point p1;
    private Point p2;
    
    public DLineModel()
    {
        super();
        p1 = new Point(bounds.x, bounds.y);
        p2 = new Point(bounds.x + bounds.width, bounds.y + bounds.height);
    } //End DLineModel constructor
    
    public void modifyWithPoints(Point anchorPoint, Point movingPoint)
    {
        p1 = new Point(anchorPoint);
        p2 = new Point(movingPoint);
        super.modifyWithPoints(anchorPoint, movingPoint);
    }
    
    @Override
    public void move(int dx, int dy)
    {
        p1.x += dx;
        p1.y += dy;
        
        p2.x += dx;
        p2.y += dy;
        
        super.move(dx, dy);
        
    } //End move
    
    @Override
    public void mimic(DShapeModel model)
    {
        //Cast mimic line object
        DLineModel mimic = (DLineModel) model;
        
        setPoint1(mimic.getPoint1());
        setPoint2(mimic.getPoint2());
        super.mimic(model);
    } //End mimic
   
    public Point getPoint1()
    {
        return p1;
    }
    
    public Point getPoint2()
    {
        return p2;
    } 
    
    public void setPoint1(Point p)
    {
        p1 = new Point(p);
    }
    
    public void setPoint2(Point p)
    {
        p2 = new Point(p);
    }
} //End DLineModel class

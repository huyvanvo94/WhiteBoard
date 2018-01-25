package whiteboardproject;


import java.awt.*;
import java.util.*;

public class DLine extends DShape
{
    public DLine(DShapeModel model, Canvas canvas)
    {
        super(model, canvas);
    } //End DLine constructor
    
    @Override
    public void draw(Graphics g, boolean selected)
    {
        DLineModel line = getModel();
        g.setColor(getColor());
        g.drawLine(line.getPoint1().x, line.getPoint1().y, line.getPoint2().x, line.getPoint2().y);
        if(selected)
        {
            drawKnobs(g);
        }
    } //End draw
    
    @Override
    public DLineModel getModel()
    {
        return (DLineModel) model;
    } //End getModel
    
    public ArrayList<Point> getKnobs()
    {
        if(knobs == null || needsRecomputeKnobs)
        {
            knobs = new ArrayList<Point>();
            DLineModel line = (DLineModel) model;
            knobs.add(new Point(line.getPoint1()));
            knobs.add(new Point(line.getPoint2()));
        }
        
        return knobs;
    } //End getKnobs
}

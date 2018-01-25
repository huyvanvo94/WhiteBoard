package whiteboardproject;


import java.awt.*;

public class DRect extends DShape 
{
    public DRect(DShapeModel model, Canvas canvas) 
    {
        super(model, canvas);
    }
    
    public DRectModel getModel() 
    {
        return (DRectModel) model;
    }
    
    public void draw(Graphics g, boolean selected) 
    {
        g.setColor(model.getColor());
        Rectangle bounds = model.getBounds();
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
     
        //If shape is selected, draw the knobs for it
        if(selected) 
        {
            drawKnobs(g);
        }
    }

} //End DRect
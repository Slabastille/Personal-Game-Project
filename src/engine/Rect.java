package engine;

import java.awt.Color;
import java.awt.Graphics;

public class Rect
{
	public int x;
	public int y;
	
	public int w;
	public int h;

	public boolean physicsEnabled;
	
	public double vx = 0;
	public double vy = 0;
	
	double ay = G;
	
	static double G = .7;
	static double F = .6;
	
	boolean held = false;
	
	public void physicsOff()
	{
		vx = 0;
		vy = 0;
		
		ay = 0;
	}
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;

		this.physicsEnabled = false;

	}
	
	public void setVelocity(double vx, double vy)
	{
		this.vx = vx;
		this.vy = vy;
	}
	
	public void grabbed()
	{
		held = true;
	}
	
	public void dropped()
	{
		held = false;
	}
	
	public void goLT(int vx)
	{
		this.vx = -vx;		
	}
	
	public void goRT(int vx)
	{
		
		this.vx = +vx;		
	}
	
	public void goUP(int vy)
	{
		this.vy = -vy;
	}
	
	public void goDN(int vy)
	{
		this.vy = +vy;
	}
	
	public void jump(int h)
	{
		vy = -h;		
	}
	
	public void move()
	{
		if(physicsEnabled){
			x += vx;
			y += vy + ay/2;
			
			vy += ay;
		}
		
	}
	
	public void moveBy(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	
	
	public void resizeBy(int dw, int dh)
	{
		w += dw;
		
		h += dh;
	}
	
	public void chase(Rect r, int dx)
	{
		if(isLeftOf(r))   goRT(dx); 
		if(isRightOf(r))  goLT(dx); 
		if(isAbove(r))    goDN(dx); 
		if(isBelow(r))    goUP(dx);
		
		move();
	}
	
	public void evade(Rect r, int dx)
	{
		if(isLeftOf(r))   goLT(dx); 
		if(isRightOf(r))  goRT(dx); 
		if(isAbove(r))    goUP(dx); 
		if(isBelow(r))    goDN(dx); 
		
		move();
	}
	
	public boolean isLeftOf(Rect r)
	{
		return x + w < r.x;
	}
	
	public boolean isRightOf(Rect r)
	{
		return r.x + r.w < x;
	}
	
	public boolean isAbove(Rect r)
	{
		return y + h < r.y;
	}
	
	public boolean isBelow(Rect r)
	{
		return r.y + r.h < y;
	}
	
	
	
	public boolean contains(int mx, int my)
	{
		return (mx >= x    )  &&
			   (mx <= x + w)  &&
			   (my >= y    )  &&
			   (my <= y + h);
	}
	
	
	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x      ) &&				
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&			   
			   (y     <= r.y + r.h);
	}

	// public void bounceOff(Rect r)
	// {
	// 	if(cameFromLeftOf(r) || cameFromRightOf(r))  vx = -vx;
	// 	if(cameFromAbove(r)  || cameFromBelow(r))    vy = -vy;
	// }

	// public void pushedOutOf(Rect r)
	// {
	// 	 if(cameFromLeftOf(r))   pushbackLeftFrom(r);		
	// 	else if(cameFromRightOf(r))	pushbackRightFrom(r);
	// 	else if(cameFromAbove(r)) 	pushbackUpFrom(r);
	// 	else if(cameFromBelow(r))    pushbackDownFrom(r);
		
	// 	vx *= F;
		
	// 	if(Math.abs(vx) <= 1)  vx = 0;
	// }
	
	
	
	
	// public boolean cameFromLeftOf(Rect r)
	// {
	// 	return x - vx + w < r.x;
	// }
	
	// public boolean cameFromRightOf(Rect r)
	// {
	// 	return r.x + r.w < x - vx;
	// }
	
	public boolean cameFromAbove(Rect r)
	{
		return y - vy + h < r.y;
	}
	
	// public boolean cameFromBelow(Rect r)
	// {
	// 	return r.y + r.h < y - vy;
	// }
	
	// public void pushbackLeftFrom(Rect r)
	// {
	// 	x = r.x - w - 1 ;
	// }
	
	// public void pushbackRightFrom(Rect r)
	// {
	// 	x = r.x + r.w + 1;
	// }
	
	// public void pushbackUpFrom(Rect r)
	// {
	// 	y = r.y - h - 1;
		
	// 	vy = 0;
	// }
	
	// public void pushbackDownFrom(Rect r)
	// {
	// 	y = r.y + r.h + 1;
	// }

	public void pushedOutOf(Rect r) {
    // 1) Calculate total overlap along X
    double overlapX = Math.min(x + w, r.x + r.w) - Math.max(x, r.x);
    // 2) Calculate total overlap along Y
    double overlapY = Math.min(y + h, r.y + r.h) - Math.max(y, r.y);
    
    // If there is no overlap, do nothing
    if (overlapX <= 0 || overlapY <= 0) return;
    
    // Decide which axis to push out on based on the smaller overlap
    if (overlapX < overlapY) {
        // Collided horizontally
        if (x < r.x) {
            // We are on the left side -> push left
            x -= overlapX;
        } else {
            // We are on the right side -> push right
            x += overlapX;
        }
        // Damp horizontal velocity
        vx *= F;
        if (Math.abs(vx) <= 1) vx = 0;
    } else {
        // Collided vertically
        if (y < r.y) {
            // We are above -> push up
            y -= overlapY;
            // Zero out vertical velocity if landing on top
            vy = 0;
        } else {
            // We are below -> push down
            y += overlapY;
        }
    }
}

	
	
	public void draw(Graphics pen)
	{
		pen.setColor(Color.YELLOW);
		pen.drawRect(x , y  , w, h); 
		pen.fillRect(x , y  , w, h);
		// pen.setColor(Color.MAGENTA);
		// pen.drawRect(x - Camera.x , y - Camera.y  , w, h);
		// pen.fillRect(x - Camera.x , y - Camera.y  , w, h);
		// System.out.println("RECT DIMENSIONS: x " + (x) + " y: " + (y));


	}
	
	
	public String toString()
	{
		return "new Rect(" + x + ", " + y + ", " + w + ", " + h + "),";
	}

}

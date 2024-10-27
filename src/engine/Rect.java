package engine;

import java.awt.Graphics;

public class Rect
{
	public int x;
	public int y;
	
	int w;
	int h;
	
	int old_x;
	int old_y;

	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;

		this.old_x = x;
		this.old_y = y;
	}
	public void moveLT(int dx){

		old_x = x;
		x -= dx;
	}
	public void moveRT(int dx){
		old_x = x;
		x += dx;
	}
	public void moveUP(int dy){
		old_y = y;
		y -= dy;
	}
	public void moveDN(int dy){
		old_y = y;
		y += dy;
	}

	public void moveBy(int dx, int dy)
	{
		old_x = x;
		old_y = y;
		x += dx;
		y += dy;
	}
	
	public void resizeBy(int dw, int dh)
	{
		w += dw;
		h += dh;
	}
	
	
	public void draw(Graphics pen)
	{
		pen.drawRect(x, y, w, h);
	}

	
	//Collision detection and resolution
	public void pushOutOf(Rect r){
		if(cameFromAbove(r)) pushbackUpFrom(r);
		if(cameFromBelow(r)) pushbackDownFrom(r);
		if(cameFromLeftOf(r)) pushBackLeftFrom(r);
		if(cameFromRightOf(r)) pushBackRightFrom(r);
	}


	//Collision detection
	public boolean overlaps (Rect r){
		return (x + w >= r.x      ) &&
			   (x 	  <= r.x + r.w) &&
			   (y + h >= r.y      ) &&
			   (y 	  <= r.y + r.h);
	}

	public boolean cameFromLeftOf(Rect r){
		return old_x + w < r.x;
	}
	public boolean cameFromRightOf(Rect r){
		return old_x > r.x + r.w;
	}
	public boolean cameFromAbove(Rect r){
		return old_y + h < r.y;
	}
	public boolean cameFromBelow(Rect r){
		return old_y > r.y + r.h;
	}

	//Collosion resolution
	public void pushBackLeftFrom(Rect r){
		x = r.x - w - 1;
	}
	public void pushBackRightFrom(Rect r){
		x = r.x + r.w + 1;
	}
	public void pushbackUpFrom(Rect r){
		y = r.y - h - 1;
	}
	public void pushbackDownFrom(Rect r){
		y = r.y + r.h + 1;
	}


	//Chasing a target
	public void chase(Rect r){
		if(isLeftOf(r))  moveRT(1);
		if(isRightOf(r)) moveLT(1);
		if(isAbove(r))   moveDN(1);
		if(isBelow(r))   moveUP(1);
	}
	//Checking direction for Chasing
	public boolean isLeftOf(Rect r){
		return x + w < r.x;
	}
	public boolean isRightOf(Rect r){
		return x > r.x + r.w;
	}
	public boolean isAbove(Rect r){
		return y + h < r.y;
	}
	public boolean isBelow(Rect r){
		return y > r.y + r.h;
	}
}

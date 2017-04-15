from graphics import *
from time import sleep

def main():
    win = GraphWin("Draw Circles", 400, 400)
    win.setBackground("orange")
 
    circ = Circle(Point(200, 200), 40)
    circ.setFill('yellow')
    circ.setOutline('black')
    circ.draw(win)

    text = Text(Point(200, 380), "Click mouse on new location for circle")
    text.draw(win)
    
    for i in range(3):
        click = win.getMouse()
        center = circ.getCenter()
        dx = click.getX() - center.getX()
        dy = click.getY() - center.getY()
        for i in range (100):
            circ.move(dx/100, dy/100)
            sleep(.001)
main()

from time import sleep
from graphics import *

def main():

    win = GraphWin("SNOWMAN!!!!", 500, 500)
    win.setBackground("black")

    head = Circle(Point(250, 200), 50)
    head.setFill("blue")
    head.draw(win)

    torso = Circle(Point(250, 300), 50)
    torso.setFill("blue")
    torso.draw(win)

    ass = Circle(Point(250, 400), 50)
    ass.setFill("blue")
    ass.draw(win)

    for i in range(3):
        click = win.getMouse()
        centerHead = head.getCenter()
        dx = click.getX() - centerHead.getX()
        dy = click.getY() - centerHead.getY()
        for i in range (100):
            head.move(dx/100, dy/100)
            torso.move(dx/100, dy/100)
            ass.move(dx/100, dy/100)
            sleep(.01)       
main()

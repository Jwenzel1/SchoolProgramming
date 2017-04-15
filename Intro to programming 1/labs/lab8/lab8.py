from graphics import *

def main():
    end = False
    win = GraphWin("My Paint",600,600)
    win.setBackground("white")
    while(end == False):

        fill = Entry(Point(200, 500), 10)
        outline = Entry(Point(400, 500), 10)
        fill.draw(win)
        outline.draw(win)
        clickCenter = win.getMouse()
        clickRadius = win.getMouse()

        centerX = clickCenter.getX()
        centerY = clickCenter.getY()

        radiusX = clickRadius.getX()
        radiusY = clickRadius.getY()
        radiusLength = (((radiusX-centerX)**2)-((radiusY-centerY)**2))**(.5)
        if centerX == radiusX:
            end = True
        else:
            fillText = fill.getText()
            outlineText = outline.getText()
            circ = Circle(Point(centerX, centerY), radiusLength)
            circ.setFill(fillText.strip())
            circ.setOutline(outlineText.strip())
            circ.draw(win)
    
main()

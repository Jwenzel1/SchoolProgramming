class Stack:
    
    #Initializing the stack
    def __init__(self): 
        self.theStack =[]
 
    #Displays the value on top of the stack or tells the user its empty
    def top(self): 
        if self.isEmpty(): 
            return "Empty Stack" 
        else: 
            return self.theStack[-1] 

    #checks if the stack is empty. Returns a boolean of true if it is or false
    #if it still has something in it
    def isEmpty(self): 
        return len(self.theStack) == 0 

    #Adds and item to the top of the stack pushing all the other items back
    def push(self, item): 
        self.theStack.append(item)  

    #Removes or "pops" the top item off of the stack. If there is nothing
    #to remove the function returns a prompt telling the user the stack is 
    #empty
    def pop(self): 
        if not self.isEmpty(): 
            temp = self.theStack[- 1] 
            del(self.theStack[- 1]) 
            return temp 
        else: 
            return "Empty Stack"

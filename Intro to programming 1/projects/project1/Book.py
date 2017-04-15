class Book:

    #Initialization
    def __init__(self, title, quantity, price):
        self.title = str(title)
        self.quantity = int(quantity)
        self.price = float(price)

    #Getters
    def getTitle(self): return self.title
    def getQuantity(self): return self.quantity
    def getPrice(self): return self.price

    #Setters
    def setTitle(self, newTitle): self.title = newTitle
    def setQuantity(self, newQuantity): self.quantity = newQuantity
    def setPrice(self, newPrice): self.price = newPrice

    #Other
    def displayInfo(self):
        print "       Title: %s, Qty: %d, Price: $%.2f"%(self.title, \
                                                  self.quantity, self.price) 

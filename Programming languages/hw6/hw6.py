things = ["bigburger", "fishjoe", "chessypiza"]
print things
things[0] = things[0].capitalize()
things[2] = things[2].capitalize()
print things
things[1] = things[1].upper()
print things
food = {'red-meat':{'beef': {}, 'lamb': ['baked', 'bar-b-qued', 'fried'], 'bison':{}}, 'poultry':{}, 'other':{}}
print food.keys()
print food['red-meat'].keys()
print food['red-meat']['lamb']

FILE_LEN = 26
LAB_POINTS_POSSIBLE = 30.00
E = 60
D = 70
C = 80
B = 90
def findLabScore(rawLabScore):
    return rawLabScore * 100 / LAB_POINTS_POSSIBLE

def findCourseScore(courseDataList):
    raw = courseDataList[-1]*courseDataList[-2]
    lab = findLabScore(raw)
    weight = 0
    score = 0
    courseScore = 0

    for element in courseDataList:
       position = courseDataList.index(element)
       if position%2 == 0:
           weight = element
       else:
           score = element
       if weight == courseDataList[-2]:
           weight = 0
       if weight != 0 and score != 0:
           courseScore += weight * score
           weight = 0
           score = 0
           
    return courseScore + lab

def findLetterGrade(courseScore):
    if courseScore < E:
        return "E"
    if courseScore < D:
        return "D"
    if courseScore < C:
        return "C"
    if courseScore < B:
        return "B"
    else:
        return "A"

def main():
    
    courseDataList = []

    name = raw_input()

    for i in range(FILE_LEN):
        courseDataList.append(input())
    
    courseScore = findCourseScore(courseDataList)

    letterGrade = findLetterGrade(courseScore)

    print "%s: %.2f - %c" % (name, courseScore, letterGrade)

main()

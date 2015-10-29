def costFcn (start, end):
    return start[0]*end[0] + start[1]*end[1];

# Define P_px->py to be the minimum cost to get from start to end
# Define positions to be p1, p2, p3, p4, end
# P_p1->p2 is the minimum cost of P_p1->p2 + Pp3->end
# base case: P_p?->end is min(all eight possibilities)

def compute(p, end, cost):
    if p[0] == end[0] and p[1] == end[1]:
        return cost + costFcn(p, end);
    elif 

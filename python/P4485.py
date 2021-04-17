import heapq

dr = [1, 0, -1 ,0]
dc = [0, 1, 0, -1]

def solution(N, cave):
    costs = [[10000 for j in range(N)] for i in range(N)]

    pq = [(cave[0][0], (0, 0))]
    while len(pq) > 0:
        cur = heapq.heappop(pq)
        cur_cost = cur[0]
        cur_pos = cur[1]

        for i in range(4):
            nr = cur_pos[0] + dr[i]
            nc = cur_pos[1] + dc[i]
            if nr not in range(N) or nc not in range(N):
                continue

            new_cost = cur_cost + cave[nr][nc]
            if new_cost < costs[nr][nc]:
                costs[nr][nc] = new_cost
                
                if nr == N - 1 and nc == N - 1:
                    return new_cost
                else:
                    heapq.heappush(pq, (new_cost, (nr, nc)))
    raise RuntimeError()

p_num = 1
while True:
    N = int(input())
    if N == 0:
        break

    cave = []
    for i in range(N):
        cave.append(list(map(int, input().split())))
    
    answer = solution(N, cave)

    print(f"Problem {p_num}: {answer}")
    p_num += 1
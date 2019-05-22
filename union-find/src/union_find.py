
# Just for reference
class BaseUnionFind:
    def __init__(self, N):
        self.N = N
        self.id = list(range(N))

    def union(self, p: int , q: int) -> None:
        pass

    def connected(self, p: int, q: int) -> bool:
        pass


class QuickFind(BaseUnionFind):
    def __init__(self, N):
        return super().__init__(N)

    def union(self, p, q):
        # Change all entries equal to id[p] to id[q]
        p_tmp = self.id[p]
        q_tmp = self.id[q]
        for i in range(self.N):
            if self.id[i] == p_tmp:
                self.id[i] = q_tmp

    def connected(self, p, q):
        # Check that entries are equal
        return self.id[p] == self.id[q]


class QuickUnion(BaseUnionFind):
    def __init__(self, N):
        return super().__init__(N)

    def union(self, p, q):
        # Set root of p to point to root of q
        self.id[self.root(p)] = self.root(q)

    def root(self, p):
        while self.id[p] != p:
            p = self.id[p]
        return p

    def connected(self, p, q):
        return self.root(p) == self.root(q)


class OptimizedUnionFind(BaseUnionFind):
    # Implements path compression and weighted union
    def __init__(self, N):
        return super().__init__(N)

    def union(self, p, q):
        pass
        
    def connected(self, p, q):
        pass

    def root(self, p):
        pass
    
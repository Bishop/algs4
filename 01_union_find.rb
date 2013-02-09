class UF
    def initialize(capacity)
        @id = (0...capacity).to_a
    end

    def to_s
        @id.map(&:to_s).join(' ')
    end
end

class QuickFind < UF
    def connected?(p, q)
        @id[p] == @id[q]
    end

    def union(p, q)
        idp = @id[p]
        idq = @id[q]
        @id.map! { |id| id == idp ? idq : id }
    end
end

class QuickUnion < UF
    def initialize(capacity)
        super
        @sz = [1] * capacity
    end

    def connected?(p, q)
        root(p) == root(q)
    end

    def union(p, q)
        i, j = root(p), root(q)
        if @sz[i] < @sz[j]
            @id[i] = j; @sz[j] += @sz[i]
        else
            @id[j] = i; @sz[i] += @sz[j]
        end
    end

    def to_s
        super + " (#{@sz.map(&:to_s).join(' ')})"
    end

    private

    def root(i)
        while i != @id[i]
            @id[i] = @id[@id[i]]
            i = @id[i]
        end
        i
    end
end

size = 10
algs = [QuickFind, QuickUnion]

for alg in algs
    puts alg.name

    qf = alg.new size
    puts qf.to_s

    qf.union(1, 3);
    qf.union(5, 9);
    qf.union(8, 3);

    puts qf.to_s
    puts qf.connected?(1, 8).inspect
end
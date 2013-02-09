class QuickFind
    def initialize(capacity)
        @id = (0...capacity).to_a
    end

    def connected?(p, q)
        @id[p] == @id[q]
    end

    def union(p, q)
        idp = @id[p]
        idq = @id[q]
        @id.map! { |id| id == idp ? idq : id }
    end

    def to_s
        @id.map(&:to_s).join(' ')
    end
end

qf = QuickFind.new 10
puts qf.to_s

qf.union(1, 3);
qf.union(5, 9);
qf.union(8, 3);

puts qf.to_s
puts qf.connected?(1, 8).inspect
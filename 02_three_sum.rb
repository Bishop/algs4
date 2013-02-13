require 'binary_search/native'

class ThreeSum
    def self.brute_force_count(ints)
        count = 0

        for i in ints.dup
            for j in ints.dup
                for k in ints.dup
                    count += 1 if i + j + k == 0
                end
            end
        end
        count
    end

    def self.ruby_count(ints)
        ints.product(ints, ints).map { |t| t.reduce(&:+) }.count { |t| t == 0 }
    end

    def self.deluxe(ints)
        ints.sort!
        count = 0

        for i in ints.dup
            for j in ints.dup
                count += 1 if ints.binary_index -(i + j)
            end
        end
        count
    end
end

ints = File.read(ARGV[0]).split(/\s+/).map(&:to_i)
puts ThreeSum.ruby_count(ints) rescue puts 'Not run'
#puts ThreeSum.brute_force_count(ints)
puts ThreeSum.deluxe(ints)
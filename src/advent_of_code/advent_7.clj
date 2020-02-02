(ns advent_of_code.advent-7
  (:require clojure.pprint))

;(def input "1002,4,3,4,33")
;(def input "3,225,1,225,6,6,1100,1,238,225,104,0,1002,148,28,224,1001,224,-672,224,4,224,1002,223,8,223,101,3,224,224,1,224,223,223,1102,8,21,225,1102,13,10,225,1102,21,10,225,1102,6,14,225,1102,94,17,225,1,40,173,224,1001,224,-90,224,4,224,102,8,223,223,1001,224,4,224,1,224,223,223,2,35,44,224,101,-80,224,224,4,224,102,8,223,223,101,6,224,224,1,223,224,223,1101,26,94,224,101,-120,224,224,4,224,102,8,223,223,1001,224,7,224,1,224,223,223,1001,52,70,224,101,-87,224,224,4,224,1002,223,8,223,1001,224,2,224,1,223,224,223,1101,16,92,225,1101,59,24,225,102,83,48,224,101,-1162,224,224,4,224,102,8,223,223,101,4,224,224,1,223,224,223,1101,80,10,225,101,5,143,224,1001,224,-21,224,4,224,1002,223,8,223,1001,224,6,224,1,223,224,223,1102,94,67,224,101,-6298,224,224,4,224,102,8,223,223,1001,224,3,224,1,224,223,223,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,108,677,677,224,102,2,223,223,1005,224,329,101,1,223,223,1107,677,226,224,102,2,223,223,1006,224,344,101,1,223,223,1107,226,226,224,102,2,223,223,1006,224,359,101,1,223,223,1108,677,677,224,102,2,223,223,1005,224,374,101,1,223,223,8,677,226,224,1002,223,2,223,1005,224,389,101,1,223,223,108,226,677,224,1002,223,2,223,1006,224,404,1001,223,1,223,107,677,677,224,102,2,223,223,1006,224,419,101,1,223,223,1007,226,226,224,102,2,223,223,1005,224,434,101,1,223,223,1007,677,677,224,102,2,223,223,1005,224,449,1001,223,1,223,8,677,677,224,1002,223,2,223,1006,224,464,101,1,223,223,1108,677,226,224,1002,223,2,223,1005,224,479,101,1,223,223,7,677,226,224,1002,223,2,223,1005,224,494,101,1,223,223,1008,677,677,224,1002,223,2,223,1006,224,509,1001,223,1,223,1007,226,677,224,1002,223,2,223,1006,224,524,1001,223,1,223,107,226,226,224,1002,223,2,223,1006,224,539,1001,223,1,223,1107,226,677,224,102,2,223,223,1005,224,554,101,1,223,223,1108,226,677,224,102,2,223,223,1006,224,569,101,1,223,223,108,226,226,224,1002,223,2,223,1006,224,584,1001,223,1,223,7,226,226,224,1002,223,2,223,1006,224,599,101,1,223,223,8,226,677,224,102,2,223,223,1005,224,614,101,1,223,223,7,226,677,224,1002,223,2,223,1005,224,629,101,1,223,223,1008,226,677,224,1002,223,2,223,1006,224,644,101,1,223,223,107,226,677,224,1002,223,2,223,1005,224,659,1001,223,1,223,1008,226,226,224,1002,223,2,223,1006,224,674,1001,223,1,223,4,223,99,226")
;(def input "3,8,1001,8,10,8,105,1,0,0,21,38,55,72,93,118,199,280,361,442,99999,3,9,1001,9,2,9,1002,9,5,9,101,4,9,9,4,9,99,3,9,1002,9,3,9,1001,9,5,9,1002,9,4,9,4,9,99,3,9,101,4,9,9,1002,9,3,9,1001,9,4,9,4,9,99,3,9,1002,9,4,9,1001,9,4,9,102,5,9,9,1001,9,4,9,4,9,99,3,9,101,3,9,9,1002,9,3,9,1001,9,3,9,102,5,9,9,101,4,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99")
;(def input "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,\n27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5")
;(def input "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,\n-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,\n53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10")
;(def input "3,8,1001,8,10,8,105,1,0,0,21,38,55,72,93,118,199,280,361,442,99999,3,9,1001,9,2,9,1002,9,5,9,101,4,9,9,4,9,99,3,9,1002,9,3,9,1001,9,5,9,1002,9,4,9,4,9,99,3,9,101,4,9,9,1002,9,3,9,1001,9,4,9,4,9,99,3,9,1002,9,4,9,1001,9,4,9,102,5,9,9,1001,9,4,9,4,9,99,3,9,101,3,9,9,1002,9,3,9,1001,9,3,9,102,5,9,9,101,4,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99")
;(def input "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,\n-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,\n53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10")
;(def input "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0")
;(def input "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9")
;(def input "1102,34463338,34463338,63,1007,63,34463338,63,1005,63,53,1101,0,3,1000,109,988,209,12,9,1000,209,6,209,3,203,0,1008,1000,1,63,1005,63,65,1008,1000,2,63,1005,63,904,1008,1000,0,63,1005,63,58,4,25,104,0,99,4,0,104,0,99,4,17,104,0,99,0,0,1101,0,252,1023,1101,0,0,1020,1102,1,39,1013,1102,1,234,1029,1102,26,1,1016,1101,37,0,1005,1101,0,27,1011,1101,21,0,1000,1101,0,29,1019,1101,35,0,1003,1102,22,1,1007,1102,1,32,1001,1101,1,0,1021,1102,1,216,1027,1102,30,1,1012,1102,1,24,1009,1101,36,0,1002,1101,0,31,1010,1101,0,243,1028,1102,787,1,1024,1102,255,1,1022,1102,33,1,1017,1102,1,23,1004,1102,778,1,1025,1102,1,28,1008,1101,0,223,1026,1102,1,25,1015,1101,0,20,1006,1102,34,1,1014,1101,38,0,1018,109,-4,1202,5,1,63,1008,63,32,63,1005,63,203,4,187,1106,0,207,1001,64,1,64,1002,64,2,64,109,37,2106,0,-6,1001,64,1,64,1106,0,225,4,213,1002,64,2,64,109,3,2106,0,-8,4,231,1001,64,1,64,1105,1,243,1002,64,2,64,109,-12,2105,1,-1,1105,1,261,4,249,1001,64,1,64,1002,64,2,64,109,-13,2102,1,-3,63,1008,63,31,63,1005,63,285,1001,64,1,64,1106,0,287,4,267,1002,64,2,64,109,6,21102,40,1,0,1008,1017,40,63,1005,63,313,4,293,1001,64,1,64,1105,1,313,1002,64,2,64,109,-10,2107,31,-6,63,1005,63,331,4,319,1105,1,335,1001,64,1,64,1002,64,2,64,109,-6,2102,1,7,63,1008,63,28,63,1005,63,357,4,341,1105,1,361,1001,64,1,64,1002,64,2,64,109,2,21107,41,40,8,1005,1011,377,1106,0,383,4,367,1001,64,1,64,1002,64,2,64,109,-1,1201,2,0,63,1008,63,26,63,1005,63,403,1106,0,409,4,389,1001,64,1,64,1002,64,2,64,109,22,1205,-4,425,1001,64,1,64,1105,1,427,4,415,1002,64,2,64,109,-9,21101,42,0,3,1008,1018,39,63,1005,63,451,1001,64,1,64,1105,1,453,4,433,1002,64,2,64,109,3,21107,43,44,0,1005,1018,475,4,459,1001,64,1,64,1105,1,475,1002,64,2,64,109,-7,21101,44,0,0,1008,1011,44,63,1005,63,497,4,481,1105,1,501,1001,64,1,64,1002,64,2,64,109,17,1206,-7,513,1105,1,519,4,507,1001,64,1,64,1002,64,2,64,109,-24,1207,5,25,63,1005,63,537,4,525,1105,1,541,1001,64,1,64,1002,64,2,64,109,7,21108,45,43,2,1005,1013,557,1106,0,563,4,547,1001,64,1,64,1002,64,2,64,109,-5,1207,-3,34,63,1005,63,583,1001,64,1,64,1106,0,585,4,569,1002,64,2,64,109,5,21108,46,46,5,1005,1016,607,4,591,1001,64,1,64,1105,1,607,1002,64,2,64,109,-12,2108,20,8,63,1005,63,627,1001,64,1,64,1105,1,629,4,613,1002,64,2,64,109,24,1206,-3,647,4,635,1001,64,1,64,1105,1,647,1002,64,2,64,109,-30,2108,32,8,63,1005,63,665,4,653,1106,0,669,1001,64,1,64,1002,64,2,64,109,22,1208,-9,20,63,1005,63,691,4,675,1001,64,1,64,1106,0,691,1002,64,2,64,109,-4,21102,47,1,3,1008,1014,49,63,1005,63,715,1001,64,1,64,1105,1,717,4,697,1002,64,2,64,109,-10,2101,0,1,63,1008,63,36,63,1005,63,743,4,723,1001,64,1,64,1105,1,743,1002,64,2,64,109,16,1201,-9,0,63,1008,63,28,63,1005,63,769,4,749,1001,64,1,64,1105,1,769,1002,64,2,64,109,2,2105,1,5,4,775,1001,64,1,64,1106,0,787,1002,64,2,64,109,-5,1202,-6,1,63,1008,63,26,63,1005,63,807,1106,0,813,4,793,1001,64,1,64,1002,64,2,64,109,-16,2107,37,4,63,1005,63,833,1001,64,1,64,1105,1,835,4,819,1002,64,2,64,109,2,2101,0,1,63,1008,63,34,63,1005,63,855,1105,1,861,4,841,1001,64,1,64,1002,64,2,64,109,19,1205,2,875,4,867,1105,1,879,1001,64,1,64,1002,64,2,64,109,-2,1208,-8,23,63,1005,63,899,1001,64,1,64,1106,0,901,4,885,4,64,99,21101,0,27,1,21102,915,1,0,1106,0,922,21201,1,61455,1,204,1,99,109,3,1207,-2,3,63,1005,63,964,21201,-2,-1,1,21102,942,1,0,1105,1,922,22102,1,1,-1,21201,-2,-3,1,21102,1,957,0,1105,1,922,22201,1,-1,-2,1106,0,968,22101,0,-2,-2,109,-3,2105,1,0")
(def input "3,8,1005,8,324,1106,0,11,0,0,0,104,1,104,0,3,8,102,-1,8,10,101,1,10,10,4,10,1008,8,0,10,4,10,1002,8,1,29,2,1102,17,10,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,1,10,4,10,102,1,8,55,2,4,6,10,1,1006,10,10,1,6,14,10,3,8,1002,8,-1,10,101,1,10,10,4,10,1008,8,1,10,4,10,101,0,8,89,3,8,102,-1,8,10,1001,10,1,10,4,10,108,0,8,10,4,10,1002,8,1,110,1,104,8,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,1008,8,1,10,4,10,102,1,8,137,2,9,17,10,2,1101,14,10,3,8,102,-1,8,10,101,1,10,10,4,10,1008,8,0,10,4,10,101,0,8,167,1,107,6,10,1,104,6,10,2,1106,6,10,3,8,1002,8,-1,10,101,1,10,10,4,10,108,1,8,10,4,10,1001,8,0,200,1006,0,52,1006,0,70,1006,0,52,3,8,102,-1,8,10,101,1,10,10,4,10,1008,8,1,10,4,10,1002,8,1,232,1006,0,26,1,104,19,10,3,8,102,-1,8,10,1001,10,1,10,4,10,108,0,8,10,4,10,102,1,8,260,1,2,15,10,2,1102,14,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,0,8,10,4,10,1001,8,0,290,1,108,11,10,1006,0,36,1006,0,90,1006,0,52,101,1,9,9,1007,9,940,10,1005,10,15,99,109,646,104,0,104,1,21101,0,666412360596,1,21101,341,0,0,1105,1,445,21101,838366659476,0,1,21102,1,352,0,1106,0,445,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,21101,0,97713695975,1,21102,1,399,0,1106,0,445,21102,179469028392,1,1,21101,410,0,0,1105,1,445,3,10,104,0,104,0,3,10,104,0,104,0,21102,1,988220650260,1,21101,433,0,0,1105,1,445,21101,0,838345843560,1,21101,444,0,0,1106,0,445,99,109,2,22101,0,-1,1,21102,1,40,2,21102,1,476,3,21101,466,0,0,1106,0,509,109,-2,2105,1,0,0,1,0,0,1,109,2,3,10,204,-1,1001,471,472,487,4,0,1001,471,1,471,108,4,471,10,1006,10,503,1101,0,0,471,109,-2,2106,0,0,0,109,4,1202,-1,1,508,1207,-3,0,10,1006,10,526,21101,0,0,-3,22101,0,-3,1,22102,1,-2,2,21102,1,1,3,21101,0,545,0,1106,0,550,109,-4,2105,1,0,109,5,1207,-3,1,10,1006,10,573,2207,-4,-2,10,1006,10,573,21201,-4,0,-4,1106,0,641,21201,-4,0,1,21201,-3,-1,2,21202,-2,2,3,21102,592,1,0,1106,0,550,21201,1,0,-4,21101,0,1,-1,2207,-4,-2,10,1006,10,611,21101,0,0,-1,22202,-2,-1,-2,2107,0,-3,10,1006,10,633,22102,1,-1,1,21102,1,633,0,106,0,508,21202,-2,-1,-2,22201,-4,-2,-4,109,-5,2105,1,0")

(defn println-and-return [x]
  (do (println x) x))

(defn parameters-seq []
  (reverse (vec (filter
                  (fn [sequence] (== (count (set sequence)) (count sequence)))
                  (for [a (range 5 10)
                        b (range 5 10)
                        c (range 5 10)
                        d (range 5 10)
                        e (range 5 10)]
                    [a b c d e])))))

(defn create-initial-state [program input]
  {:state         :start
   :program       (reduce
                    conj
                    (map
                      (fn [code index] {index code})
                      program
                      (iterate inc 0)))
   :index         0
   :relative-base 0
   :input         input
   :output        []})

(defn create-initial-state-with-key [program input key]
  {key (create-initial-state program input)})

(defn create-initial-states [program parameters-seq amplifiers]
  (reduce
    conj
    (map (fn [parameter amplifier]
           (create-initial-state-with-key program [parameter] amplifier))
         parameters-seq amplifiers)))

(defn get-without-program [program-state]
  (dissoc program-state :program))

(defn get-without-programs [program-states]
  (reduce conj (map (fn [[k v]] {k (get-without-program v)}) program-states)))

(defn parse-int [value]
  (bigint value))

(defn parse-input [input]
  (vec
    (map parse-int
         (map clojure.string/trim
              (clojure.string/split input #",")))))

(defn command-parameters [value]
  [(mod value 100)
   (mod (quot value 100) 10)
   (mod (quot value 1000) 10)
   (mod (quot value 10000) 10)])

(defn write-handle-mode [program arg-index mode relative-base]
  (do
    #_(println "handle-mode" arg-index mode (get program arg-index))
    (let [value (get program arg-index)]
      (case mode
        0 value
        2 (+ relative-base value)))))

(defn pre-write-handle-mode [program-state modes index]
  (do
    #_(println modes index)
    #_(clojure.pprint/pprint
        (get-without-program program-state))
    (write-handle-mode
      (:program program-state)
      (+ (:index program-state) index)
      (nth modes (dec index))
      (:relative-base program-state))))


(defn read-handle-mode [program arg-index mode relative-base]
  (do
    #_(println "handle-mode" arg-index mode (get program arg-index))
    (let [value (get program arg-index)]
      (case mode
        0 (get program value 0)
        1 value
        2 (get program (+ relative-base value) 0)))))

(defn pre-read-handle-mode [program-state modes index]
  (do
    #_(println modes index)
    #_(clojure.pprint/pprint
        (get-without-program program-state))
    (read-handle-mode
      (:program program-state)
      (+ (:index program-state) index)
      (nth modes (dec index))
      (:relative-base program-state))))

(defn command-sum [program-state modes]
  (do
    #_(println (get-without-program program-state))
    (let [program (:program program-state)
          index (:index program-state)
          arg-1 (pre-read-handle-mode program-state modes 1)
          arg-2 (pre-read-handle-mode program-state modes 2)
          arg-3 (pre-write-handle-mode program-state modes 3)
          ;arg-3 (pre-handle-mode program-state modes 3)
          ;arg-3 (get program (+ 3 index))
          program (do
                    #_(println arg-1 arg-2 arg-3)
                    (assoc program arg-3 (+ arg-1 arg-2)))]
      (-> program-state
          (assoc :state :continue)
          (assoc :index (+ index 4))
          (assoc :program program)))))

(defn command-multiply [program-state modes]
  (let [program (:program program-state)
        index (:index program-state)
        arg-1 (pre-read-handle-mode program-state modes 1)
        arg-2 (pre-read-handle-mode program-state modes 2)
        arg-3 (pre-write-handle-mode program-state modes 3)
        ;arg-3 (pre-handle-mode program-state modes 3)
        ;arg-3 (get program (+ 3 index))
        program (assoc program arg-3 (* arg-1 arg-2))]
    (-> program-state
        (assoc :state :continue)
        (assoc :index (+ index 4))
        (assoc :program program))))

(defn command-read-input [program-state modes]
  (let [input (:input program-state)]
    (if (empty? input)
      (assoc program-state :state :wait-input)
      (let [program (:program program-state)
            index (:index program-state)
            ;arg-1 (get program (+ 1 index))
            arg-1 (pre-write-handle-mode program-state modes 1)
            ;arg-1 (pre-read-handle-mode program-state modes 1)
            program (assoc program arg-1 (first input))]
        (-> program-state
            (assoc :state :continue)
            (assoc :index (+ index 2))
            (assoc :program program)
            (assoc :input (rest input)))))))

(defn command-write-output [program-state modes]
  (let [index (:index program-state)
        output (:output program-state)
        arg-1 (pre-read-handle-mode program-state modes 1)
        output (conj output arg-1)]
    (-> program-state
        (assoc :state :send-message)
        (assoc :index (+ index 2))
        (assoc :output output))))

(defn command-jump-if-true [program-state modes]
  (let [index (:index program-state)
        arg-1 (pre-read-handle-mode program-state modes 1)
        arg-2 (pre-read-handle-mode program-state modes 2)
        index (if (not (zero? arg-1)) arg-2 (+ index 3))]
    (-> program-state
        (assoc :state :continue)
        (assoc :index index))))

(defn command-jump-if-false [program-state modes]
  (let [index (:index program-state)
        arg-1 (pre-read-handle-mode program-state modes 1)
        arg-2 (pre-read-handle-mode program-state modes 2)
        index (if (zero? arg-1) arg-2 (+ index 3))]
    (-> program-state
        (assoc :state :continue)
        (assoc :index index))))

(defn command-less-than [program-state modes]
  (let [program (:program program-state)
        index (:index program-state)
        arg-1 (pre-read-handle-mode program-state modes 1)
        arg-2 (pre-read-handle-mode program-state modes 2)
        arg-3 (pre-write-handle-mode program-state modes 3)
        ;arg-3 (pre-handle-mode program-state modes 3)
        ;arg-3 (get program (+ 3 index))
        store-value (if (< arg-1 arg-2) 1 0)
        program (assoc program arg-3 store-value)]
    (-> program-state
        (assoc :state :continue)
        (assoc :index (+ index 4))
        (assoc :program program))))

(defn command-equals [program-state modes]
  (let [program (:program program-state)
        index (:index program-state)
        arg-1 (pre-read-handle-mode program-state modes 1)
        arg-2 (pre-read-handle-mode program-state modes 2)
        arg-3 (pre-write-handle-mode program-state modes 3)
        ;arg-3 (pre-handle-mode program-state modes 3)
        ;arg-3 (get program (+ 3 index))
        store-value (if (== arg-1 arg-2) 1 0)
        program (assoc program arg-3 store-value)]
    (-> program-state
        (assoc :state :continue)
        (assoc :index (+ index 4))
        (assoc :program program))))

(defn command-adjust-relative-base [program-state modes]
  (let [index (:index program-state)
        relative-base (:relative-base program-state)
        arg-1 (pre-read-handle-mode program-state modes 1)]
    (-> program-state
        (assoc :state :continue)
        (assoc :index (+ index 2))
        (assoc :relative-base (+ relative-base arg-1)))))

(defn command-halt [program-state modes]
  (assoc program-state :state :halt))

(defn choose-command [command]
  (case command
    1 command-sum
    2 command-multiply
    3 command-read-input
    4 command-write-output
    5 command-jump-if-true
    6 command-jump-if-false
    7 command-less-than
    8 command-equals
    9 command-adjust-relative-base
    99 command-halt))

(defn handle-command [program-state command modes]
  (do
    #_(let [index (:index program-state)
            program (:program program-state)]
        (println index
                 "----"
                 (get program index)
                 (get program (+ index 1))
                 (get program (+ index 2))
                 (get program (+ index 3))
                 (get program (+ index 4))))
    (apply (choose-command command) [program-state modes])))

(defn get-command [program-state]
  (get (:program program-state) (:index program-state)))

(defn evaluate [program-state]
  (let [[command & modes] (command-parameters (get-command program-state))
        program-state (handle-command program-state command modes)]
    (case (:state program-state)
      (:halt :wait-input) program-state
      (recur program-state))))

(defn emulate-amplifiers-step [program-states previous-result amplifier-key]
  (do
    #_(println "before" output state amplifier-key previous-result)
    (let [program-state (get program-states amplifier-key)
          input (:input program-state)
          next-input (conj input previous-result)
          next-program-state (assoc program-state :input next-input)
          one-amplifier-state (evaluate next-program-state)
          output (:output one-amplifier-state)]
      (do
        #_(println "after" (get-without-program one-amplifier-state) amplifier-key previous-result)
        [(assoc program-states
           amplifier-key
           (-> one-amplifier-state
               (assoc :output (subvec output 1))
               (assoc :input (rest input))))
         (first (:output one-amplifier-state))]))))

(defn emulate-amplifiers [program-states previous-result amplifiers-seq]
  (do
    #_(clojure.pprint/pprint
        (get-without-programs program-states))
    (if (= :halt
           (:state (get program-states (first amplifiers-seq))))
      previous-result
      (let [[program-states output-value]
            (emulate-amplifiers-step
              program-states
              previous-result
              (first amplifiers-seq))]
        (recur program-states output-value (rest amplifiers-seq))))))

;(let [program (parse-input input)]
;  (let [amplifiers [:first :second :third :fourth :fifth]]
;    (reduce
;      max
;      (map (fn [parameters]
;             (emulate-amplifiers
;               (create-initial-states program parameters amplifiers)
;               0
;               (cycle amplifiers)))
;           (parameters-seq)))))

(defn next-step [x y direction]
  (case direction
    :up [x (inc y)]
    :right [(inc x) y]
    :down [x (dec y)]
    :left [(dec x) y]))

(defn next-direction [directions direction turn]
  (let [direction-index (.indexOf directions direction)
        turn (if (== 0 turn) -1 turn)
        length (count directions)
        direction (mod (+ length direction-index turn) length)]
    (get directions direction)))

(def DIRECTIONS [:up :right :down :left])

(defn emulate-robot [program-state colors steps x y direction]
  (do
    #_(clojure.pprint/pprint
        [
         (get-without-program program-state)
         colors
         steps
         directions
         x y])
    (if (= :halt (:state program-state))
      [colors steps]
      (let [color (get colors [x y] 0)
            program-state (evaluate (-> program-state
                                        (assoc :input [color])
                                        (assoc :output [])))
            [paint-color turn] (:output program-state)
            colors (assoc colors [x y] paint-color)
            direction (next-direction DIRECTIONS direction turn)
            steps (conj steps [x y])
            [x y] (next-step x y direction)]
        (recur program-state colors steps x y direction)))))

(defn max-min-grid [keys]
  (let [xs (vec (map (fn [[x y]] x) keys))
        ys (vec (map (fn [[x y]] y) keys))]
    [(reduce min xs)
     (reduce max xs)
     (reduce min ys)
     (reduce max ys)]))

(defn print-grid [colors]
  (let [[min-x max-x min-y max-y] (max-min-grid (keys colors))]
    (for [y (reverse (range min-y (inc max-y)))]
      (reduce
        str
        (map
          (fn [key] (if (= 0 (get colors key 0)) "." "#"))
          (for [x (range min-x (inc max-x))] [x y]))))))

(let [program (parse-input input)
      program-state (create-initial-state program [1])
      [colors steps] (emulate-robot program-state {} [] 0 0 DIRECTIONS)]
  (print-grid colors))

(ns advent-of-code.year2020.day1
  (:require [advent-of-code.utils :as utils]))

(defn find-number-with-sum [number->frequency sum [first-number second-number]]
  (let [target-number           (- sum first-number second-number)
        number->frequency       (-> number->frequency
                                    (update first-number dec)
                                    (update second-number dec))
        target-number-frequency (get number->frequency target-number 0)]
    (if (> target-number-frequency 0)
      [first-number second-number target-number]
      nil)))

(defn find-target-sum [target-sum numbers]
  (let [number->frequency (frequencies numbers)]
    (->> (for [first-number  numbers
               second-number numbers]
           [first-number second-number])
         (map (partial find-number-with-sum number->frequency target-sum))
         (filter some?)
         (some identity))))

(defn multiply-result-values [coll]
  (apply * (find-target-sum 2020 coll)))

(defn parse-input [line]
  (->> line
       (clojure.string/split-lines)
       (mapv #(Integer/parseInt %))))

(defn -main [& args]
  (->> (utils/parsed-files "resources/2020/day1" parse-input)
       (map (partial utils/assoc-result multiply-result-values))
       (map utils/remove-content))
  )

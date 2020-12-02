(ns advent-of-code.utils
  (:import (java.io File)))

(defn files [folder]
  (->> (clojure.java.io/file folder)
       (file-seq)
       (filter #(^File .isFile %))))

(defn parsed-files [folder parser]
  (->> (files folder)
       (map #(^File .getPath %))
       (map #(array-map :file-name % :content (parser (slurp %))))))

(defn assoc-result [f state]
  (assoc state :result (f (:content state))))

(defn remove-content [state]
  (dissoc state :content))

(ns day4.core
  (:gen-class)
  (:require [clojure.string :as str]))

;; We need a function to get all possible permutations of a line
;; Split-file gives all horizontal lines so thats covered
;; We need vertical lines so we would have to transpose the split file somehow
;; Lastly we need to figur out diagonals so starting with top left -> bottom right
;; and top right -> bottom left

;; Function to check the number of occurences of XMAS and SAMX in a line
(defn check-line
  [line]
  (let [occurences (re-seq #"(?=(XMAS|SAMX))" line)]
    (count occurences)))

;;  Function to transpose a 2D array to get vertical lines
(defn transpose
  [matrix]
  (apply mapv vector matrix))

(defn get-top-right-diagonal
  [matrix row col]
  (loop [r row
         c col
         diagonal []]
    (if (or (>= r (count matrix))
            (< c 0))
      (str/join diagonal)
      (recur (inc r)
             (dec c)
             (conj diagonal (get-in matrix [r c]))))))

(defn get-top-left-diagonal
  [matrix row col]
  (loop [r row
         c col
         diagonal []]
    (if (or (>= r (count matrix))
            (>= c (count (first matrix))))
      (str/join diagonal)
      (recur (inc r)
             (inc c)
             (conj diagonal (get-in matrix [r c]))))))

(defn get-diagonals
  [matrix]
  (let [rows (count matrix)
        cols (count (first matrix))
        top-right-diagonals (for [c (range cols)]
                              (get-top-right-diagonal matrix 0 c))
        top-left-diagonals (for [c (range cols)]
                             (get-top-left-diagonal matrix 0 c))
        first-col-top-left (for [r (range rows)]
                             (get-top-left-diagonal matrix r 0))
        last-col-top-right (for [r (range rows)]
                             (get-top-right-diagonal matrix r (dec cols)))]
    (filter #(>= (count %) 4)
            (concat top-right-diagonals top-left-diagonals first-col-top-left last-col-top-right))))

(defn -main
  [& args]
  (let [file (slurp "resources/input.txt")
        horizontal-lines (str/split-lines file)
        two-d-array (vec (map vec horizontal-lines))
        transposed-array (transpose two-d-array)
        vertical-lines (map str/join transposed-array)
        diagonals (get-diagonals two-d-array)
        total (->> (concat horizontal-lines vertical-lines diagonals)
                   (map check-line)
                   (reduce +))]
    (println "horizontal values:" (reduce + (map check-line horizontal-lines)))
    (println "vertical values:" (reduce + (map check-line vertical-lines)))
    (println "diagonal values:" (reduce + (map check-line diagonals)))
    (println "TOTAL:" total)))
    ;; (println (check-line (first vertical-lines)))))

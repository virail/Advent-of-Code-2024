(ns day2.core
  (:require [clojure.string :as str])
  (:gen-class))

;; take a line and process it
(defn process-line
  [line]
  (let [split-line (str/split line #" ")]
    (mapv #(Integer/parseInt %) split-line)))

;; Saw Partition but that won't work (03/12/24 It Will WORK 😭)
(defn direction?
  [x y]
  (cond
    (< x y) "inc"
    (> x y) "dec" 
    :else "eq"))

;; Check pair depending on if incrementing or decrementing
(defn check-pair
  [x y direction]
  (cond
    (= direction "inc") (and (< x y) (<= (- y x) 3))
    (= direction "dec") (and (> x y) (<= (- x y) 3))
    :else false))

;; figure out how to process a line at a time
(defn check-line
  [line]
  (let [current (process-line line)
        direction (direction? (first current) (second current))
        pairs (partition 2 1 current)]
        (every? #(check-pair (first %) (second %) direction) pairs)))

;; if decrementing then we need to make sure its within bounds of 1 <= dec <= 3

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [reports (slurp "resources/reports.txt")
        split-reports (str/split-lines reports)
        valid-lines (filter check-line split-reports)]
    (println "Number of valid lines:" (count valid-lines))))

;; Now we have to check and see if removing a singular element in a line will allow it to pass
;; We can do this by removing an element and checking if the line is valid
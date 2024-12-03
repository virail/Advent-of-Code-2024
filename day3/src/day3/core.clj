(ns day3.core
  (:gen-class)
  (:require [clojure.string :as str]))

;; process a line using regex I believe
(defn extract-instructions
  [line]
  (re-seq #"mul\((\d{1,3}),(\d{1,3})\)" line))

(defn extract-numbers
[instruction]
(vector (Integer/parseInt (second instruction)) (Integer/parseInt (last instruction))))

(defn process-line
[line]
(let [instructions (extract-instructions line)]
(reduce + (map (fn [[a b]] (* a b)) (map extract-numbers instructions)))))

;; the instructions 

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [file (slurp "resources/instructions.txt")
        split-file (str/split-lines file)
        instructions (map process-line split-file)]
    (print (reduce + instructions))))

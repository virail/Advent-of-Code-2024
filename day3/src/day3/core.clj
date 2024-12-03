(ns day3.core
  (:gen-class)
  (:require [clojure.string :as str]))

;; process a line using regex I believe
(defn extract-instructions
  [line]
  (re-seq #"mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)" line))
;; Now returns both mul and don't/do instructions
;; so when using we need a flag on the do() and don't() instructions


;; only extract-numbers when mul instruction
(defn extract-numbers
  [instruction]
  (vector (Integer/parseInt (second instruction)) (Integer/parseInt (last instruction))))

(defn process-line
  [line]
  (let [instructions (extract-instructions line)]
    (loop [curr instructions
           flag true
           result 0]
      (if (empty? curr)
        result
        (let [instruction (first curr)]
          (cond
            (re-matches #"do\(\)" (first instruction)) (recur (rest curr) true result)
            (re-matches #"don't\(\)" (first instruction)) (recur (rest curr) false result)
            :else (let [[_ a b] instruction
                        a (Integer/parseInt a)
                        b (Integer/parseInt b)]
                    (recur (rest curr) flag (if flag (+ result (* a b)) result)))))))))

;; the instructions 

;; Now have to check for don't() and do() which enables or disables the mul instructions
;; could maybe use regex to capture both the mul and don't/do instructions
;; then go through the instructions and have a flag that is set on the do() and don't()

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [file (slurp "resources/instructions.txt")
        instructions (process-line file)] 
    (print instructions)))

;; changed so we pass the whole file to process-line instead of split by lines since the flag would reset between lines
;; we want it to persist so a dont() call will prevent mul's in the next line etc.
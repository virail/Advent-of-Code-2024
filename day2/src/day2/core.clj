(ns day2.core
  (:require [clojure.string :as str])
  (:gen-class))

(def reports (slurp "resources/reports.txt"))

;; take a line and process it
(defn process-line [line]
    (map #(Integer/parseInt %) bits))

;; Saw Partition but that won't work

(defn check
  [line]
  (loop [cur (first line)
         sec (second line)]
    (if (> cur sec)
      (if (> (- cur sec) 3)
        false
        (recur (rest line) sec))))
    (if (< cur sec)
      (if (> (- sec cur) 3)
        false
        (recur (rest line) sec))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

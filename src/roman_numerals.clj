(ns roman-numerals
  (:gen-class))

(def ^:private arabic->roman-map (into (sorted-map-by >) {1000 "M"
                                                          900  "CM"
                                                          500  "D"
                                                          400  "CD"
                                                          100  "C"
                                                          90   "XC"
                                                          50   "L"
                                                          40   "XL"
                                                          10   "X"
                                                          9    "IX"
                                                          5    "V"
                                                          4    "IV"
                                                          1    "I"}))


;;;;;;;; FIRST ATTEMPT - MAKES TESTS PASS, TDD ;;;;;;;;;;;;;;;;;
;(defn- loop-current-numeral [remainder result-seq arabic numeral]
;  (loop [remainder remainder
;         result-seq result-seq]
;    (if (> arabic remainder)
;      [remainder result-seq]
;      (recur (- remainder arabic) (conj result-seq numeral)))))
;
;(defn- generate-roman-numerals [arabic-number]
;  (let [reducer (reduce
;                  (fn [[remainder result-seq] [arabic numeral]]
;                    (if (pos-int? remainder)
;                      (loop-current-numeral remainder result-seq arabic numeral)
;                      (reduced [remainder result-seq])))
;                  [arabic-number []]
;                  arabic->roman-map)]
;    (reduce str (last reducer))))


;;;;;;;; SECOND ATTEMPT - USES SAME TESTS AS REGRESSION, MORE CONCISE. ;;;;;;;;;;;;;;;;;
(defn generate-roman-numerals [arabic-number]
  (loop [remainder arabic-number
         result-set []]
    (if (pos-int? remainder)
      (let [[map-int numeral] (first (filter #(>= remainder (key %)) arabic->roman-map))]
        (recur (- remainder map-int) (conj result-set numeral)))
      (reduce str result-set))))

(defn generate [arabic-number]
  (if (and (number? arabic-number)
           (< arabic-number 4000))
    (generate-roman-numerals arabic-number)
    (throw (Exception. "Please only pass numbers below 4,000"))))

(defn- parse-int [number-string]
  (try (Integer/parseInt number-string)
       (catch Exception e nil)))

(defn -main "main function - takes a string as per cmd"
  [string-integer]
  (prn (str "The roman numeral for " string-integer " is '" (generate (parse-int string-integer)) "'.")))
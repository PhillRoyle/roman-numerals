(ns roman-numerals-test
  (:require [midje.sweet :refer :all]
            [roman-numerals :refer [generate]]))


(facts "about fetching roman numerals - happy path"
  (tabular "the simple ones"
           (let [description ?description]
             (fact {:midje/description description}
                   (generate ?arabic) => ?numeral))

                ?description            ?arabic | ?numeral
                "get numeral for 1"     1       | "I"
                "get numeral for 5"     5       | "V"
                "get numeral for 10"    10      | "X"
                "get numeral for 50"    50      | "L"
                "get numeral for 100"   100     | "C"
                "get numeral for 500"   500     | "D"
                "get numeral for 1000"  1000    | "M")

  (tabular "the cumulative ones"
           (let [description ?description]
             (fact {:midje/description description}
                   (generate ?arabic) => ?numeral))

                ?description           ?arabic | ?numeral
                "get numeral for 6"    6       | "VI"
                "get numeral for 11"   11      | "XI"
                "get numeral for 15"   15      | "XV"
                "get numeral for 16"   16      | "XVI"
                "get numeral for 17"   17      | "XVII"
                "get numeral for 61"   61      | "LXI"
                "get numeral for 1155" 1155    | "MCLV"))

(facts "about fetching roman numerals - more complex"
       (tabular "the 4x and 9x ones"
                (let [description ?description]
                  (fact {:midje/description description}
                        (generate ?arabic) => ?numeral))

                ?description           ?arabic | ?numeral
                "get numeral for 4"    4       | "IV"
                "get numeral for 9"    9       | "IX"
                "get numeral for 40"   40      | "XL"
                "get numeral for 90"   90      | "XC"
                "get numeral for 400"  400     | "CD"
                "get numeral for 900"  900     | "CM")

       (tabular "the looping ones"
                (let [description ?description]
                  (fact {:midje/description description}
                        (generate ?arabic) => ?numeral))

                ?description           ?arabic | ?numeral
                "get numeral for 2"    2       | "II"
                "get numeral for 3"    3       | "III"
                "get numeral for 17"   17      | "XVII"
                "get numeral for 21"   21      | "XXI"
                "get numeral for 1235" 1235    | "MCCXXXV"
                "get numeral for 3000" 3000    | "MMM"
                "get numeral for 3999" 3999    | "MMMCMXCIX"))

(facts "about fetching roman numerals - at the edges"
       (fact "fetching 0"
             (generate 0) => "")
       (fact "fetching nil"
             (generate nil) => (throws Exception "Please only pass numbers below 4,000"))
       (fact "fetching above the limit"
             (generate 4000) => (throws Exception "Please only pass numbers below 4,000"))
       (fact "fetching a string"
             (generate "4000") => (throws Exception "Please only pass numbers below 4,000")))

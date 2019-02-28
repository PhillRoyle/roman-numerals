(defproject roman-numerals "0.1.0-SNAPSHOT"
  :description "roman-numerals exercise"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [midje/midje "1.9.0"]]
  :mirrors {"central"  {:name "central"
                        :url  "https://repo.maven.apache.org/maven2/"}
            #"clojars" {:name "Internal nexus"
                        :url  "https://repo.clojars.org"}}
  :main roman-numerals
  :aot [roman-numerals])
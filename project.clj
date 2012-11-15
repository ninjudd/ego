(defproject ego "0.1.9"
  :url "https://github.com/flatland/ego"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :description "A library for working with a simple 'id' format."
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [useful "0.8.3-alpha8"]]
  :profiles {:1.3 {:dependencies [[org.clojure/clojure "1.3.0"]]}
             :1.5 {:dependencies [[org.clojure/clojure "1.5.0-master-SNAPSHOT"]]}}
  :aliases {"testall" ["with-profile" "dev,default:dev,1.3,default:dev,1.5,default" "test"]}
  :repositories {"sonatype-snapshots" {:url "http://oss.sonatype.org/content/repositories/snapshots"
                                       :snapshots true
                                       :releases {:checksum :fail :update :always}}})

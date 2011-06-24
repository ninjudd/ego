(ns ego.core
  (:use [useful :only [verify]]
        [clojure.string :only [join]]))

(defn node-type [^String id]
  (let [i (.indexOf id "-")]
    (when (not= -1 i)
      (.substring id 0 i))))

(defn node-number [^String id & [node-type]]
  (let [[type num] (.split id "-")]
    (verify (or (nil? node-type) (= node-type type))
            (format "node-id %s is not of type %s" id node-type))
    (Long/parseLong num)))

(defn verify-type [expected id]
  (verify (contains? expected (-> id node-type keyword))
          (format "%s is not of type(s): %s" id (join ", " (map name expected)))))
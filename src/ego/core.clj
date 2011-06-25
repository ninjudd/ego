(ns ego.core
  (:use [useful :only [verify]]
        [clojure.string :only [join]]))

(defn split-id
  "Split an id on -. Optionally pass a function (such as a set) that will be passed
   the id's type. If this function returns false, an error will be thrown."
  [^String id & [expected]]
  (let [[type num] (.split id "-")
        type (keyword type)]
    (verify (or (nil? expected) (expected type))
            (if (set? expected)
              (format "node-id %s doesn't match type(s): %s"
                      id (join ", " (map name expected)))
              "node-id's type was not what was expected"))
    [type (Long/parseLong num)]))
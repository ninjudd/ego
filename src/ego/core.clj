(ns ego.core
  (:use [useful.utils :only [verify]]
        [clojure.string :only [join]]))

(defn split-id
  "Split an id on -. Optionally pass a function (such as a set) that will be passed
   the id's type. If this function returns false, an error will be thrown."
  [^String string & [expected]]
  (let [parts (.split string "-")
        untyped (= 1 (alength parts))
        type (when-not untyped
               (keyword (aget parts 0)))
        id (aget parts (if untyped 0 1))]
    (verify (or (nil? expected) (expected type))
            (if (set? expected)
              (format "node-id %s doesn't match type(s): %s"
                      string (join ", " (map name expected)))
              "node-id's type was not what was expected"))
    [type id]))

(defn type-key
  "Given an id or type, return its type as a keyword."
  [type-or-id]
  (if (keyword? type-or-id)
    type-or-id
    (first (split-id type-or-id))))

(defn type-name
  "Given an id or type, return its type as a string."
  [type-or-id]
  (name (type-key type-or-id)))

(defn type?
  "Check to see if the type of id matches on of the given set of type keywords."
  [type id]
  (if (set? type)
    (contains? type (type-key id))
    (.startsWith ^String id (str (name type) "-"))))
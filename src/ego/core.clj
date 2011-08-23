(ns ego.core
  (:use [useful.fn :only [fix]]
        [useful.utils :only [verify]]
        [clojure.string :only [join]]))

(defn split-id
  "Split an id on dash. Optionally pass a function (such as a set) that will be passed
   the id's type. If this function returns false, an error will be thrown."
  [^String id & [expected]]
  (let [parts (.split id "-")
        untyped (= 1 (alength parts))
        type (when-not untyped
               (keyword (aget parts 0)))
        key (aget parts (if untyped 0 1))]
    (verify (or (nil? expected) (expected type))
            (if (set? expected)
              (format "node-id %s doesn't match type(s): %s"
                      id (join ", " (map name expected)))
              "node-id's type was not what was expected"))
    [type key]))

(defn id-number
  "Split the provided id and convert to a Long. Optionally, pass a function to validate the id."
  [id & [expected]]
  (if (string? id)
    (Long. (last (split-id id expected)))
    id))

(defn make-id
  [type id]
  (str (name type) "-"
       (fix id string? (comp last split-id))))

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
  [type ^String id]
  (if (set? type)
    (contains? type (type-key id))
    (let [type-str ^String (name type)
          type-len (.length type-str)
          id-len (.length id)]
      (and (> id-len type-len)
           (.startsWith id type-str)
           (= \- (.charAt id type-len))))))

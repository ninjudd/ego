(ns ego.core
  (:use [useful :only [verify]]
        [clojure.string :only [join]]))

(defn split-id
  "Split an id on -. If node-type is passed, verify that the type matches."
  [^String id & [node-type]]
  (let [[type num] (.split id "-")]
    (verify (or (nil? node-type) (= node-type type))
            (format "node-id %s is not of type %s" id node-type))
    [type (Long/parseLong num)]))

(defn node-type
  "Get the type of an id. If node-type is passed, verify that the type matches."
  [^String id & [node-type]]
  (first (split-id id node-type)))

(defn node-number
  "Get the number of an id. If node-type is passed, verify that the type matches."
  [^String id & [node-type]]
  (second (split-id id node-type)))
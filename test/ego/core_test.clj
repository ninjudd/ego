(ns ego.core-test
  (:use ego.core
        midje.sweet))

;; #
(fact "split-id returns a vector of keyword type and string id"
  (split-id "foo-1") => (just [:foo "1"]))

(fact "split-id can take a function"
  (split-id "foo-1" #{:foo}) => (just [:foo "1"]))

(fact "split-id throws an exception when function returns false"
  (split-id "foo-1" #{:bar}) => (throws Exception #"doesn't match")
  (split-id "foo-1" (constantly false)) => (throws Exception #"was not what"))

(fact "id-number returns a Long"
  (id-number "robot-1") => 1
  (class (id-number "robot-1")) => Long)

(fact "make-id works with raw numbers"
  (make-id :robot 4) => "robot-4")

(fact "make-id works replaces type of existing id"
  (make-id :robot "person-11") => "robot-11")

(fact "type-key returns the type of an id"
  (type-key "foo-1") => :foo)

(fact "type-key just returns a type directly"
  (type-key :bar) => :bar)

(fact "type-name returns the type of an id as a string"
  (type-name "foo-1") => "foo"
  (type-name :bar) => "bar")

(fact "type? checks the type of id"
  (type? :foo "foo-1") => true
  (type? :foo "bar-11") => false)

(fact "type? checks the type of id with a set"
  (type? #{:foo :bar} "bar-1") => true
  (type? #{:foo :bar} "baz-11") => false)

(fact "handles untyped nodes with type-looking names"
  (type? :foo "foo") => false)

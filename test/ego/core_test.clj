(ns ego.core-test
  (:use ego.core
        clojure.test))

(deftest test-split-id
  (testing "split-id returns a vector of keyword type and string id"
    (is (= [:foo "1"] (split-id "foo-1"))))
  (testing "split-id can take a function"
    (is (= [:foo "1"] (split-id "foo-1" #{:foo}))))
  (testing "split-id throws an exception when function returns false"
    (is (thrown-with-msg? Exception #"doesn't match" (split-id "foo-1" #{:bar})))
    (is (thrown-with-msg? Exception #"was not what" (split-id "foo-1" (constantly false))))))

(deftest test-id-number
  (testing "id-number returns a Long"
    (is (= 1 (id-number "robot-1")))
    (is (= Long (class (id-number "robot-1"))))))

(deftest test-make-id
  (testing "make-id works with raw numbers"
    (is (= "robot-4" (make-id :robot 4))))
  (testing "make-id works replaces type of existing id"
    (is (= "robot-11" (make-id :robot "person-11")))))

(deftest test-type-key
  (testing "type-key returns the type of an id"
    (is (= :foo (type-key "foo-1"))))
  (testing "type-key just returns a type directly"
    (is (= :bar (type-key :bar)))))

(deftest test-type-name
  (testing "type-name returns the type of an id as a string"
    (is (= "foo" (type-name "foo-1"))))
  (testing "type-name just returns a type as a string"
    (is (= "bar" (type-name :bar)))))

(deftest test-type?
  (testing "type? checks the type of id"
    (is (type? :foo "foo-1"))
    (is (not (type? :foo "bar-11"))))
  (testing "type? checks the type of id with a set"
    (is (type? #{:foo :bar} "bar-1"))
    (is (not (type? #{:foo :bar} "baz-11"))))
  (testing "handles untyped nodes with type-looking names"
    (is (not (type? :foo "foo")))))
